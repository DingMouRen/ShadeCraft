package com.dingmouren.shadecraft.core.widgets

import android.graphics.BlurMaskFilter
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.ConstantColor.NeumorphismLightBackgroundColor
import com.dingmouren.shadecraft.core.LightSource
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

@Composable
fun ShadeTabPathBar(
    radiusSize: Dp = 0.dp,
    tabSize:Int = 0,
    tabHeight:Dp = 0.dp,
    selectedIndex:Int = 0,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor:Color = NeumorphismLightBackgroundColor,
    blurRadius:Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset:Float = 10f,
){
    val TAG = "ShaderTabPathBar"
    BoxWithConstraints() {
        Log.d(TAG,"constraints:${constraints.toString()}")
        Canvas(modifier = Modifier.fillMaxSize() ){
            val tabWidthPx = constraints.maxWidth/tabSize.toFloat()
            val tabHeightPx = tabHeight.toPx()
            val radiusSizePx = radiusSize.toPx()

            val path = createPath(constraints,tabWidthPx,tabHeightPx,selectedIndex,radiusSizePx,tabSize)
            //浅色阴影画笔
            val paintShadowLight = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }
            //浅色阴影在光源方向的偏移量
            val backgroundShadowLightOffset: Offset = when(lightSource){
                LightSource.LEFT_TOP -> Offset(-offset,-offset)
                LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
                LightSource.RIGHT_TOP -> Offset(offset, -offset)
                LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
                else -> {
                    Offset(0f,0f)
                }
            }

            //深色阴影画笔
            val paintShadowDark = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }
            //深色阴影在光源方向的偏移量
            val backgroundShadowDarkOffset:Offset = when(LightSource.opposite(lightSource)){
                LightSource.LEFT_TOP -> Offset(-offset,-offset)
                LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
                LightSource.RIGHT_TOP -> Offset(offset, -offset)
                LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
                else -> {Offset(0f,0f)}
            }

            drawIntoCanvas {

                //画布平移绘制浅色阴影
                it.save()
                it.translate(backgroundShadowLightOffset.x,backgroundShadowLightOffset.y)
                it.drawPath(path,paintShadowLight)

                it.restore()
                //画布平移绘制深色阴影
                it.save()
                it.translate(backgroundShadowDarkOffset.x,backgroundShadowDarkOffset.y)
                it.drawPath(path,paintShadowDark)
                it.restore()
            }
            //绘制背景
            drawPath(
                path = path,
                color = backgroundColor,
                style = Fill
            )
        }

    }
}

fun createPath(constraints: Constraints, tabWidthPx: Float, tabHeightPx: Float, selectedIndex: Int,radiusSizePx:Float,tabSize:Int): Path {

    return if (radiusSizePx > 0f){
        Path().apply {
            moveTo(selectedIndex * tabWidthPx + radiusSizePx, 0f)
            lineTo(selectedIndex * tabWidthPx + tabWidthPx - radiusSizePx,0f)
            quadraticBezierTo(selectedIndex * tabWidthPx + tabWidthPx,
                    0f,
                selectedIndex * tabWidthPx + tabWidthPx,
                    radiusSizePx
                )
            if (selectedIndex == tabSize - 1 ){
                lineTo(selectedIndex * tabWidthPx + tabWidthPx,tabHeightPx + radiusSizePx)
            }else{
                lineTo(selectedIndex * tabWidthPx + tabWidthPx,tabHeightPx -  radiusSizePx)
                quadraticBezierTo(
                    selectedIndex * tabWidthPx + tabWidthPx,
                    tabHeightPx,
                    selectedIndex * tabWidthPx + tabWidthPx + radiusSizePx,
                    tabHeightPx
                )
                lineTo(constraints.maxWidth - radiusSizePx,tabHeightPx )
                quadraticBezierTo(
                    constraints.maxWidth.toFloat(),
                    tabHeightPx,
                    constraints.maxWidth.toFloat(),
                    tabHeightPx + radiusSizePx
                )
            }


            lineTo(constraints.maxWidth.toFloat(),constraints.maxHeight - radiusSizePx )
            quadraticBezierTo(
                constraints.maxWidth.toFloat(),
                constraints.maxHeight.toFloat(),
                constraints.maxWidth - radiusSizePx,
                constraints.maxHeight.toFloat(),

            )
            lineTo(radiusSizePx,constraints.maxHeight.toFloat())
            quadraticBezierTo(
                0f,
                constraints.maxHeight.toFloat(),
                0f,
                constraints.maxHeight - radiusSizePx,
            )
            lineTo(0f,tabHeightPx+radiusSizePx)
            if (selectedIndex == 0){
                lineTo(0f,radiusSizePx)
                quadraticBezierTo(
                    0f,
                    0f,
                    radiusSizePx,
                    0f
                )
            }else{
                quadraticBezierTo(
                    0f,
                    tabHeightPx,
                    radiusSizePx,
                    tabHeightPx
                )
                lineTo(selectedIndex * tabWidthPx - radiusSizePx,tabHeightPx)
                quadraticBezierTo(
                    selectedIndex * tabWidthPx,
                    tabHeightPx,
                    selectedIndex * tabWidthPx,
                    tabHeightPx - radiusSizePx
                )
                lineTo(selectedIndex * tabWidthPx ,radiusSizePx)
                quadraticBezierTo(
                    selectedIndex * tabWidthPx,
                    0f,
                    selectedIndex * tabWidthPx + radiusSizePx,
                    0f
                )

                close()

            }
        }



    }else{
       Path().apply {
            moveTo(selectedIndex * tabWidthPx,0f)
            lineTo(selectedIndex * tabWidthPx + tabWidthPx,0f)
            lineTo(selectedIndex * tabWidthPx + tabWidthPx,tabHeightPx)
            lineTo(constraints.maxWidth.toFloat(),tabHeightPx)
            lineTo(constraints.maxWidth.toFloat(),constraints.maxHeight.toFloat())
            lineTo(0f,constraints.maxHeight.toFloat())
            lineTo(0f,tabHeightPx)
            lineTo(selectedIndex * tabWidthPx,tabHeightPx)
            lineTo(selectedIndex * tabWidthPx,0f)
            close()
        }
    }


}
