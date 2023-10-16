package com.dingmouren.shadecraft.ui.page.widget

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.widgets.ShadeRadioButton
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeRadioButtonPage() {

    val blurRadius = remember {
        mutableStateOf(8f)
    }
    val offset = remember {
        mutableStateOf(10f)
    }
    val cornerRadius = remember {
        mutableStateOf(0.dp)
    }
    val selectedPrivate = remember { mutableStateOf(false) }

    TitleSurface(title = "ShadeRadioButton") {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ShadeRadioButton(
                blurRadius = blurRadius.value,
                offset = offset.value,
                cornerRadius = cornerRadius.value,
                imageVector = Icons.Filled.Email,
                isVector = true,
                size = 50.dp,
                selected = selectedPrivate.value,
                onSelectedChanged = {
                    selectedPrivate.value = it
                    App.context().toast(if (it) "selected" else "unselected")
                },
            )
            Spacer(modifier = Modifier.height(150.dp))
            ChangeBlurRadiusRadioButtonPage(blurRadius)
            Spacer(modifier = Modifier.height(30.dp))
            ChangeOffsetRadioButtonPage(offset)
            Spacer(modifier = Modifier.height(30.dp))
            ChangeCornerRadiusRadioButtonPage(cornerRadius)

        }

    }
}

@Composable
fun ChangeCornerRadiusRadioButtonPage(cornerRadius: MutableState<Dp>) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = cornerRadius.value.value,
            onValueChange = {
                cornerRadius.value = it.dp
                Log.i("ShadeRadioButtonPage","it:$it offset:${cornerRadius.value}")
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
fun ChangeOffsetRadioButtonPage(offset: MutableState<Float>) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = offset.value,
            onValueChange = {
                offset.value = it
                Log.i("ShadeRadioButtonPage","it:$it offset:${offset.value}")
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
fun ChangeBlurRadiusRadioButtonPage(blurRadius: MutableState<Float>) {
    Row(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Slider(
            value = blurRadius.value,
            onValueChange = {
                blurRadius.value = it
                Log.i("ShadeRadioButtonPage","it:$it offset:${blurRadius.value}")
            },
            valueRange = 0.1f..20f,
            modifier = Modifier.weight(3f)

        )
        Text(
            text = "Blur:${blurRadius.value.toInt().toString()}",
            modifier = Modifier.weight(1f)
        )
    }
}