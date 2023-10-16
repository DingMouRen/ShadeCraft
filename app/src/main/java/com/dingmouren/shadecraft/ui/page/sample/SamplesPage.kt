package com.dingmouren.shadecraft.ui.page.sample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.widgets.ShadeButton
import com.dingmouren.shadecraft.route.LocalNavController
import com.dingmouren.shadecraft.route.RoutePath
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun SamplesPage(){
    TitleSurface(title = "Samples") {
        SamplesList()
    }
}

@Composable
fun SamplesList() {
    val navController = LocalNavController.current
    val widgetPageList = mutableListOf(
        RoutePath.MusicAlenaYurinaPage,
        RoutePath.CaskerSmartHomePage,
        RoutePath.DimestWashingMachinePage,
        RoutePath.FatemeSouriPage,
        RoutePath.KaylnKwanThermostatPage,
        RoutePath.KaylnKwanLightingPage,
        RoutePath.PierrePavlovicClockPage

    )
    Box(
        modifier = Modifier.fillMaxSize().padding(vertical = 30.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(widgetPageList) { item ->
                Column() {
                    Spacer(modifier = Modifier.height(15.dp))
                    ShadeButton(
                        text = item,
                        onClick = { navController.navigate(item) },
                        cornerRadius = 10.dp,
                        fontSize = 20.sp,
                        offset = 20f,
                        blurRadius = 20f,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }

            }
        }
    }
}
