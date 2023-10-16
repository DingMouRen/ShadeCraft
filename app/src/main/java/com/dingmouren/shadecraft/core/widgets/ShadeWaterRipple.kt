package com.dingmouren.shadecraft.core.widgets

import android.graphics.BlurMaskFilter
import android.os.Handler
import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import kotlinx.coroutines.delay
import kotlin.math.sqrt

@Composable
fun ShadeWaterRipple(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius: Float = 29f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 30f,
    borderWidth: Dp = 10.dp,
    size: Dp,
) {

    var click by remember { mutableStateOf(0) }

    Box(modifier = Modifier.size(size).clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) {
        click += 1
    }){
        if (click > 0){
            ShadeWater(
                modifier = modifier,
                size = size,
                initialValue = 0.dp
            )
        }
    }


}

@Composable
fun ShadeWater(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius: Float = 29f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 30f,
    borderWidth: Dp = 10.dp,
    size: Dp,
    initialValue:Dp
){
    val sizeCurrent by rememberInfiniteTransition().animateValue(
        initialValue = initialValue,
        targetValue = size,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(2 * 1000, easing = LinearEasing)
        )
    )

    var rippleAlpha by remember { mutableStateOf(1f) }
    LaunchedEffect(sizeCurrent) {
        rippleAlpha = 1f - sizeCurrent.value / size.value
    }

    Canvas(modifier = modifier.size(sizeCurrent)) {
        val startX = this.size.width / 2 - this.size.width / 2 / sqrt(2f)
        val startY = startX
        val endX = this.size.width - startX
        val endY = endX
        //浅色阴影画笔
        val paintShadowLight = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                    nativePaint.shader = LinearGradientShader(
                        Offset(startX, startY),
                        Offset(endX, endY),
                        mutableListOf(shadowColorLight, shadowColorLight.copy(alpha = 0f)),
                    )
                    if (offset > 0) nativePaint.maskFilter =
                        BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //浅色阴影在光源方向的偏移量
        val backgroundShadowLightOffset: Offset = when (lightSource) {
            LightSource.LEFT_TOP -> Offset(-offset, -offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset, offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {
                Offset(0f, 0f)
            }
        }
        //深色阴影画笔
        val paintShadowDark = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                    nativePaint.shader = LinearGradientShader(
                        Offset(startX, startY),
                        Offset(endX, endY),
                        mutableListOf(shadowColorDark.copy(alpha = 0f), shadowColorDark),
                    )
                    if (offset > 0) nativePaint.maskFilter =
                        BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //
        val paintShadow = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also { nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                    nativePaint.shader = LinearGradientShader(
                        Offset(startY, startY),
                        Offset(endX, endY),
                        mutableListOf(shadowColorDark, backgroundColor),
                    )
                    if (offset > 0) nativePaint.maskFilter =
                        BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //深色阴影在光源方向的偏移量
        val backgroundShadowDarkOffset: Offset = when (LightSource.opposite(lightSource)) {
            LightSource.LEFT_TOP -> Offset(-offset, -offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset, offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {
                Offset(0f, 0f)
            }
        }
        drawIntoCanvas {

            //画布平移绘制浅色阴影
            it.save()
            it.translate(backgroundShadowLightOffset.x, backgroundShadowLightOffset.y)
            paintShadowLight.style = PaintingStyle.Stroke
            paintShadowLight.strokeWidth = borderWidth.toPx()
            it.drawCircle(
                Offset(this.size.width / 2, this.size.height / 2),
                (this.size.width - borderWidth.toPx()) / 2,
                paintShadowLight.also { paint: Paint -> paint.alpha = rippleAlpha }
            )

            it.restore()
            //画布平移绘制深色阴影
            it.save()
            it.translate(backgroundShadowDarkOffset.x, backgroundShadowDarkOffset.y)
            paintShadowDark.style = PaintingStyle.Stroke
            paintShadowDark.strokeWidth = borderWidth.toPx()
            it.drawCircle(
                Offset(this.size.width / 2, this.size.height / 2),
                (this.size.width - borderWidth.toPx()) / 2,
                paintShadowDark.also { paint: Paint -> paint.alpha = rippleAlpha }
            )
            it.restore()
            //绘制没有位移的深色阴影
            it.save()
            paintShadow.style = PaintingStyle.Stroke
            paintShadow.strokeWidth = borderWidth.toPx()
            it.drawCircle(
                Offset(this.size.width / 2, this.size.height / 2),
                (this.size.width - borderWidth.toPx()) / 2,
                paintShadow.also { paint: Paint -> paint.alpha = rippleAlpha }
            )
            it.restore()
        }

    }
}