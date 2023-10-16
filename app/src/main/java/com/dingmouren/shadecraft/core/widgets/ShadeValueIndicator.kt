package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow
import com.dingmouren.shadecraft.ui.page.sample.DimestTitleColor

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ShadeValueIndicator(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 50f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 50f,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    borderWidth:Dp = 30.dp,
    value:Int,
    textStyle: TextStyle,
    min:Int,
    max:Int,
){
    var lastNum = remember { mutableStateOf(0) }
    var currentNum = value
    val lineLength = with(LocalDensity.current){(borderWidth/3).toPx()}
    val padding = with(LocalDensity.current){(borderWidth/3).toPx()}
    if(currentNum > max) {
        currentNum = max
    }

    val rotationAngle =  animateFloatAsState(
        targetValue = (currentNum.toFloat() - min) / (max - min) * 360.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy ,
            stiffness =  Spring.StiffnessLow
        )
    )

    Card(
        modifier = modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            )
           ,
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            modifier
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            BoxWithConstraints(modifier = modifier) {
                Canvas(modifier = Modifier.size(maxWidth,maxHeight)){

                    rotate(rotationAngle.value, pivot = Offset(this.size.width / 2, this.size.height / 2)) {
                        drawLine(
                            color = shadowColorDark,
                            start = Offset(this.size.width / 2, padding),
                            end = Offset(this.size.width / 2, padding + lineLength),
                            strokeWidth = 20f,
                            cap = StrokeCap.Round
                        )
                    }

                }
            }

            Card(
                modifier = Modifier
                    .padding(borderWidth)
                    .foregroundShadow(
                        shadowColorLight,
                        shadowColorDark,
                        blurRadius,
                        lightSource,
                        offset * 2,
                        cornerRadius,
                    )
                ,
                shape = RoundedCornerShape(cornerRadius),
                elevation = 0.dp
            ) {
                Box(
                    modifier
                        .background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedContent(
                        targetState = currentNum,
                        transitionSpec = if (lastNum.value > currentNum) {
                            {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Up) with
                                        fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Up)
                            }
                        }else if (lastNum.value < currentNum){
                            {
                                slideIntoContainer(AnimatedContentScope.SlideDirection.Down) with
                                        fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Down)
                            }
                        }else {
                            {
                                fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                                        scaleIn(initialScale = 0.92f, animationSpec = tween(220, delayMillis = 90)) with
                                        fadeOut(animationSpec = tween(90))
                            }
                        }

                    ) {
                        lastNum.value = currentNum
                        Text(
                            text = "$it°",
                            style = textStyle
                        )

                    }
                }

            }
        }

    }
}