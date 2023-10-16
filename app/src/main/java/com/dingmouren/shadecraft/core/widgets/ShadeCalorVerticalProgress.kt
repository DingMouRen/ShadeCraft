package com.dingmouren.shadecraft.core.widgets

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@Composable
fun ShadeCalorVerticalProgress(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 20f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 15f,
    cornerRadius: Dp = 0.dp,
    progress: Int = 0,
    max :Int = 100,
    progressColor:Color,
    width:Dp,
    height:Dp
){

    val density = LocalDensity.current

    val heightPx = with(density){ height .toPx()}
    val progressPx = progress.toFloat() / max.toFloat() * heightPx
    val progressDp = with(density){progressPx.toDp()}
    Box(modifier = Modifier.wrapContentSize()){
        Card(
            modifier =
            if (progress == 0) {
                modifier.backgroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    offset,
                    cornerRadius,
                )
            } else {
                modifier.foregroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    offset,
                    cornerRadius,
                )
            },

            shape = RoundedCornerShape(cornerRadius),
            elevation = 0.dp
        ) {
            Box(modifier = Modifier
                .width(width)
                .height(height)){

            }

        }
        Card(
            shape = RoundedCornerShape(cornerRadius),
            elevation = 0.dp,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Box(modifier = Modifier
                .width(width)
                .height(progressDp)
                .background(progressColor)
            )
        }
    }

}