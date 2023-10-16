package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.widgets.ShadeWaterRipple
import com.dingmouren.shadecraft.ui.widget.TitleSurface
import java.util.logging.Handler

@Composable
fun ShadeWaterRipplePage(){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val offset = remember {
        mutableStateOf(10f)
    }

    val blurRadius = remember {
        mutableStateOf(1f)
    }
    val borderWidth = remember {
        mutableStateOf(20.dp)
    }



    TitleSurface(title = "ShadeWaterRipple") {
        Box(modifier = Modifier.fillMaxSize()){
            Box(modifier = Modifier.size(screenWidthDp).align(Alignment.Center)){

                ShadeWaterRipple(
                    modifier = Modifier
                        .align(Alignment.Center),
                    size = screenWidthDp,

                    )

            }
        }


//
//        ChangeOffsetShadeWaterRipplePage(offset)
//
//        ChangeBorderWidthShadeWaterRipplePage(borderWidth)
//
//        ChangeBlurRadiusShadeWaterRipplePage(blurRadius)
    }
}


@Composable
fun ChangeBlurRadiusShadeWaterRipplePage(blurRadius: MutableState<Float>) {
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = blurRadius.value,
            onValueChange = {
                blurRadius.value = it
                Log.i("ShadeButtonPage","it:$it offset:${blurRadius.value}")
            },
            valueRange = 0.1f..100f,
            modifier = Modifier.weight(3f)

        )
        Text(
            text = "Blur:${blurRadius.value.toInt().toString()}",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ChangeBorderWidthShadeWaterRipplePage(cornerRadius: MutableState<Dp>) {
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = cornerRadius.value.value,
            onValueChange = {
                cornerRadius.value = it.dp
                Log.i("ShadeButtonPage","it:$it offset:${cornerRadius.value}")
            },
            valueRange = 0f..100f,
            modifier = Modifier.weight(3f)

        )
        Text(
            text = "Corner:${cornerRadius.value.value.toInt().toString()}",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ChangeOffsetShadeWaterRipplePage(offset: MutableState<Float>) {
    Spacer(Modifier.height(50.dp))
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = offset.value,
            onValueChange = {
                offset.value = it
                Log.i("ShadeButtonPage","it:$it offset:${offset.value}")
            },
            valueRange = 0f..100f,
            modifier = Modifier.weight(3f)

        )
        Text(
            text = "Offset:${offset.value.toInt().toString()}",
            modifier = Modifier.weight(1f)
        )
    }


}
