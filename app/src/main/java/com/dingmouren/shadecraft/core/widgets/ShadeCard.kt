package com.dingmouren.shadecraft.core.widgets

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeCard(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    onClick: () -> Unit,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    selectedEnable:Boolean = false,
    selected: Boolean = false,
    content: @Composable () -> Unit,
) {
    var selected by remember { mutableStateOf(selected) }
    var currentOffsetBack by remember { mutableStateOf(offset) }
    var currentOffsetFore by remember { mutableStateOf(0f) }
    LaunchedEffect(offset) { // 使用LaunchedEffect监听offset的变化
        if (selected){
            currentOffsetBack = 0f
            currentOffsetFore = offset
        }else{
            currentOffsetBack = offset
            currentOffsetFore = 0f
        }

    }

    Card(
        modifier = modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffsetFore,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffsetBack,
                cornerRadius,
            )
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (selectedEnable){
                            selected = !selected
                            if (selected) {
                                currentOffsetBack = 0f
                                currentOffsetFore = 22f
                            } else {
                                currentOffsetBack = 10f
                                currentOffsetFore = 0f
                            }
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        onClick()
                    }
                }
                true
            },
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            modifier
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            content()
        }

    }

}
