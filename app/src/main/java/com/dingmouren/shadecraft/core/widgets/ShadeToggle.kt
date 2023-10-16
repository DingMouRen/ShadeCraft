package com.dingmouren.shadecraft.core.widgets

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.Toggle
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@Composable
fun ShadeToggle(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    width: Dp = 60.dp,
    height: Dp = 30.dp,
    toggle:Toggle = Toggle.OFF,
    onToggleChange:(open:Boolean)->Unit,
    colorOn:Color =  Color(0xff1aef0b)
) {
    val TAG = "ShadeToggle"
    val borderWidth = height / 21 //描边宽度
    val innerWidth = width - borderWidth * 2 //内部布局宽度 dp
    val innerWidthPx =  with(LocalDensity.current){innerWidth.toPx()}//内部布局宽度 px
    val innerHeight = height - borderWidth * 2 ////内部布局高度
    val innerPadding = height / 7 + borderWidth //内部布局padding dp
    val innerPaddingPx = with(LocalDensity.current){innerPadding.toPx()}//内部布局padding px
    val circleButtonSize = height / 210 * 145 //圆形按钮大小 dp
    val circleButtonSizePx = with(LocalDensity.current){circleButtonSize.toPx()} //圆形按钮大小 px
    var currentToggle by remember { mutableStateOf(toggle) } //当前开关状态

    var currentTargetValue by remember { mutableStateOf(innerPaddingPx) }
    val offsetCircleButton by animateFloatAsState(
        targetValue = currentTargetValue,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    val innerBackgroundColor = remember { Animatable(backgroundColor) }

    LaunchedEffect(currentToggle) { // 使用LaunchedEffect监听offset的变化
        currentTargetValue = if (currentToggle == Toggle.OFF) innerPaddingPx else innerWidthPx - innerPaddingPx - circleButtonSizePx
        innerBackgroundColor.animateTo(if (currentToggle == Toggle.OFF) backgroundColor else colorOn)
    }

    Card(
        modifier = modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius*2,
                lightSource,
                offset,
                height / 2,
            )
            .clickable(//去除默认的水波纹效果
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                currentToggle = if (currentToggle == Toggle.OFF) Toggle.ON else Toggle.OFF
                onToggleChange.invoke(currentToggle == Toggle.ON)
            }
        ,

        shape = RoundedCornerShape(height / 2),
        elevation = 0.dp
    ) {
        Box(
            androidx.compose.ui.Modifier
                .background(backgroundColor)
                .size(width, height)
        ) {

            Card(
                modifier = Modifier
                    .foregroundShadow(
                        shadowColorLight,
                        shadowColorDark,
                        blurRadius,
                        lightSource,
                        offset,
                        innerHeight / 2,
                    )
                    .align(Alignment.Center),
                shape = RoundedCornerShape(innerHeight / 2),
                elevation = 0.dp
            ) {
                Box(
                    Modifier
                        .background(innerBackgroundColor.value)
                        .size(innerWidth, innerHeight)
                ) {
                    Card(
                        modifier = Modifier
                            .offset { IntOffset(offsetCircleButton.toInt(), 0) }
                            .backgroundShadow(
                                shadowColorLight,
                                shadowColorDark,
                                blurRadius,
                                lightSource,
                                offset/3,
                                circleButtonSize / 2,
                            )
                            .align(Alignment.CenterStart),
                        shape = RoundedCornerShape(circleButtonSize / 2),
                        elevation = 0.dp
                    ) {
                        Box(
                            Modifier
                                .background(backgroundColor)
                                .size(circleButtonSize, circleButtonSize)
                        ) {

                        }

                    }
                }

            }
        }

    }

}