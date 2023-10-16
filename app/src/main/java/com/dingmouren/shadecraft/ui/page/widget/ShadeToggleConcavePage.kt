package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.widgets.ShadeToggle
import com.dingmouren.shadecraft.core.widgets.ShadeToggleConcave
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeToggleConcavePage(){
    TitleSurface(title = "ShadeToggleConcave") {
        Box(
            modifier = Modifier.fillMaxSize(),

        ) {
            ShadeToggleConcave(
                modifier = Modifier.align(Alignment.Center),
                width = 150.dp,
                height = 75.dp,
                onToggleChange = {
                    App.context().toast(if(it) "开" else "关")
                },
                blurRadius = 20f,
                offset = 15f
            )

        }
    }
}