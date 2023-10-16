package com.dingmouren.shadecraft.ui.page.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.widgets.ShadeButton
import com.dingmouren.shadecraft.core.widgets.ShadeRadioButton
import com.dingmouren.shadecraft.core.widgets.ShadeValueIndicator

val KaylnKwanTitleColor = Color(0xff9C9C9C)
val KaylnKwanSubColor = Color(0xffC0C0C0)
val KaylnKwanYellowColor = Color(0xffF2F2A8)
val KaylnKwanOrageColor = Color(0xffF0D0B3)
val KaylnKwanBlueColor = Color(0xffCDDDE6)
val KaylnKwanPinkColor = Color(0xffE9D0E3)
val KaylnKwanIconColor = Color(0xffC4C4C4)

@Composable
fun KaylnKwanThermostatPage(){
    val num = remember { mutableStateOf(23) }
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            KaylnKwanThermostatTopBar()
            KaylnKwanThermostatInfo()
            KaylnKwanThermostatIndicators(num)
            KaylnKwanThermostatBottomBar(num)
        }
    }
}

@Composable
fun KaylnKwanThermostatTopBar() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(KaylnKwanTitleColor),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "General settings",
            color = KaylnKwanTitleColor
        )
    }

}

@Composable
fun KaylnKwanThermostatInfo() {
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "Thermostat",
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        color = KaylnKwanTitleColor
    )
    Text(
        text = "Upstairs Bathroom",
        fontSize = 16.sp,
        color = KaylnKwanSubColor
    )
    Spacer(modifier = Modifier.height(25.dp))
    Row(modifier = Modifier
        .wrapContentSize()
    ) {
        Column(modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
            ) {
            Text(
                text = "Humidity",
                fontSize = 20.sp,
                color = KaylnKwanSubColor
            )
            Text(
                text = "49%",
                fontSize = 22.sp,
                color = KaylnKwanTitleColor
            )
        }
        Spacer(modifier = Modifier.width(50.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Indoor",
                fontSize = 20.sp,
                color = KaylnKwanSubColor
            )
            Text(
                text = "85",
                fontSize = 22.sp,
                color = KaylnKwanTitleColor
            )
        }
    }

}

@Composable
fun KaylnKwanThermostatIndicators(num: MutableState<Int>) {
    Spacer(modifier = Modifier.height(35.dp))
    ShadeValueIndicator(
        modifier = Modifier
            .size(200.dp),
        cornerRadius = 100.dp,
        borderWidth = 200.dp / 5,
        value = num.value,
        textStyle = TextStyle(fontSize = 35.sp, color = KaylnKwanSubColor),
        min = 20,
        max = 50
    )

    val selected1 = remember { mutableStateOf(false) }
    val selected2 = remember { mutableStateOf(false) }
    val selected3 = remember { mutableStateOf(false) }
    val selected4 = remember { mutableStateOf(false) }
    Spacer(modifier = Modifier.height(40.dp))
    Row(
        modifier = Modifier
            .wrapContentSize(),
    ){
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                painter = painterResource(id = R.drawable.temperature_2),
                isVector = false,
                size = 30.dp,
                offset = 20f,
                blurRadius = 30f,
                cornerRadius = 13.dp,
                iconColor = KaylnKwanIconColor,
                selected = selected1.value,
                onSelectedChanged = {
                    selected1.value = it
                },
            )

        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                painter = painterResource(id = R.drawable.heat),
                isVector = false,
                size = 30.dp,
                offset = 20f,
                blurRadius = 30f,
                cornerRadius = 13.dp,
                iconColor = KaylnKwanIconColor,
                selected = selected2.value,
                onSelectedChanged = {
                    selected2.value = it
                },
            )

        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                painter = painterResource(id = R.drawable.loop_list),
                isVector = false,
                size = 30.dp,
                offset = 20f,
                blurRadius = 30f,
                cornerRadius = 13.dp,
                iconColor = KaylnKwanIconColor,
                selected = selected3.value,
                onSelectedChanged = {
                    selected3.value = it
                },
            )

        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                painter = painterResource(id = R.drawable.cloud_offline),
                isVector = false,
                size = 30.dp,
                offset = 20f,
                blurRadius = 30f,
                cornerRadius = 13.dp,
                iconColor = KaylnKwanIconColor,
                selected = selected4.value,
                onSelectedChanged = {
                    selected4.value = it
                },
            )

        }
    }

}

@Composable
fun KaylnKwanThermostatBottomBar(num: MutableState<Int>) {
    Spacer(modifier = Modifier.height(60.dp))
    ShadeButton(
        text = "Set temperature",
        onClick = {
            num.value = num.value + 1
        },
        textColor = KaylnKwanSubColor,
        width = 280.dp,
        height = 70.dp,
        cornerRadius = 15.dp,
        offset = 20f,
        blurRadius = 30f,
        fontSize = 20.sp
    )
}
