package com.dingmouren.shadecraft.core.widgets

import android.graphics.Typeface
import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow
import kotlin.random.Random


@Composable
fun ShadeWaveProgress(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    width :Dp,
    height:Dp,
    colors: List<Color>,
    progress:Float = 0f,
    maxProgress:Float = height.value * LocalDensity.current.density,
    onProgress:(progress:Float)->Unit
){
    val density = LocalDensity.current.density
    val widthPx = width.value * density
    val heightPx = height.value * density
    val levelLine =  remember { mutableStateOf((maxProgress - progress)/maxProgress * heightPx) }
    Card(
        modifier = modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .width(width)
                .height(height)
                .pointerInput(Unit){
                    detectVerticalDragGestures { change, _ ->
                        levelLine.value =  change.position.y
                        var progressCallback = (heightPx -levelLine.value)/heightPx * maxProgress
                        if (progressCallback < 0f){
                            progressCallback = 0f
                        }else if(progressCallback > maxProgress){
                            progressCallback = maxProgress
                        }

                        onProgress.invoke(progressCallback)
                    }
                }
        ) {



            var waveHeight = 30f //波浪起伏幅度
            var waveWidth = widthPx / 1.2f //波浪长度
            var waveWidthBelow = widthPx  //波浪长度
            var abovePath  by remember { mutableStateOf(Path()) }
            var belowPath  by remember { mutableStateOf(Path()) }
            val firstColor = colors[0]


            val animateValue by rememberInfiniteTransition().animateFloat(  // 创建一个无限循环的浮点数动画
                initialValue = 0f,  // 动画的初始值
                targetValue = waveWidth,  // 动画的目标值
                animationSpec = infiniteRepeatable(  // 动画的配置参数，默认为无限循环的动画效果
                    animation = tween(
                        durationMillis = 1000,  // 动画的持续时间，单位为毫秒
                        easing = CubicBezierEasing(0.2f, 0.2f, 0.7f, 0.9f)  // 动画的缓动效果
                    ),
                    repeatMode = RepeatMode.Restart  // 动画的重复模式
                )
            )
            val animateValueBelow by rememberInfiniteTransition().animateFloat(  // 创建一个无限循环的浮点数动画
                initialValue = waveWidthBelow,  // 动画的初始值
                targetValue = 0f,  // 动画的目标值
                animationSpec = infiniteRepeatable(  // 动画的配置参数，默认为无限循环的动画效果
                    animation = tween(
                        durationMillis = 1000,  // 动画的持续时间，单位为毫秒
                        easing = CubicBezierEasing(0.2f, 0.2f, 0.7f, 0.9f)  // 动画的缓动效果
                    ),
                    repeatMode = RepeatMode.Restart  // 动画的重复模式
                )
            )


            Canvas(modifier = Modifier.fillMaxSize() ){
                if (levelLine.value == heightPx)return@Canvas
                belowPath.reset()
                belowPath.moveTo(-waveWidthBelow  + animateValueBelow, levelLine.value)  // 移动到初始点的位置
                var i = -waveWidthBelow
                while (i < widthPx + waveWidthBelow) {  // 循环绘制波浪的路径
                    belowPath.relativeQuadraticBezierTo(waveWidthBelow / 4f, -waveHeight, waveWidthBelow / 2f, 0f)
                    belowPath.relativeQuadraticBezierTo(waveWidthBelow / 4f, waveHeight, waveWidthBelow / 2f, 0f)
                    i += waveWidthBelow
                }
                belowPath.lineTo(this.size.width,this.size.height)
                belowPath.lineTo(0f,this.size.height)
                belowPath.close()
                drawPath(
                    path = belowPath,
                    color = firstColor.copy(alpha = 0.5f)
                )

                //上面的波浪
                abovePath.reset()
                abovePath.moveTo(-waveWidth + animateValue, levelLine.value)  // 移动到初始点的位置
                var j = -waveWidth
                while (j < widthPx + waveWidth) {  // 循环绘制波浪的路径
                    abovePath.relativeQuadraticBezierTo(waveWidth / 4f, -waveHeight, waveWidth / 2f, 0f)
                    abovePath.relativeQuadraticBezierTo(waveWidth / 4f, waveHeight, waveWidth / 2f, 0f)
                    j += waveWidth
                }
                abovePath.lineTo(this.size.width,this.size.height)
                abovePath.lineTo(0f,this.size.height)
                abovePath.close()
                drawPath(
                    path = abovePath,
                    brush = Brush.linearGradient(
                        colors = colors,
                        start = Offset(0f,  levelLine.value), // 起点坐标
                        end = Offset(0f, size.height), // 终点坐标
                    )
                )


            }
        }

    }

}

