package com.dingmouren.shadecraft.ui.page.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
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
fun WidgetsPage() {
    TitleSurface(title = "Widgets") {
        WidgetList()
    }


}

@Composable
fun WidgetList() {
    val navController = LocalNavController.current
    val widgetPageList = mutableListOf(
        RoutePath.ShadeButtonPage,
        RoutePath.ShadeTextPage,
        RoutePath.ShadeIconButtonPage,
        RoutePath.ShadeRadioButtonPage,
        RoutePath.ShadeCardPage,
        RoutePath.ShadeSliderPage,
        RoutePath.ShadeSearchBarPage,
        RoutePath.ShadeTogglePage,
        RoutePath.ShadeTabRowPage,
        RoutePath.ShadeLinearProgressIndicatorPage,
        RoutePath.ShadeCircleProgressConvexPage,
        RoutePath.ShadeCircleProgressConcavePage,
        RoutePath.ShadeCircleProgressMixedPage,
        RoutePath.ShadeTabPathBarPage,
        RoutePath.ShadeToggleConcavePage,
        RoutePath.ShadeWaterRipplePage,
        RoutePath.ShadeWaveProgressPage,
        RoutePath.ShadeCalorVerticalProgressPage,
        RoutePath.ShadeValueIndicatorPage,
        RoutePath.ShadeLightPage,
        RoutePath.ShadeScaleOptionPage,
        RoutePath.ShadeTimepiecePage,
        RoutePath.ShadeTabElevatedPage,
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
