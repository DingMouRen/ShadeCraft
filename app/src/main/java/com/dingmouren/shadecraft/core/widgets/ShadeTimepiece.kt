package com.dingmouren.shadecraft.core.widgets

import android.graphics.BlurMaskFilter
import android.util.Log
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalDensity
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ShadeTimepiece(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 60f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 30f,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    hourMinuteColor:Color = Color.Red,
    secondColor:Color = Color.White
){
    
    
  
    BoxWithConstraints(modifier = modifier) {

        val maxWidthPx = with(LocalDensity.current){maxWidth.toPx()}
        val maxHeightPx = with(LocalDensity.current){maxHeight.toPx()}

        val sizeInnerForeground = maxWidthPx/8
        
        val offsetInnerForeground = sizeInnerForeground / 4
        
        val blurRadiusInnerForeground = 20f

        val secondLength = maxWidthPx / 4 / 8 * 9

        val minuteLength = maxWidthPx / 2 / 4 * 3

        val hourLength = maxWidthPx / 4

        val handWidth = 5f

        val paint = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = backgroundColor.toArgb() //设置画笔颜色
                }
        }

        //浅色阴影画笔
        val paintShadowLightForeground = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.strokeWidth = offsetInnerForeground
                    nativePaint.maskFilter = BlurMaskFilter(blurRadiusInnerForeground, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //浅色阴影在光源方向的偏移量
        val shadowLightOffsetForeground: Offset = when(LightSource.opposite(lightSource)){
            LightSource.LEFT_TOP -> Offset(offsetInnerForeground,offsetInnerForeground)
            LightSource.LEFT_BOTTOM -> Offset(offsetInnerForeground,-offsetInnerForeground)
            LightSource.RIGHT_TOP -> Offset(-offsetInnerForeground, offsetInnerForeground)
            LightSource.RIGHT_BOTTOM -> Offset(-offsetInnerForeground, -offsetInnerForeground)
            else -> {
                Offset(0f,0f)
            }
        }
        //深色阴影画笔
        val paintShadowDarkForeground = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.strokeWidth = offsetInnerForeground //设置描边宽度
                    nativePaint.maskFilter = BlurMaskFilter(blurRadiusInnerForeground, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //深色阴影在光源方向的偏移量
        val shadowDarkOffsetForeground: Offset = when(lightSource){
            LightSource.LEFT_TOP -> Offset(offsetInnerForeground,offsetInnerForeground)
            LightSource.LEFT_BOTTOM -> Offset(offsetInnerForeground,-offsetInnerForeground)
            LightSource.RIGHT_TOP -> Offset(-offsetInnerForeground, offsetInnerForeground)
            LightSource.RIGHT_BOTTOM -> Offset(-offsetInnerForeground, -offsetInnerForeground)
            else -> {
                Offset(0f,0f)
            }
        }

        //浅色阴影画笔
        val paintShadowLightBackground = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                    if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //浅色阴影在光源方向的偏移量
        val shadowLightOffsetBackground:Offset = when(lightSource){
            LightSource.LEFT_TOP -> Offset(-offset,-offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {Offset(0f,0f)}
        }
        //深色阴影画笔
        val paintShadowDarkBackground = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                    if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //深色阴影在光源方向的偏移量
        val shadowDarkOffsetBackground:Offset = when(LightSource.opposite(lightSource)){
            LightSource.LEFT_TOP -> Offset(-offset,-offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {Offset(0f,0f)}
        }

        var hourAngle  = remember { mutableStateOf(0f) }
        var minuteAngle = remember { mutableStateOf(0f) }
        var secondAngle = remember { mutableStateOf(0f) }


        LaunchedEffect(Unit) {
            while (true) {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)
                val second = calendar.get(Calendar.SECOND)
                val hourAngleTmp = (hour.toFloat() - 12) / 12f * 360f
                val minuteAngleTmp = minute.toFloat() / 60f * 360f
                val secondAngleTmp = second.toFloat() / 60f * 360f
                hourAngle.value = hourAngleTmp
                minuteAngle.value = minuteAngleTmp
                secondAngle.value = secondAngleTmp
                delay(1000)
            }
        }

        Canvas(modifier = modifier.size(maxWidth,maxHeight)){

            //绘制表盘
            drawIntoCanvas {
                //绘制浅色阴影
                it.save()
                it.translate(shadowLightOffsetBackground.x,shadowLightOffsetBackground.y)
                it.drawRoundRect(
                    0f,
                    0f,
                    this.size.width,
                    this.size.height,
                    this.size.width/2,
                    this.size.width/2,
                    paintShadowLightBackground
                )
                it.restore()
                //绘制深色阴影
                it.save()
                it.translate(shadowDarkOffsetBackground.x,shadowDarkOffsetBackground.y)
                it.drawRoundRect(
                    0f,
                    0f,
                    this.size.width,
                    this.size.height,
                    this.size.width/2,
                    this.size.width/2,
                    paintShadowDarkBackground
                )
                it.restore()
                //绘制背景色表盘
                it.drawRoundRect(
                    0f,
                    0f,
                    this.size.width,
                    this.size.height,
                    this.size.width/2,
                    this.size.width/2,
                    paint
                )
            }
            //绘制轴心圆
            drawIntoCanvas {
                val left = this.size.width/2 - sizeInnerForeground/2
                val top = this.size.height/2 - sizeInnerForeground/2
                val right = this.size.width/2 + sizeInnerForeground/2
                val bottom = this.size.height/2 + sizeInnerForeground/2


                //绘制轴心圆
                it.drawRoundRect(
                    left,
                    top,
                    right,
                    bottom,
                    sizeInnerForeground/2,
                    sizeInnerForeground/2,
                    paint
                )

                //绘制深色阴影
                it.save()
                val pathShadowDark = Path().also { path ->
                    path.addRoundRect(RoundRect(left, top, right , bottom, sizeInnerForeground / 2, sizeInnerForeground / 2))
                }
                it.clipPath(pathShadowDark)
                it.translate(shadowDarkOffsetForeground.x/7*2,shadowDarkOffsetForeground.y/7*2)
                it.drawRoundRect(
                    left,
                    top,
                    right ,
                    bottom ,
                    sizeInnerForeground / 2,
                    sizeInnerForeground / 2,
                    paintShadowDarkForeground
                )
                it.restore()
                //绘制浅色阴影
                it.save()
                val pathShadowLight = Path().also { path ->
                    path.addRoundRect(RoundRect(left, top, right , bottom, sizeInnerForeground / 2, sizeInnerForeground / 2))
                }
                it.clipPath(pathShadowLight)
                it.translate(shadowLightOffsetForeground.x/7*2,shadowLightOffsetForeground.y/7*2)
                it.drawRoundRect(
                    left,
                    top,
                    right ,
                    bottom ,
                    sizeInnerForeground / 2,
                    sizeInnerForeground / 2,
                    paintShadowLightForeground
                )
                it.restore()


            }
            //绘制时针、分针、秒针
            drawIntoCanvas {

                //绘制时针
                drawLine(
                    color = hourMinuteColor,
                    start = Offset(maxWidthPx/2 ,maxWidthPx/2),
                    end = calculateHandPositionForTimepiece(maxWidthPx/2 ,maxWidthPx/2,hourLength,hourAngle.value),
                    strokeWidth = handWidth,
                )
                //绘制分针
                drawLine(
                    color = hourMinuteColor,
                    start = Offset(maxWidthPx/2 ,maxWidthPx/2),
                    end = calculateHandPositionForTimepiece(maxWidthPx/2 ,maxWidthPx/2,minuteLength,minuteAngle.value),
                    strokeWidth = handWidth,
                )
                //绘制秒针
                drawLine(
                    color = secondColor,
                    start = Offset(maxWidthPx/2 ,maxHeightPx/2),
                    end = calculateHandPositionForTimepiece(maxWidthPx/2 ,maxWidthPx/2,secondLength,secondAngle.value),
                    strokeWidth = handWidth,
                )

            }
        }

    }

}

private fun calculateHandPositionForTimepiece(centerX: Float, centerY: Float, length: Float, angleDegrees: Float): Offset {
    val angleRadians = Math.toRadians(angleDegrees.toDouble())
    val x = (centerX + length * sin(angleRadians)).toFloat()
    val y = (centerY - length * cos(angleRadians)).toFloat()
    return Offset(x, y)
}