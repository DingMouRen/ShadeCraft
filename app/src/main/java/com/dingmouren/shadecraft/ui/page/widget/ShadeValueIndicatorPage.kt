package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.widgets.ShadeButton
import com.dingmouren.shadecraft.core.widgets.ShadeValueIndicator
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeValueIndicatorPage(){
    TitleSurface(title = "ShadeValueIndicator") {
        val size = 260.dp
        val num = remember { mutableStateOf(23) }
        Box(modifier = Modifier.fillMaxSize()){
            ShadeValueIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(size),
                cornerRadius = size / 2,
                borderWidth = size / 5,
                value = num.value,
                textStyle = TextStyle(fontSize = 35.sp, color = Color.Gray.copy(alpha = 0.6f)),
                min = 20,
                max = 50
            )

            Row(modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(40.dp)) {
                ShadeButton(
                    modifier = Modifier.weight(1f),
                    onClick = { num.value = if(num.value -1 < 20) 20 else num.value -1},
                    text = "-"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ShadeButton(
                    modifier = Modifier.weight(1f),
                    onClick = { num.value = if(num.value +1 > 50) 50 else num.value +1 },
                    text = "+"

                )
            }
        }
    }
}