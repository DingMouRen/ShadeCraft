package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioNoBouncy
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.Direction
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@Composable
fun ShadeLinearProgressIndicator(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    progressBarWidth: Dp = 6.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    progress: Float = 0f,
    maxProgress: Float = 100f,
    progressColor: Color = ConstantColor.backgroundColorOrange,
    shadowColorLightProgress: Color = ConstantColor.shadowColorLightOrange,
    shadowColorDarkProgress: Color = ConstantColor.shadowColorDarkOrange,
    direction: Direction = Direction.Horizontal,
    animation:Boolean = true
) {
    val TAG = "ShadeLinearProgressIndicator"

    var sliderWidth by remember {
        mutableStateOf(0f)
    }
    val currentProgress by animateFloatAsState(
        targetValue = progress / maxProgress * sliderWidth,
        animationSpec = spring(
            dampingRatio = if (animation) Spring.DampingRatioMediumBouncy else DampingRatioNoBouncy,
            stiffness =  Spring.StiffnessMedium
        )
    )

    val padding = 20.dp
    val paddingPx = with(LocalDensity.current) { padding.toPx() }
    if (direction == Direction.Horizontal) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding)
            .height(progressBarWidth * 5)
            .onGloballyPositioned { coordinates ->
                sliderWidth = coordinates.size.width.toFloat() - paddingPx
            }) {
            //进度条背景
            Card(
                modifier = Modifier
                    .foregroundShadow(
                        shadowColorLight,
                        shadowColorDark,
                        blurRadius,
                        lightSource,
                        offset,
                        progressBarWidth / 2,
                    )
                    .align(Alignment.Center),

                shape = RoundedCornerShape(progressBarWidth / 2),
                elevation = 0.dp
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(progressBarWidth)
                        .background(backgroundColor)
                )
            }

            //当前进度，并设定颜色颜色
            Card(
                Modifier
                    .align(Alignment.CenterStart)
                    .backgroundShadow(
                        shadowColorLightProgress,
                        shadowColorDarkProgress,
                        blurRadius,
                        lightSource,
                        offset / 5,
                        progressBarWidth / 2,
                    ),
                shape = RoundedCornerShape(progressBarWidth / 2),
            ) {
                Box(
                    Modifier
                        .width((currentProgress / LocalDensity.current.density).dp)
                        .height(progressBarWidth)
                        .background(progressColor),

                    )
            }

        }
    }else{
        Box(modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = padding)
            .width(progressBarWidth * 5)
            .onGloballyPositioned { coordinates ->
                sliderWidth = coordinates.size.height.toFloat() - paddingPx
            }) {
            //进度条背景
            Card(
                modifier = Modifier
                    .foregroundShadow(
                        shadowColorLight,
                        shadowColorDark,
                        blurRadius,
                        lightSource,
                        offset,
                        progressBarWidth / 2,
                    )
                    .align(Alignment.Center),

                shape = RoundedCornerShape(progressBarWidth / 2),
                elevation = 0.dp
            ) {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .width(progressBarWidth)
                        .background(backgroundColor)
                )
            }

            //当前进度，并设定颜色颜色
            Card(
                Modifier
                    .align(Alignment.BottomCenter)
                    .backgroundShadow(
                        shadowColorLightProgress,
                        shadowColorDarkProgress,
                        blurRadius,
                        lightSource,
                        offset / 5,
                        progressBarWidth / 2,
                    ),
                shape = RoundedCornerShape(progressBarWidth / 2),
            ) {
                Box(
                    Modifier
                        .height((currentProgress / LocalDensity.current.density).dp)
                        .width(progressBarWidth)
                        .background(progressColor),

                    )
            }

        }
    }

}