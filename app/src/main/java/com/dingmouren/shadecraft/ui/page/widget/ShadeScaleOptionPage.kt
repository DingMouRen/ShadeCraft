package com.dingmouren.shadecraft.ui.page.widget

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.widgets.ShadeScaleOption
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeScaleOptionPage(){
    TitleSurface(title = "ShadeScaleOption") {
        Box(modifier = Modifier.fillMaxSize()){
            ShadeScaleOption(
                modifier = Modifier
                    .width(100.dp)
                    .height(380.dp)
                    .align(Alignment.Center),
                scales = 4,
                onScaleChange = {
                    Toast.makeText(App.context(),it.toString(),Toast.LENGTH_SHORT).show()
                }
            )

        }

    }
}