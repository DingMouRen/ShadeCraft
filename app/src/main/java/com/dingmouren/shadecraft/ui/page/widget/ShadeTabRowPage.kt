package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor.backgroundColorOrange
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorDarkOrange
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorLightOrange
import com.dingmouren.shadecraft.core.widgets.ShadeTab
import com.dingmouren.shadecraft.core.widgets.ShadeTabRow
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeTabRowPage() {
    val selectedTabIndex = remember { mutableStateOf(0) }
    val selectedBottomTabIndex = remember { mutableStateOf(0) }

    TitleSurface(title = "ShadeTabRow") {
        Box(Modifier.fillMaxSize()){
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentSize()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                ShadeTabRow(
                    selectedTabIndex = selectedTabIndex.value,
                ) {
                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 0 },
                    ) {
                        Text(text = "Tab 1")
                    }

                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 1 }
                    ) {
                        Text(text = "Tab 2")
                    }
                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 2 }
                    ) {
                        Text(text = "Tab 3")
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                ShadeTabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    cornerRadius = 38.dp
                ) {
                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 0 }
                    ) {
                        Text(text = "Tab 1")
                    }

                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 1 }
                    ) {
                        Text(text = "Tab 2")
                    }
                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 2 }
                    ) {
                        Text(text = "Tab 3")
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                ShadeTabRow(
                    selectedTabIndex = selectedTabIndex.value,
                    cornerRadius = 38.dp,
                    sliderColor = backgroundColorOrange,
                    shadowColorLightSlider = shadowColorLightOrange,
                    shadowColorDarkSlider = shadowColorDarkOrange
                ) {
                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 0 }
                    ) {
                        Text(text = "Tab 1", color = if (selectedTabIndex.value == 0) Color.White else Color.Black)
                    }

                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 1 }
                    ) {
                        Text(text = "Tab 2", color = if (selectedTabIndex.value == 1) Color.White else Color.Black)
                    }
                    ShadeTab(
                        onSelected = {  selectedTabIndex.value = 2 }
                    ) {
                        Text(text = "Tab 3", color = if (selectedTabIndex.value == 2) Color.White else Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                ShadeTabRow(
                    selectedTabIndex = selectedBottomTabIndex.value,
                    cornerRadius = 8.dp
                ) {
                    ShadeTab(
                        onSelected = {  selectedBottomTabIndex.value = 0 }
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = null)
                    }

                    ShadeTab(
                        onSelected = {  selectedBottomTabIndex.value = 1 }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                    ShadeTab(
                        onSelected = {  selectedBottomTabIndex.value = 2 }
                    ) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
                    }
                    ShadeTab(
                        onSelected = {  selectedBottomTabIndex.value = 3 }
                    ) {
                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                    }
                    ShadeTab(
                        onSelected = {  selectedBottomTabIndex.value = 4 }
                    ) {
                        Icon(imageVector = Icons.Default.Face, contentDescription = null)
                    }
                }
            }
        }

    }
}