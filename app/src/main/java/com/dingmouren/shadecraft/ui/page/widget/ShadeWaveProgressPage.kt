package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.widgets.ShadeWaveProgress
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeWaveProgressPage(){
    TitleSurface(title = "ShadeWaveProgress") {
       Box(modifier = Modifier.fillMaxSize(),
           contentAlignment = Alignment.Center
       ){
           ShadeWaveProgress(
               width = 80.dp,
               height = 500.dp,
               cornerRadius = 40.dp,
               colors = mutableListOf(
                   Color(0xffFAC20B),
                   Color(0xffF58C08),
                   Color(0xffBD56B1),
                   Color(0xff9949CE),
                   Color(0xff5E54E6),
               ),
               progress = 50f,
               maxProgress = 100f,
               onProgress = {
                   Log.e("ShadeWaveProgressPage","progress:$it")
               },
               blurRadius = 30f
           )
       }
    }
}