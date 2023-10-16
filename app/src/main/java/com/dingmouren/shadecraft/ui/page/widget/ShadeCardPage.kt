package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.widgets.ShadeCard
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeCardPage(){
    TitleSurface(title = "ShadeCard") {
        Box(
            modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)
        ) {
            ShadeCard(
                modifier = Modifier.align(Alignment.Center),
                onClick = { App.context().toast("Card Click") },
                offset = 20f,
                blurRadius = 20f
            ){
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_1),
                        contentDescription = "",
                        modifier = Modifier
                            .width(180.dp)
                            .height(101.15.dp)
                            .padding(horizontal = 8.dp)
                    )
                    Text(
                        text = "Dunhuang mural: graceful flying deity, golden wings, celestial bird.",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(0.dp,0.dp,5.dp,0.dp)
                    )
                }
            }

        }
    }
}