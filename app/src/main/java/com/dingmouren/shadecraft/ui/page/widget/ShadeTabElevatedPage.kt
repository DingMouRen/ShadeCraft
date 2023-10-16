package com.dingmouren.shadecraft.ui.page.widget

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.widgets.ShadeTabElevated
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeTabElevatedPage(){
    TitleSurface(title = "ShadeTabElevated") {
        Box(modifier = Modifier.fillMaxSize()){
            ShadeTabElevated(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 20.dp),
                onSelectedChange = {

                }
            )
        }
    }
}