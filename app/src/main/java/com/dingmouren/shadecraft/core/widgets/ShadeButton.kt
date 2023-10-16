package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.BlurProvider
import com.dingmouren.shadecraft.core.ConstantColor.NeumorphismLightBackgroundColor
import com.dingmouren.shadecraft.core.ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK
import com.dingmouren.shadecraft.core.ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeButton(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    textColor:Color = Color.Black,
    onClick: () -> Unit,
    backgroundColor: Color = NeumorphismLightBackgroundColor,
    fontWeight: FontWeight? = null,
    width :Dp? = null,
    height:Dp? = null,
    convexity : Convexity = Convexity.CONVEX
) {
    var currentOffset by remember { mutableStateOf(offset) }
    LaunchedEffect(offset) { // 使用LaunchedEffect监听offset的变化
        currentOffset = offset
    }
    val TAG = "ShadeButton"

    Card(
        modifier = if (convexity == Convexity.CONVEX){
            modifier.backgroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    currentOffset,
                    cornerRadius,
                )
                .pointerInteropFilter {
                    Log.e(TAG,"pointerInteropFilter motionEvent:${it.action}")
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            currentOffset = 0f

                        }
                        MotionEvent.ACTION_UP -> {
                            currentOffset = offset
                            onClick()
                        }
                        MotionEvent.ACTION_CANCEL -> {
                            currentOffset = offset
                        }
                    }
                    true
                }
        } else {
            modifier.foregroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    currentOffset,
                    cornerRadius,
                ).backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffset,
                cornerRadius,
            )
        },
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            modifier = if (width != null && height != null) Modifier.background(backgroundColor).width(width).height(height)
            else Modifier.background(backgroundColor).padding(horizontal = 10.dp, vertical = 6.dp)

        ) {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = fontSize,
                fontStyle = fontStyle,
                color = textColor,
                fontWeight = fontWeight
                )
        }

    }

}
