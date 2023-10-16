package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.widgets.ShadeIconButton
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeIconButtonPage() {
    TitleSurface(title = "ShadeIconButton") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            ShadeIconButton(
                onClick = {
                    App.context().toast("点击")
                },
                imageVector = Icons.Filled.Email,
                size = 50.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            ShadeIconButton(
                onClick = {
                    App.context().toast("点击")
                },
                imageVector = Icons.Filled.Phone,
                size = 50.dp,
                cornerRadius = 25.dp,
                iconColor = Color.Red
            )
            Spacer(modifier = Modifier.height(30.dp))
            ShadeIconButton(
                onClick = {
                    App.context().toast("点击")
                },
                imageVector = Icons.Filled.ShoppingCart,
                size = 50.dp,
                iconColor = Color.Red,
                shape = Shape.Circle,
                iconPadding = 5.dp
            )
        }
    }

}

