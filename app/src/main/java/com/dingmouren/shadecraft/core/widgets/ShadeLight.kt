package com.dingmouren.shadecraft.core.widgets

import android.graphics.BlurMaskFilter
import android.graphics.RectF
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.utils.PathUtils

@Composable
fun ShadeLight(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius:Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset:Float = 10f,
    convexity: Convexity = Convexity.CONVEX
){
    BoxWithConstraints(modifier = modifier) {
        Canvas(modifier = modifier.size(maxWidth,maxHeight) ){

            val paths = PathUtils.getPathFromDrawable(R.drawable.bulb)

            val matrix = android.graphics.Matrix()
            paths[0].transform(matrix)
            val bounds = RectF()
            paths[0].computeBounds(bounds,true)


            val pathWidth = bounds.width()
            val pathHeight = bounds.height()
            val isLandscape = pathWidth > pathHeight
            val scale = if (isLandscape){
                maxWidth.toPx() / pathWidth
            }else{
                maxHeight.toPx() / pathHeight
            }

            val scalePath = PathUtils.scalePath(paths[0],scale,scale).asComposePath()
            scalePath.translate(Offset(maxWidth.toPx()/2 - bounds.width() * scale / 2,0f))

            //浅色阴影画笔
            val paintShadowLight = Paint().also { paint: Paint ->
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
            val paintShadowDark = Paint().also { paint: Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }
            //深色阴影在光源方向的偏移量
            val backgroundShadowDarkOffset: Offset = when(LightSource.opposite(lightSource)){
                LightSource.LEFT_TOP -> Offset(-offset,-offset)
                LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
                LightSource.RIGHT_TOP -> Offset(offset, -offset)
                LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
                else -> {
                    Offset(0f,0f)
                }
            }


            //浅色阴影画笔
            val paintShadowLightConcave = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                        nativePaint.style = android.graphics.Paint.Style.STROKE
                        nativePaint.strokeWidth = offset
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }


            //浅色阴影在光源方向的偏移量
            val backgroundShadowLightOffsetConcave:Offset = when(LightSource.opposite(lightSource)){
                LightSource.LEFT_TOP -> Offset(offset,offset)
                LightSource.LEFT_BOTTOM -> Offset(offset,-offset)
                LightSource.RIGHT_TOP -> Offset(-offset, offset)
                LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
                else -> {Offset(0f,0f)}
            }

            //深色阴影画笔
            val paintShadowDarkConcave = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                        nativePaint.style = android.graphics.Paint.Style.STROKE
                        nativePaint.strokeWidth = offset //设置描边宽度
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }

            //深色阴影在光源方向的偏移量
            val backgroundShadowDarkOffsetConcave:Offset = when(lightSource){
                LightSource.LEFT_TOP -> Offset(offset,offset)
                LightSource.LEFT_BOTTOM -> Offset(offset,-offset)
                LightSource.RIGHT_TOP -> Offset(-offset, offset)
                LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
                else -> {Offset(0f,0f)}
            }


            drawIntoCanvas {
                if (convexity == Convexity.CONVEX){
                    //画布平移绘制浅色阴影
                    it.save()
                    it.translate(backgroundShadowLightOffset.x,backgroundShadowLightOffset.y)
                    it.drawPath(scalePath,paintShadowLight)

                    it.restore()
                    //画布平移绘制深色阴影
                    it.save()
                    it.translate(backgroundShadowDarkOffset.x,backgroundShadowDarkOffset.y)
                    it.drawPath(scalePath,paintShadowDark)
                    it.restore()

                    //绘制背景
                    drawPath(
                        path = scalePath,
                        color = backgroundColor,
                        style = Fill
                    )
                }else{
                    //绘制背景
                    drawPath(
                        path = scalePath,
                        color = backgroundColor,
                        style = Fill
                    )

                    //绘制深色阴影
                    it.save()
                    val pathShadowDark = Path().also { path ->
                        path.moveTo(0f, 0f)
                        path.addPath(scalePath,Offset(0f,0f))
                    }
                    it.clipPath(pathShadowDark)
                    it.translate(backgroundShadowDarkOffsetConcave.x,backgroundShadowDarkOffsetConcave.y)
                    it.drawPath(scalePath,paintShadowDarkConcave)
                    it.restore()
                    //绘制浅色阴影
                    it.save()
                    val pathShadowLight = Path().also { path ->
                        path.moveTo(0f, 0f)
                        path.addPath(scalePath,Offset(0f,0f))
                    }
                    it.clipPath(pathShadowLight)
                    it.translate(backgroundShadowLightOffsetConcave.x,backgroundShadowLightOffsetConcave.y)
                    it.drawPath(scalePath,paintShadowLightConcave)
                    it.restore()
                }

            }





        }

    }
}