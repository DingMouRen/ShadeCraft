package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.widgets.ShadeCircleProgressConcave
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeCircleProgressConcavePage(){
    TitleSurface(title = "ShadeCircleProgressConcave") {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            ShadeCircleProgressConcave(
                progress = 40,
                progressColor = Color(0xff8769D5),
                size = 150.dp,
                borderWidth = 35.dp,
                backgroundColor = Color(0xffD4DAE7),
                progressBgColor = Color(0xff8769D5),
            )
        }
    }
}