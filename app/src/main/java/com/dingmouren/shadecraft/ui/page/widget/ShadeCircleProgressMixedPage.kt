package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.widgets.ShadeCircleProgressMixed
import com.dingmouren.shadecraft.ui.page.sample.FatemeSouriSecondColor
import com.dingmouren.shadecraft.ui.page.sample.FatemeSouriTitleColor
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeCircleProgressMixedPage(){
    TitleSurface(title = "ShadeCircleProgressMixed") {

        Box(modifier = Modifier.fillMaxSize()){
            val title = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriTitleColor, fontSize = 20.sp)) {
                    append("895\n")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = FatemeSouriSecondColor, fontSize = 12.sp)) {
                    append("Calories")
                }
            }
            ShadeCircleProgressMixed(
                modifierOuter = Modifier.align(Alignment.Center).size(200.dp),
                borderWidthInner = 30.dp,
                sizeInner = 150.dp,
                backgroundColorInner = Color(0xffD4DAE7),
                progressColor = Color(0xff8769D5),
                progress = 70,
                text = title,
                cornerRadiusOuter = 100.dp,
                offsetOuter = 20f
            )
        }
    }
}