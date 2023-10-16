package com.dingmouren.shadecraft.core.widgets

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.BlurProvider
import com.dingmouren.shadecraft.core.ConstantColor.NeumorphismLightBackgroundColor
import com.dingmouren.shadecraft.core.ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK
import com.dingmouren.shadecraft.core.ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.ext.backgroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeIconButton(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = BlurProvider.getDefaultBlurRadius(App.context()),
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 20f,
    cornerRadius: Dp = 0.dp,
    onClick: () -> Unit,
    backgroundColor: Color = NeumorphismLightBackgroundColor,
    size:Dp,
    imageVector: ImageVector,
    iconColor:Color = Color.Black,
    shape: Int = Shape.Rectangle,
    iconPadding:Dp = 0.dp

    ) {
    var currentOffset by remember { mutableStateOf(offset) }
    LaunchedEffect(offset) { // 使用LaunchedEffect监听offset的变化
        currentOffset = offset
    }
    var currentCornerRadius by remember { mutableStateOf(cornerRadius) }
    if (shape == Shape.Circle){
        currentCornerRadius = (size + size / 5 * 2)/2
    }

    Card(
        modifier = modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffset,
                currentCornerRadius,
            )
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        currentOffset = 0f

                    }
                    MotionEvent.ACTION_UP -> {
                        currentOffset = offset
                        onClick()
                    }
                }
                true
            },
        shape = RoundedCornerShape(currentCornerRadius),
        elevation = 0.dp
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .padding(size / 5)
        ) {
            Icon(imageVector = imageVector,
                contentDescription = "ShadeImageButton",
                modifier = Modifier.size(size).padding(iconPadding),
                tint = iconColor
            )
        }

    }

}

