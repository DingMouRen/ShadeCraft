package com.dingmouren.shadecraft.ui.page.sample

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.widgets.ShadeCard
import com.dingmouren.shadecraft.core.widgets.ShadeImageButton
import com.dingmouren.shadecraft.route.LocalNavController
import com.dingmouren.shadecraft.route.RoutePath

val CaskerThemeColor = Color(0xff6D7587)
val CaskerSecondColor = Color(0xffB2B5BC)
val CaskerIconColor = Color(0xff7A8399)

data class CaskerSmartHomeBean(@DrawableRes val img :Int, val roomName:String,val countDevices:Int )

val listCaskerSmart = mutableListOf(
    CaskerSmartHomeBean(R.drawable.casker_yupen,"Bathroom",2),
    CaskerSmartHomeBean(R.drawable.casker_shafa,"Living room",3),
    CaskerSmartHomeBean(R.drawable.casker_yupen,"Bathroom",2),
    CaskerSmartHomeBean(R.drawable.casker_shafa,"Living room",3),
    CaskerSmartHomeBean(R.drawable.casker_yupen,"Bathroom",2),
    CaskerSmartHomeBean(R.drawable.casker_shafa,"Living room",3),
    CaskerSmartHomeBean(R.drawable.casker_yupen,"Bathroom",2),
    CaskerSmartHomeBean(R.drawable.casker_shafa,"Living room",3),
    CaskerSmartHomeBean(R.drawable.casker_yupen,"Bathroom",2),
    CaskerSmartHomeBean(R.drawable.casker_shafa,"Living room",3),
)

@Composable
fun CaskerSmartHomePage(){

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val cardWidth = (screenWidthDp - 20.dp - 15.dp)/5*3
    val cardHeight = cardWidth/5*7

    Box(
        modifier = Modifier.fillMaxSize(),
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            CaskerSmartHomeTitleBar()
            CaskerSmartHomePowerNotice()
            CaskerSmartHomeRooms(cardWidth,cardHeight)
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(horizontal = 3.dp)
            .fillMaxWidth()
            .wrapContentSize()){
            CaskerSmartHomeRemoteAccess()
        }
    }
}

@Composable
fun CaskerSmartHomeTitleBar() {
    val title = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = CaskerThemeColor)) {
            append("Hello, ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = CaskerThemeColor)) {
            append("Annie!")
        }
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Text(
            text = title
        , fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Aniting I Can helpyou with?",
            fontSize = 12.sp,
            color = CaskerSecondColor
        )
    }
}

@Composable
fun CaskerSmartHomePowerNotice() {
    val title = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = CaskerThemeColor, fontSize = 20.sp)) {
            append("26.3 ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = CaskerThemeColor, fontSize = 12.sp)) {
            append("Kwh")
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        ShadeImageButton(
            onClick = {  }, 
            size = 50.dp,
            painter =  painterResource(id = R.drawable.flashlight),
            shape = Shape.Circle
        )
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = title,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Power usage for today",
                fontSize = 12.sp,
                color = CaskerSecondColor
            )
        }
        
    }
}

@Composable
fun CaskerSmartHomeRooms(cardWidth: Dp, cardHeight: Dp) {
    val navController = LocalNavController.current
    LazyRow(
        modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp)
    ){
        items(listCaskerSmart){ item->
            ShadeCard(
                modifier = Modifier.padding(5.dp,0.dp,15.dp,0.dp),
                onClick = { navController.navigate(RoutePath.CaskerLivingRoomPage) },
                cornerRadius = 20.dp
            ) {
                Box(modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight),
                ){
                  Image(
                      painter = painterResource(id = item.img),
                      contentDescription = null,
                      modifier = Modifier
                          .align(Alignment.CenterStart)
                          .size(130.dp),
                  )
                    Column(modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .align(Alignment.BottomStart)) {
                        Text(
                            text = item.roomName,
                            color = CaskerThemeColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "${item.countDevices} device",
                            fontSize = 12.sp,
                            color = CaskerSecondColor
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun CaskerSmartHomeRemoteAccess() {
    ShadeCard(
        onClick = {  },
        cornerRadius = 20.dp
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(20.dp,10.dp,50.dp,30.dp),
        ) {

            Box(modifier = Modifier
                .width(25.dp)
                .height(3.dp)
                .background(CaskerThemeColor)
                .align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Quick remote access",
                color = CaskerThemeColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Swipe up to get a fast access to your wireless remote control.",
                fontSize = 12.sp,
                color = CaskerSecondColor
            )
        }
    }
}
