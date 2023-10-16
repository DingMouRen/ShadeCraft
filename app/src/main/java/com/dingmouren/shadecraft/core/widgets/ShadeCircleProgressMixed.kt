package com.dingmouren.shadecraft.core.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.ui.page.sample.FatemeSouriPurple
import com.dingmouren.shadecraft.ui.page.sample.FatemeSouriSecondColor
import com.dingmouren.shadecraft.ui.page.sample.FatemeSouriTitleColor

@Composable
fun ShadeCircleProgressMixed(
    modifierOuter: Modifier = Modifier,
    shadowColorLightOuter: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDarkOuter: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadiusOuter: Float = 30f,
    lightSourceOuter: Int = LightSource.DEFAULT,
    offsetOuter: Float = 15f,
    cornerRadiusOuter: Dp = 0.dp,
    backgroundColorOuter: Color = ConstantColor.NeumorphismLightBackgroundColor,
    text: AnnotatedString? = null,
    borderWidthInner :Dp = 20.dp,
    sizeInner:Dp,
    backgroundColorInner: Color = ConstantColor.NeumorphismLightBackgroundColor,
    progressColor: Color,
    progress: Int,
){
    ShadeCard(
        modifier = modifierOuter,
        onClick = {  },
        cornerRadius = cornerRadiusOuter,
        blurRadius = blurRadiusOuter,
        offset = offsetOuter,
    ) {

        Box(modifier = Modifier.size(sizeInner)){
            ShadeCircleProgressConcave(
                modifier = Modifier.align(Alignment.Center),
                progress = progress,
                progressColor = progressColor,
                backgroundColor = backgroundColorInner,
                size = sizeInner,
                borderWidth = borderWidthInner,
                text = text
            )
        }
    }
}