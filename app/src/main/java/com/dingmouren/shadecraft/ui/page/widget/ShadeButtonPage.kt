package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.BlurProvider
import com.dingmouren.shadecraft.core.ConstantColor.backgroundColorOrange
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorDarkOrange
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorLightOrange
import com.dingmouren.shadecraft.core.widgets.ShadeButton
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeButtonPage() {
    TitleSurface(title = "ShadeButton") {
        ShadeButtonSample()
    }

}

@Composable
fun ShadeButtonSample() {
    val offset = remember {
        mutableStateOf(10f)
    }
    val cornerRadius = remember {
        mutableStateOf(0.dp)
    }
    val blurRadius = remember {
        mutableStateOf(8f)
    }
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(100.dp))
            ShadeButton(
                offset = offset.value,
                blurRadius = blurRadius.value,
                text = "Hello ShadeCraft",
                cornerRadius = cornerRadius.value,
                fontSize = 25.sp,
                onClick = {
                    App.context().toast("Hello Button")
                },
            )

            Spacer(Modifier.height(50.dp))

            ShadeButton(
                offset = offset.value,
                blurRadius = blurRadius.value,
                text = "Hello ShadeCraft",
                cornerRadius = cornerRadius.value,
                fontSize = 25.sp,
                onClick = {
                    App.context().toast("Hello Button")
                },
                textColor = Color.White,
                backgroundColor = backgroundColorOrange,
                shadowColorLight = shadowColorLightOrange,
                shadowColorDark = shadowColorDarkOrange
            )
            Spacer(Modifier.height(100.dp))

            ChangeOffset(offset)

            ChangeCornerRadius(cornerRadius)

            ChangeBlurRadius(blurRadius)
        }
    }
}

@Composable
fun ChangeBlurRadius(blurRadius: MutableState<Float>) {
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
fun ChangeCornerRadius(cornerRadius: MutableState<Dp>) {
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
fun ChangeOffset(offset: MutableState<Float>) {
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


