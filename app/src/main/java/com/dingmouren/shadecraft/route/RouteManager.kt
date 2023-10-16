package com.dingmouren.shadecraft.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dingmouren.shadecraft.ui.page.MainPage
import com.dingmouren.shadecraft.ui.page.TestPage
import com.dingmouren.shadecraft.ui.page.sample.*
import com.dingmouren.shadecraft.ui.page.widget.*

//提供全局路由跳转
val LocalNavController = compositionLocalOf <NavController>{ error("No NavController provided") }


@Composable
fun RouteManager(){

    val routeController = rememberNavController()


    CompositionLocalProvider(
        LocalNavController provides routeController ,
    ) {
        NavHost(
            navController = routeController,
            startDestination = RoutePath.Main){
            composable(RoutePath.Main){
                MainPage()
            }
            composable(RoutePath.SamplesPage){
                SamplesPage()
            }
            composable(RoutePath.TestPage){
                TestPage()
            }

            composable(RoutePath.MusicAlenaYurinaPage){
                MusicAlenaYurinaPage()
            }
            composable(
                RoutePath.MusicAlenaYurinaPlayingPage,
                arguments = listOf(
                    navArgument("musicIndex"){
                        type = NavType.StringType
                        defaultValue = "0"
                        nullable = false
                    }
                )
            ){
                val musicIndex = it.arguments?.getString("musicIndex")
                musicIndex?.let { index -> MusicAlenaYurinaPlayingPage(index.toInt()) }
            }
            composable(RoutePath.CaskerSmartHomePage){
                CaskerSmartHomePage()
            }
            composable(RoutePath.CaskerLivingRoomPage){
                CaskerLivingRoomPage()
            }
            composable(RoutePath.DimestWashingMachinePage){
                DimestWashingMachinePage()
            }
            composable(RoutePath.FatemeSouriPage){
                FatemeSouriPage()
            }
            composable(RoutePath.KaylnKwanThermostatPage){
                KaylnKwanThermostatPage()
            }
            composable(RoutePath.KaylnKwanLightingPage){
                KaylnKwanLightingPage()
            }
            composable(RoutePath.PierrePavlovicClockPage){
                PierrePavlovicClockPage()
            }
            // - - - -- - - - - - - - - - - - - - - - - - - - - - -
            composable(RoutePath.WidgetsPage){
                WidgetsPage()
            }
            composable(RoutePath.ShadeButtonPage){
                ShadeButtonPage()
            }
            composable(RoutePath.ShadeTextPage){
                ShadeTextPage()
            }
            composable(RoutePath.ShadeIconButtonPage){
                ShadeIconButtonPage()
            }
            composable(RoutePath.ShadeRadioButtonPage){
                ShadeRadioButtonPage()
            }
            composable(RoutePath.ShadeCardPage){
                ShadeCardPage()
            }
            composable(RoutePath.ShadeSliderPage){
                ShadeSliderPage()
            }
            composable(RoutePath.ShadeSearchBarPage){
                ShadeSearchBarPage()
            }
            composable(RoutePath.ShadeTogglePage){
                ShadeTogglePage()
            }
            composable(RoutePath.ShadeTabRowPage){
                ShadeTabRowPage()
            }
            composable(RoutePath.ShadeLinearProgressIndicatorPage){
                ShadeLinearProgressIndicatorPage()
            }
            composable(RoutePath.ShadeCircleProgressConvexPage){
                ShadeCircleProgressConvexPage()
            }
            composable(RoutePath.ShadeTabPathBarPage){
                ShadeTabPathBarPage()
            }
            composable(RoutePath.ShadeToggleConcavePage){
                ShadeToggleConcavePage()
            }
            composable(RoutePath.ShadeWaterRipplePage){
                ShadeWaterRipplePage()
            }
            composable(RoutePath.ShadeWaveProgressPage){
                ShadeWaveProgressPage()
            }
            composable(RoutePath.ShadeCircleProgressConcavePage){
                ShadeCircleProgressConcavePage()
            }
            composable(RoutePath.ShadeCalorVerticalProgressPage){
                ShadeCalorVerticalProgressPage()
            }
            composable(RoutePath.ShadeValueIndicatorPage){
                ShadeValueIndicatorPage()
            }
            composable(RoutePath.ShadeCircleProgressMixedPage){
                ShadeCircleProgressMixedPage()
            }
            composable(RoutePath.ShadeLightPage){
                ShadeLightPage()
            }
            composable(RoutePath.ShadeScaleOptionPage){
                ShadeScaleOptionPage()
            }
            composable(RoutePath.ShadeTimepiecePage){
                ShadeTimepiecePage()
            }
            composable(RoutePath.ShadeTabElevatedPage){
                ShadeTabElevatedPage()
            }

        }
    }

}