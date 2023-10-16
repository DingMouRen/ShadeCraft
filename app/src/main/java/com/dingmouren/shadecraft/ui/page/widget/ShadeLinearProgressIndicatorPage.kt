package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.Direction
import com.dingmouren.shadecraft.core.widgets.ShadeButton
import com.dingmouren.shadecraft.core.widgets.ShadeLinearProgressIndicator
import com.dingmouren.shadecraft.core.widgets.ShadeSlider
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeLinearProgressIndicatorPage() {
    var progress by remember { mutableStateOf(0f) }
    TitleSurface(title = "ShadeLinearProgressIndicator") {
        Box(modifier = Modifier.fillMaxSize()){
            Column(Modifier.wrapContentSize().align(Alignment.Center)) {
                Spacer(modifier = Modifier.height(30.dp))
                ShadeLinearProgressIndicator(
                    progressBarWidth = 16.dp,
                    maxProgress = 100f,
                    progress = progress,

                    )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    ShadeLinearProgressIndicator(
                        progressBarWidth = 16.dp,
                        maxProgress = 100f,
                        progress = progress,
                        direction = Direction.Vertical,
                        animation = false
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp),contentAlignment = Alignment.Center) {
                    ShadeButton(text = "Add",
                        onClick = {
                        progress += 20f },
                        offset = 20f, blurRadius = 20f
                    )
                }

            }
        }


    }
}