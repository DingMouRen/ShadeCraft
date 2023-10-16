package com.dingmouren.shadecraft.ui.page.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.widgets.*
import com.dingmouren.shadecraft.route.LocalNavController

@Composable
fun CaskerLivingRoomPage(){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }
    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            CaskerLivingRoomActionBar()
            Spacer(modifier = Modifier.height(30.dp))
            CaskerLivingRoomAction()
            CaskerLivingRoomActionInfo(screenWidthDp)
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(20.dp)
            .fillMaxWidth()
            .wrapContentSize()){
            CaskerLivingRoomSettingBottomBar(screenWidthDp)
        }
    }
}

@Composable
fun CaskerLivingRoomActionBar() {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        )
        {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Start),
                tint = CaskerThemeColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Living Room",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp),
            color = CaskerThemeColor
        )

    }
}

@Composable
fun CaskerLivingRoomAction() {
    val selected1 = remember { mutableStateOf(false) }
    val selected2 = remember { mutableStateOf(false) }
    val selected3 = remember { mutableStateOf(false) }
    val selected4 = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
    ){
      Column(
          modifier = Modifier.weight(1f),
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          ShadeRadioButton(
              painter = painterResource(id = R.drawable.temperature),
              isVector = false,
              size = 36.dp,
              offset = 7f,
              cornerRadius = 10.dp,
              iconColor = CaskerIconColor,
              selected = selected1.value,
              onSelectedChanged = {
                  selected1.value = it
              },
          )
          Text(
              text = "Temp",
              color = CaskerThemeColor,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(vertical = 5.dp)
          )
      }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                painter = painterResource(id = R.drawable.sun),
                isVector = false,
                size = 36.dp,
                offset = 7f,
                cornerRadius = 10.dp,
                iconColor = CaskerIconColor,
                selected = selected2.value,
                onSelectedChanged = {
                    selected2.value = it
                },
            )
            Text(
                text = "Light",
                color = CaskerThemeColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                painter = painterResource(id = R.drawable.wifi),
                isVector = false,
                size = 36.dp,
                offset = 7f,
                cornerRadius = 10.dp,
                iconColor = CaskerIconColor,
                selected = selected3.value,
                onSelectedChanged = {
                    selected3.value = it
                },
            )
            Text(
                text = "Internet",
                color = CaskerThemeColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShadeRadioButton(
                imageVector = Icons.Filled.Add,
                isVector = true,
                size = 36.dp,
                offset = 7f,
                cornerRadius = 10.dp,
                iconColor = CaskerIconColor,
                selected = selected4.value,
                onSelectedChanged = {
                    selected4.value = it
                },
            )
            Text(
                text = "Add",
                color = CaskerThemeColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 5.dp),
            )
        }
    }

}

@Composable
fun CaskerLivingRoomActionInfo(screenWidthDp: Dp) {
    val processCircleSize = screenWidthDp * 2 /3
    val circleInnerSize = processCircleSize - 50.dp
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(screenWidthDp)
            .padding(20.dp)
        ){
            ShadeCircleProgressConvex(
                shape = Shape.Circle,
                progress = 30,
                progressBgColor = Color(0xffE3E5EA),
                progressColor = CaskerThemeColor,
                size = processCircleSize,
                showProgressText = false,
                showEndCirclePoint = false,
                borderWidth = 2.dp,
                offset = 0f,
                progressColorGradient = false,
                modifier = Modifier.align(Alignment.Center)
            )

            ShadeCard(
                onClick = {  },
                modifier = Modifier.align(Alignment.Center),
                cornerRadius = circleInnerSize/2,
                offset = 15f
            ) {
                Box(
                    modifier = Modifier.size(circleInnerSize),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "27.0°C",
                        color = CaskerThemeColor,
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Thin
                    )
                }

            }
        }
      Row(modifier = Modifier.padding(horizontal = 20.dp)) {
          Column(modifier = Modifier.weight(1f)) {
              Text(
                  text = "Current temperature",
                  color = CaskerThemeColor,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.height(5.dp))
              Text(
                  text = "18.5",
                  color = CaskerThemeColor,
                  fontSize = 35.sp,
              )
          }
          Column(modifier = Modifier.weight(1f)) {
              Text(
                  text = "Turn On/Off",
                  color = CaskerThemeColor,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.height(5.dp))
              ShadeToggleConcave(
                  width = 75.dp,
                  height = 45.dp,
                  fontSize = 11.sp,
                  onToggleChange = {
                  }
              )
          }
      }
    }

}

@Composable
fun CaskerLivingRoomSettingBottomBar(screenWidthDp: Dp) {
    ShadeButton(
        text = "Set temperature",
        onClick = {  },
        textColor = CaskerThemeColor,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        cornerRadius = 15.dp,
        width = screenWidthDp - 60.dp,
        height = 60.dp

    )
}
