package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.widgets.ShadeLight
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeLightPage(){
    TitleSurface(title = "ShadeLight") {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            ShadeLight(
                modifier = Modifier
                    .size(300.dp),

            )
            Spacer(modifier = Modifier.height(20.dp))
            ShadeLight(
                modifier = Modifier
                    .size(300.dp),
                convexity =  Convexity.CONCAVE
            )
        }
    }
}