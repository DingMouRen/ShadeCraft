package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorLightOrange
import com.dingmouren.shadecraft.core.widgets.ShadeToggle
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeTogglePage() {
    TitleSurface(title = "ShadeToggle") {
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                modifier = Modifier.wrapContentSize().align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                ShadeToggle(
                    width = 100.dp,
                    height = 50.dp,
                    onToggleChange = {
                        App.context().toast(if(it) "开" else "关")
                    },
                    blurRadius = 10f
                )
                Spacer(modifier = Modifier.height(30.dp))
                ShadeToggle(
                    width = 100.dp,
                    height = 50.dp,
                    colorOn = shadowColorLightOrange,
                    onToggleChange = {
                        App.context().toast(if(it) "开" else "关")
                    },
                    blurRadius = 10f
                )
            }
        }

    }
}