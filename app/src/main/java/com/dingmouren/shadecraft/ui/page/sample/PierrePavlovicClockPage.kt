package com.dingmouren.shadecraft.ui.page.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.Toggle
import com.dingmouren.shadecraft.core.widgets.*
import com.dingmouren.shadecraft.ext.toast

val PierrePavlovicClockTitleColor = Color(0xff2C3D5F)
val PierrePavlovicClockSubColor = Color(0xff55627D)
val PierrePavlovicClockRedColor = Color(0xffFA8181)

data class PierrePavlovicWorldClockBean(val city:String,val date:String,val time:String)

val listWorldColocks = mutableListOf (
    PierrePavlovicWorldClockBean("Beijing","Tuesday:11/10","02:01 AM"),
    PierrePavlovicWorldClockBean("HangZhou","Monday:11/10","06:06 PM"),
    PierrePavlovicWorldClockBean("Beijing","Tuesday:11/10","02:01 AM"),
    PierrePavlovicWorldClockBean("HangZhou","Monday:11/10","06:06 PM"),
    PierrePavlovicWorldClockBean("Beijing","Tuesday:11/10","02:01 AM"),
    PierrePavlovicWorldClockBean("HangZhou","Monday:11/10","06:06 PM"),
    PierrePavlovicWorldClockBean("Beijing","Tuesday:11/10","02:01 AM"),
    PierrePavlovicWorldClockBean("HangZhou","Monday:11/10","06:06 PM"),
    PierrePavlovicWorldClockBean("Beijing","Tuesday:11/10","02:01 AM"),
    PierrePavlovicWorldClockBean("HangZhou","Monday:11/10","06:06 PM"),
)

data class PierrePavlovicAlarmBean(val plan:String,val time:String ,val toggle:Boolean,val remark:String)

val listAlarm = mutableListOf(
    PierrePavlovicAlarmBean("Every Day","06:45 AM",false,"Wake up"),
    PierrePavlovicAlarmBean("Every Day","01:00 AM",true,"Lunch time"),
    PierrePavlovicAlarmBean("Mon,Fri","19:00 PM",true,"Sport"),
//    PierrePavlovicAlarmBean("Every Day","06:45 AM",false,"Wake up"),
//    PierrePavlovicAlarmBean("Every Day","01:00 AM",true,"Lunch time"),
//    PierrePavlovicAlarmBean("Mon,Fri","19:00 PM",true,"Sport"),
//    PierrePavlovicAlarmBean("Every Day","06:45 AM",false,"Wake up"),
//    PierrePavlovicAlarmBean("Every Day","01:00 AM",true,"Lunch time"),
//    PierrePavlovicAlarmBean("Mon,Fri","19:00 PM",true,"Sport"),
//    PierrePavlovicAlarmBean("Every Day","06:45 AM",false,"Wake up"),
//    PierrePavlovicAlarmBean("Every Day","01:00 AM",true,"Lunch time"),
//    PierrePavlovicAlarmBean("Mon,Fri","19:00 PM",true,"Sport"),
)

@Composable
fun PierrePavlovicClockPage(){

    val currentIndex = remember { mutableStateOf(2) }

    Box(modifier = Modifier.fillMaxWidth()){
        when(currentIndex.value){
            2 ->{
                PierrePavlovicWorldClockPage()
            }
            3 ->{
                PierrePavlovicAlarmPage()
            }
            else->{
                Box(modifier = Modifier.fillMaxSize())
            }
        }
        ShadeTabElevated(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 20.dp),
            onSelectedChange = {
                currentIndex.value = it
            },
            initTabIndex = currentIndex.value

        )

    }
}

@Composable
fun PierrePavlovicWorldClockPage() {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val cardWidth = screenWidthDp / 275f * 190f
    val cardHeight = cardWidth / 200f * 70f

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        PierrePavlovicWorldClockBar()
        PierrePavlovicWorldClockInfo()
        PierrePavlovicWorldClockList(cardWidth,cardHeight)
    }

}

@Composable
fun PierrePavlovicWorldClockBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "World Clock",
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        ShadeIconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {  },
            iconColor = PierrePavlovicClockRedColor,
            imageVector = Icons.Filled.Add,
            size = 30.dp,
            offset = 20f,
            blurRadius = 20f,
            cornerRadius = 30.dp
        )
    }
}

@Composable
fun PierrePavlovicWorldClockInfo() {
    Spacer(modifier = Modifier.height(35.dp))
    ShadeTimepiece(
        modifier = Modifier.size(260.dp)
    )
    Spacer(modifier = Modifier.height(35.dp))
    Text(
        text = "07:01 PM",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(3.dp))
    Text(
        text = "Paris",
        fontSize = 13.sp,
    )
}

@Composable
fun PierrePavlovicWorldClockList(cardWidth: Dp, cardHeight: Dp) {
    Spacer(modifier = Modifier.height(35.dp))
    LazyRow(
        modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp)
    ){
        items(listWorldColocks){ item->
            ShadeCard(
                modifier = Modifier.padding(10.dp,0.dp,10.dp,0.dp),
                onClick = {  },
                cornerRadius = 10.dp,
                offset = 20f,
                blurRadius = 20f
            ) {
                Box(modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight),
                ){

                    Column(modifier = Modifier
                        .align(Alignment.CenterStart)) {
                        Text(
                            text = item.city,
                            color = PierrePavlovicClockRedColor,
                            fontSize = 18.sp,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.date,
                            fontSize = 12.sp,
                            color = PierrePavlovicClockSubColor
                        )
                    }
                    Text(
                        text = item.time,
                        modifier = Modifier.align(Alignment.CenterEnd),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = PierrePavlovicClockTitleColor
                    )

                }
            }
        }
    }
}

@Composable
fun PierrePavlovicAlarmPage() {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val cardWidth = screenWidthDp / 275f * 180f
    val cardHeight = cardWidth / 200f * 60f

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        PierrePavlovicAlarmBar()
        PierrePavlovicAlarmList(cardWidth,cardHeight)
    }
}

@Composable
fun PierrePavlovicAlarmBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Alarm",
            modifier = Modifier.align(Alignment.CenterStart),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        ShadeIconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {  },
            iconColor = PierrePavlovicClockRedColor,
            imageVector = Icons.Filled.Add,
            size = 30.dp,
            offset = 20f,
            blurRadius = 20f,
            cornerRadius = 30.dp
        )
    }

}

@Composable
fun PierrePavlovicAlarmList(cardWidth: Dp, cardHeight: Dp) {
    Spacer(modifier = Modifier.height(35.dp))
    LazyColumn(
        modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp)
    ){
        items(listAlarm){ item->
            ShadeCard(
                modifier = Modifier.padding(20.dp,20.dp,20.dp,20.dp),
                onClick = {  },
                cornerRadius = 10.dp,
                offset = 20f,
                blurRadius = 20f
            ) {
                Box(modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight),
                ){

                    Column(modifier = Modifier
                        .align(Alignment.CenterStart)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier
                                .size(2.dp)
                                .background(
                                    PierrePavlovicClockRedColor
                                ))
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = item.plan,
                                color = PierrePavlovicClockSubColor,
                                fontSize = 14.sp,
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.time,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = PierrePavlovicClockTitleColor
                        )
                        Text(
                            text = item.remark,
                            fontSize = 12.sp,
                            color = PierrePavlovicClockTitleColor
                        )
                    }

                    ShadeToggle(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        width = 60.dp,
                        height = 30.dp,
                        colorOn = PierrePavlovicClockRedColor,
                        onToggleChange = {

                        },
                        toggle = if (item.toggle) Toggle.OFF else Toggle.ON
                    )

                }
            }
        }
    }
}
