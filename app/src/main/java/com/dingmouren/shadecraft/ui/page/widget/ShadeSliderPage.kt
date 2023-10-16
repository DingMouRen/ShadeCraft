package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.widgets.ShadeSlider
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeSliderPage(){

    val offset = remember {
        mutableStateOf(10f)
    }

    val blurRadius = remember {
        mutableStateOf(8f)
    }

    var progressBarHeight = remember {
        mutableStateOf(8.dp)
    }


    TitleSurface(title = "ShadeSlider") {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            ShadeSlider(
                offset = offset.value,
                blurRadius = blurRadius.value,
                progressBarHeight = progressBarHeight.value,
                onProgress = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            ShadeSlider(
                offset = offset.value,
                blurRadius = blurRadius.value,
                progressBarHeight = progressBarHeight.value,
                progressColor = Color.Yellow,
                maxProgress = 1000f,
                progress = 200f,
                onProgress = {
                    Log.e("ShadeSliderPage","progress:$it")
                }
            )

        }
    }
}



@Composable
fun ChangeOffsetSliderPage(offset: MutableState<Float>) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = offset.value,
            onValueChange = {
                offset.value = it
                Log.i("ShadeSliderPage","it:$it offset:${offset.value}")
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

@Composable
fun ChangeBlurRadiusSliderPage(blurRadius: MutableState<Float>) {
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = blurRadius.value,
            onValueChange = {
                blurRadius.value = it
                Log.i("ShadeSliderPage","it:$it blur:${blurRadius.value}")
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
fun ChangeCornerRadiusSliderPage(cornerRadius: MutableState<Dp>) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = cornerRadius.value.value,
            onValueChange = {
                cornerRadius.value = it.dp
                Log.i("ShadeSliderPage","it:$it corner:${cornerRadius.value}")
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
