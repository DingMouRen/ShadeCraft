package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.widgets.ShadeTimepiece
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeTimepiecePage(){
    TitleSurface(title = "ShadeTimepiece") {
        Box(modifier = Modifier.fillMaxSize()){
            ShadeTimepiece(
                modifier = Modifier.size(300.dp)
                    .align(Alignment.Center)
            )
        }
    }
}