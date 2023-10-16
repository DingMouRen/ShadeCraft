package com.dingmouren.shadecraft.core.widgets

import android.graphics.BlurMaskFilter
import android.view.MotionEvent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeScaleOption(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius:Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset:Float = 5f,
    width : Float = 20f,
    scales : Int,
    shortLineLength:Float = 20f,
    distance:Float = 60f,
    circleRadius:Float = 30f,
    onScaleChange:(scale:Int)->Unit
){




    BoxWithConstraints(modifier = modifier) {
        val maxHeightPx = with(LocalDensity.current){maxHeight.toPx()}
        val interval = maxHeightPx / (scales + 1)


        val moveValue = remember { mutableStateOf(0f) }
        val upValue = remember { mutableStateOf(0f) }
        val motionCode = remember { mutableStateOf(1) }
        val resultValue = animateFloatAsState(
            targetValue = if((upValue.value / interval).toInt() == 0) interval else  (upValue.value / interval).toInt() * interval,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.DampingRatioNoBouncy
            )
        )


        Canvas(modifier = modifier
            .size(maxWidth,maxHeight)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        motionCode.value = MotionEvent.ACTION_DOWN
                    }
                    MotionEvent.ACTION_MOVE ->{
                        motionCode.value = MotionEvent.ACTION_MOVE
                        moveValue.value = if (it.y < 0) 0f else if (it.y > maxHeightPx) maxHeightPx else it.y

                    }
                    MotionEvent.ACTION_UP -> {
                        motionCode.value = MotionEvent.ACTION_UP
                        upValue.value = if (it.y < 0) 0f else if (it.y > maxHeightPx) maxHeightPx else it.y

                        val scaleCurrent = if((upValue.value / interval).toInt() <= 0) 0
                        else if ((upValue.value / interval).toInt() >= scales) scales -1
                        else  (upValue.value / interval).toDouble().roundToInt() - 1
                        onScaleChange.invoke(scaleCurrent)

                    }
                }
                true
            },

        ){
            val widthPx = maxWidth.toPx()
            val heightPx = maxHeight.toPx()
            val startX = widthPx/2
            val startY = 0f
            val endX = widthPx/2
            val endY = heightPx
            val interval = heightPx / (scales + 1)

            val left = widthPx/2 - width/2
            val right =  widthPx/2 + width/2
            val top = 0f
            val bottom = heightPx

            //浅色阴影画笔
            val paintShadowLightConcave = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                        nativePaint.style = android.graphics.Paint.Style.STROKE
                        nativePaint.strokeWidth = offset
                        nativePaint.strokeCap = android.graphics.Paint.Cap.ROUND
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }

            val paintShadowLightCircle = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                        nativePaint.style = android.graphics.Paint.Style.FILL
                        nativePaint.strokeWidth = offset
                        nativePaint.strokeCap = android.graphics.Paint.Cap.ROUND
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }


            //浅色阴影在光源方向的偏移量
            val backgroundShadowLightOffsetConcave: Offset = when(LightSource.opposite(lightSource)){
                LightSource.LEFT_TOP -> Offset(offset,offset)
                LightSource.LEFT_BOTTOM -> Offset(offset,-offset)
                LightSource.RIGHT_TOP -> Offset(-offset, offset)
                LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
                else -> {
                    Offset(0f,0f)
                }
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
                        nativePaint.strokeCap = android.graphics.Paint.Cap.ROUND
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }
            val paintShadowDarkCircle = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                    .also {nativePaint: NativePaint ->
                        nativePaint.isAntiAlias = true //设置抗锯齿
                        nativePaint.isDither = true //开启防抖
                        nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                        nativePaint.style = android.graphics.Paint.Style.FILL
                        nativePaint.strokeWidth = offset //设置描边宽度
                        nativePaint.strokeCap = android.graphics.Paint.Cap.ROUND
                        if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                    }
            }

            //深色阴影在光源方向的偏移量
            val backgroundShadowDarkOffsetConcave: Offset = when(lightSource){
                LightSource.LEFT_TOP -> Offset(offset,offset)
                LightSource.LEFT_BOTTOM -> Offset(offset,-offset)
                LightSource.RIGHT_TOP -> Offset(-offset, offset)
                LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
                else -> {
                    Offset(0f,0f)
                }
            }


            val pathLine = Path()
            pathLine.moveTo(startX,startY)
            pathLine.lineTo(endX,endY)

            val pathDark = Path()
            pathDark.moveTo(widthPx/2+width/2,0f)
            pathDark.lineTo(widthPx/2-width/2,0f)
            pathDark.lineTo(widthPx/2-width/2,heightPx)

            val pathLight = Path()
            pathLight.moveTo(widthPx/2+width/2,0f)
            pathLight.lineTo(widthPx/2+width/2,heightPx)
            pathLight.lineTo(widthPx/2-width/2,heightPx)

            drawIntoCanvas {
                //绘制竖线
                //绘制背景
                drawPath(
                    path = pathLine,
                    color = backgroundColor,
                    style = Stroke(width = width, cap = StrokeCap.Round)
                )
                //绘制深色阴影
                it.save()
                it.clipRect(left,top,right,bottom)
                it.translate(backgroundShadowDarkOffsetConcave.x/2,backgroundShadowDarkOffsetConcave.y/2)
                it.drawPath(pathDark,paintShadowDarkConcave)
                it.restore()
                //绘制浅色阴影
                it.save()
                it.clipRect(left,top,right,bottom)
                it.translate(backgroundShadowLightOffsetConcave.x/2,backgroundShadowLightOffsetConcave.y/2)
                it.drawPath(pathLight,paintShadowLightConcave)
                it.restore()
                //绘制刻度
                for (i in 1..scales){
                    drawLine(
                        color = shadowColorDark.copy(alpha = 0.5f),
                        start = Offset(widthPx/2 - distance,interval * i),
                        end = Offset(widthPx/2  - distance - shortLineLength,interval * i),
                        strokeWidth = width/2,
                        cap = StrokeCap.Round
                    )

                }
                //绘制圆形
                it.save()
                it.translate(backgroundShadowDarkOffsetConcave.x * 2,backgroundShadowDarkOffsetConcave.y * 2)
                it.drawCircle(
                    center = getScaleCircleCenterOffset(moveValue,upValue,widthPx,motionCode,interval,scales),
                    radius = circleRadius,
                    paint = paintShadowDarkCircle
                )
                it.restore()

                it.save()
                it.translate(backgroundShadowLightOffsetConcave.x * 2,backgroundShadowLightOffsetConcave.y * 2)
                it.drawCircle(
                    center = getScaleCircleCenterOffset(moveValue,upValue,widthPx,motionCode,interval,scales),
                    radius = circleRadius,
                    paint = paintShadowLightCircle
                )
                it.restore()

                drawCircle(
                    color = backgroundColor,
                    radius = circleRadius,
                    center = getScaleCircleCenterOffset(moveValue,upValue,widthPx,motionCode,interval,scales)
                )
            }

        }

    }
}

fun getScaleCircleCenterOffset(
    moveValue: MutableState<Float>,
    upValue: MutableState<Float>,
    widthPx: Float,
    motionCode: MutableState<Int>,
    interval: Float,
    scales: Int
): Offset {
    return  Offset(widthPx/2,if (motionCode.value == MotionEvent.ACTION_MOVE)
        moveValue.value
    else
        if((upValue.value / interval).toInt() <= 0) interval
        else if ((upValue.value / interval).toInt() >= scales) scales * interval
        else  (upValue.value / interval).toDouble().roundToInt() * interval
    )
}
