package com.dingmouren.shadecraft.ui.page.widget

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.widgets.ShadeTabPathBar
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeTabPathBarPage(){

    val selectedTabIndex = remember { mutableStateOf(0) }

    TitleSurface(title = "ShaderTabPathBar") {
        Column(
            Modifier
                .fillMaxSize()
                .padding(30.dp),) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .height(50.dp), onClick = {selectedTabIndex.value = 0}){ Text(text = "")}
                Spacer(modifier = Modifier.width(10.dp))
                Button(modifier = Modifier
                    .weight(1f)
                    .height(50.dp), onClick = {selectedTabIndex.value = 1}){ Text(text = "")}
                Spacer(modifier = Modifier.width(10.dp))
                Button(modifier = Modifier
                    .weight(1f)
                    .height(50.dp), onClick = {selectedTabIndex.value = 2}){ Text(text = "")}
            }
            Spacer(modifier = Modifier.height(10.dp))
            ShadeTabPathBar(
                tabSize = 3,
                tabHeight = 60.dp,
                selectedIndex = selectedTabIndex.value,
                radiusSize = 20.dp,
                offset = 20f,
                blurRadius = 40f
            )
        }

    }
}