package com.dingmouren.shadecraft.ui.page.sample

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.widgets.*

val FatemeSouriTitleColor = Color(0xff000000)
val FatemeSouriSecondColor = Color(0xffC2C5C7)
val FatemeSouriPurple = Color(0xff8769D5)
val FatemeSouriPurpleLIght = Color(0xffA694DE)
val FatemeSouriBlue = Color(0xff63BFEB)
val FatemeSouriYellow = Color(0xffF6C439)
val FatemeSouriPink = Color(0xffFB83BE)
val FatemeSouriGreen = Color(0xff68DB56)
val FatemeSouriPath = Color(0xffD3E0FF)

@Composable
fun FatemeSouriPage() {

    val selectedIndex = remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        when (selectedIndex.value) {
            0 -> FatemeSouriHome()
            1 -> FatemeSouriAdd()
            2 -> FatemeSouriData()

        }
        FatemeSouriBottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 30.dp),
            selectedIndex
        )
    }

}

@Composable
fun FatemeSouriHome() {
    Column(
        modifier = Modifier
            .padding(20.dp, 40.dp, 20.dp, 0.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FatemeSouriHomeTopBar()
        FatemeSouriHomeProgress()
        FatemeSouriHomeVs()
    }


}

@Composable
fun FatemeSouriHomeTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { }
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            ShadeImageButton(
                onClick = { },
                size = 30.dp,
                painter = painterResource(id = R.drawable.more),
                modifier = Modifier.align(Alignment.CenterStart),
                cornerRadius = 10.dp
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .wrapContentSize(

                    )
            ) {
                ShadeImageButton(
                    onClick = { },
                    size = 50.dp,
                    painter = painterResource(id = R.drawable.fatemefourihead1),
                    modifier = Modifier.align(Alignment.CenterEnd),
                    cornerRadius = 50.dp
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset((-12).dp, (-12).dp)
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(FatemeSouriGreen)
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "October 12, 2021",
                color = FatemeSouriSecondColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start

            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Good day Nikki!",
                color = FatemeSouriTitleColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start

            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                ShadeCard(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { },
                    cornerRadius = 20.dp,
                    blurRadius = 30f,
                    offset = 15f,
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(80.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Sun",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "10",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                ShadeCard(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { },
                    cornerRadius = 20.dp,
                    blurRadius = 30f,
                    offset = 15f,
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(80.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Mon",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "11",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                ShadeCard(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { },
                    cornerRadius = 20.dp,
                    blurRadius = 30f,
                    offset = 15f,
                ) {
                    Box(
                        modifier = Modifier
                            .background(FatemeSouriPurple)
                            .width(40.dp)
                            .height(80.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Tue",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Box(modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                            ){
                                Text(
                                    text = "12",
                                    color = FatemeSouriTitleColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                ShadeCard(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { },
                    cornerRadius = 20.dp,
                    blurRadius = 30f,
                    offset = 15f,
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(80.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Wed",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "13",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                ShadeCard(
                    modifier = Modifier.align(Alignment.Center),
                    onClick = { },
                    cornerRadius = 20.dp,
                    blurRadius = 30f,
                    offset = 15f,
                ) {
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(80.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Fri",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "14",
                                color = FatemeSouriTitleColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun FatemeSouriHomeProgress() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val circleSize = screenWidthDp * 2 / 3 - 40.dp

    Text(
        text = "Today prograss",
        color = FatemeSouriTitleColor,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        textAlign = TextAlign.Center
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(circleSize + 10.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {
        Box(modifier = Modifier.weight(2f)){
            ShadeCard(
                onClick = {  },
                cornerRadius = circleSize,
                blurRadius = 30f,
                offset = 15f,
            ) {
                val title = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriTitleColor, fontSize = 20.sp)) {
                        append("895\n")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = FatemeSouriSecondColor, fontSize = 12.sp)) {
                        append("Calories")
                    }
                }
                Box(modifier = Modifier.size(circleSize)){
                    ShadeCircleProgressConcave(
                        modifier = Modifier.align(Alignment.Center),
                        progress = 70,
                        progressColor = FatemeSouriPurple,
                        backgroundColor = Color(0xffD4DAE7),
                        size = 150.dp,
                        borderWidth = 30.dp,
                        text = title
                    )
                }
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .wrapContentHeight()) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                ShadeCard(
                    onClick = {  },
                    cornerRadius = 10.dp,
                    blurRadius = 30f,
                    offset = 15f
                ) {
                    Box(modifier = Modifier
                        .size(120.dp, 60.dp)
                       ){
                        val totalRunText = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriTitleColor, fontSize = 13.sp)) {
                                append("Total Run\n")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriPurple, fontSize = 12.sp)) {
                                append("7465m")
                            }
                        }
                        Text(text = totalRunText, modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center)

                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                ShadeCard(
                    onClick = {  },
                    cornerRadius = 10.dp,
                    blurRadius = 30f,
                    offset = 15f
                ){
                    Box(modifier = Modifier
                        .size(120.dp, 60.dp)
                        ){
                        val averageSpeedText = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriTitleColor, fontSize = 13.sp)) {
                                append("Average Speed\n")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriPurple, fontSize = 12.sp)) {
                                append("10 km/h")
                            }
                        }
                        Text(text = averageSpeedText, modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center)

                    }
                }
            }
        }
    }
}

@Composable
fun FatemeSouriHomeVs() {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    val vsCardWidth = screenWidthDp - 60.dp
    val vsCardHeight = vsCardWidth / 35 * 18
    Spacer(modifier = Modifier.height(15.dp))
    ShadeCard(
        onClick = {  },
        blurRadius = 30f,
        offset = 15f,
        cornerRadius = vsCardHeight / 2
    ) {
        Box(
            modifier = Modifier
                .size(vsCardWidth, vsCardHeight)
                .padding(horizontal = 30.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.fatemefourihead1),
                contentDescription = null,
                modifier = Modifier
                    .size(vsCardHeight / 3)
                    .align(Alignment.CenterStart)
            )
            Image(
                painter = painterResource(id = R.drawable.fatemefourihead2),
                contentDescription = null,
                modifier = Modifier
                    .size(vsCardHeight / 3)
                    .align(Alignment.CenterEnd)
            )
            Text(
                text = "Duel",
                color = FatemeSouriTitleColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Text(
                text = "You",
                color = FatemeSouriTitleColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(vsCardHeight / 3)
                    .offset(0.dp, 5.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Sarah",
                color = FatemeSouriTitleColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(vsCardHeight / 3)
                    .offset(0.dp, 5.dp),
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .height(vsCardHeight / 3)
                    .align(Alignment.Center),
            ) {
                val vsText = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriGreen, fontSize = 12.sp)) {
                        append("7465m ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriSecondColor, fontSize = 12.sp)) {
                        append("vs. ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriPurple, fontSize = 12.sp)) {
                        append("3479m")
                    }
                }
                Text(text = vsText, modifier = Modifier.align(Alignment.TopCenter))
                VsLines(modifier = Modifier.align(Alignment.Center),140.dp,5.dp,)
                Text(
                    text = "Running",
                    color = FatemeSouriSecondColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

@Composable
fun VsLines(modifier: Modifier,width: Dp, height: Dp) {
    Canvas(modifier = modifier
        .size(width, height)
        .padding(vertical = 5.dp)) {
        val cornerRadius = 8.dp.toPx() // 圆角半径
        val lineWidth = 5.dp.toPx() // 直线宽度
        val startY = size.height / 2 // 起始点的 Y 值
        val endY = size.height / 2 // 终止点的 Y 值

        val line1Length = size.width * 2 / 3 // 第一条直线的长度
        val line2Length = size.width - line1Length // 第二条直线的长度

        val startPoint1 = Offset(cornerRadius, startY)
        val endPoint1 = Offset(line1Length - cornerRadius, endY)
        val startPoint2 = Offset(line1Length + cornerRadius, startY)
        val endPoint2 = Offset(size.width - cornerRadius, endY)

        // 绘制第一条直线
        drawLine(
            color = FatemeSouriGreen,
            start = startPoint1,
            end = endPoint1,
            strokeWidth = lineWidth,
            cap = StrokeCap.Round // 设置端点为圆角
        )

        // 绘制第二条直线
        drawLine(
            color = FatemeSouriPurple,
            start = startPoint2,
            end = endPoint2,
            strokeWidth = lineWidth,
            cap = StrokeCap.Round // 设置端点为圆角
        )
    }
}

@Composable
fun FatemeSouriAdd() {
}

@Composable
fun FatemeSouriData() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 40.dp, 20.dp, 0.dp)

    ) {
        FateSouriTopDataBar()
        FateSouriTopDataContent()
        FateSouriTopDataDetail()

    }
}

@Composable
fun FateSouriTopDataContent() {
    Text(
        text = "Statistics",
        color = FatemeSouriTitleColor,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(vertical = 20.dp)

    )
    Text(
        text = "Calories Statistics",
        color = FatemeSouriTitleColor,
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Start,
    )
    Spacer(modifier = Modifier.height(10.dp))
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)){
        Text(
            text = "1000\n\n800\n\n600\n\n400\n\n0",
            fontSize = 13.sp,
            color = FatemeSouriSecondColor,
            modifier = Modifier.align(Alignment.BottomStart),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 0.dp, 20.dp, 0.dp)
            .align(Alignment.BottomStart)
            .wrapContentHeight()) {
            Box(modifier = Modifier.weight(1f)){
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShadeCalorVerticalProgress(
                        progressColor = FatemeSouriPink,
                        width = 35.dp,
                        height = 160.dp,
                        progress = 60,
                        cornerRadius = 35.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Otc 7",
                        color = FatemeSouriSecondColor,
                        fontSize = 13.sp
                    )
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShadeCalorVerticalProgress(
                        progressColor = FatemeSouriYellow,
                        width = 35.dp,
                        height = 160.dp,
                        progress = 40,
                        cornerRadius = 35.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Otc 8",
                        color = FatemeSouriSecondColor,
                        fontSize = 13.sp
                    )
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShadeCalorVerticalProgress(
                        progressColor = FatemeSouriYellow,
                        width = 35.dp,
                        height = 160.dp,
                        progress = 0,
                        cornerRadius = 35.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Otc 9",
                        color = FatemeSouriSecondColor,
                        fontSize = 13.sp
                    )
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShadeCalorVerticalProgress(
                        progressColor = FatemeSouriBlue,
                        width = 35.dp,
                        height = 160.dp,
                        progress = 80,
                        cornerRadius = 35.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Otc 10",
                        color = FatemeSouriSecondColor,
                        fontSize = 13.sp
                    )
                }
            }
            Box(modifier = Modifier.weight(1f)){
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShadeCalorVerticalProgress(
                        progressColor = FatemeSouriPurple,
                        width = 35.dp,
                        height = 160.dp,
                        progress = 50,
                        cornerRadius = 35.dp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Otc 11",
                        color = FatemeSouriSecondColor,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }

}

@Composable
fun FateSouriTopDataDetail() {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    Text(
        text = "Details",
        color = FatemeSouriTitleColor,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(vertical = 10.dp)
    )
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
       Column(modifier = Modifier.wrapContentSize()){
           Row(
               modifier = Modifier
                   .wrapContentSize()
                   .offset(35.dp, 0.dp),
               verticalAlignment = Alignment.CenterVertically

           ) {
               Text(text = "7.4 Km", fontWeight = FontWeight.Bold,color = FatemeSouriPurple, fontSize = 12.sp)
               Image(
                   painter = painterResource(id = R.drawable.arrow_top),
                   contentDescription = null,
                   colorFilter = ColorFilter.tint(FatemeSouriPurple),
                   modifier = Modifier.size(30.dp)
               )
           }
           FateSouriRoutePath(modifier = Modifier.size(screenWidthDp/2))
       }
        Column(modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 20.dp, vertical = 0.dp)) {
            ShadeCard(
                onClick = {  },
                cornerRadius = 10.dp
            ) {
                Box(modifier = Modifier
                    .width(100.dp)
                    .height(125.dp)) {
                    Text(text = "Steps",fontWeight = FontWeight.Bold,color = FatemeSouriTitleColor, fontSize = 15.sp, modifier = Modifier.padding(5.dp))
                    ShadeCard(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {  },
                        cornerRadius = 80.dp,
                        blurRadius = 30f,
                        offset = 15f,
                    ) {
                        val title = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriTitleColor, fontSize = 10.sp)) {
                                append("70%\n")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal, color = FatemeSouriSecondColor, fontSize = 6.sp)) {
                                append("Goal")
                            }
                        }
                        Box(modifier = Modifier.size(80.dp)){
                            ShadeCircleProgressConcave(
                                modifier = Modifier.align(Alignment.Center),
                                progress = 70,
                                progressColor = FatemeSouriPurple,
                                backgroundColor = Color(0xffD4DAE7),
                                size = 60.dp,
                                borderWidth = 15.dp,
                                text = title
                            )
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            ShadeCard(onClick = {  },
                cornerRadius = 10.dp) {

                Box(modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)) {
                    Text(text = "Heart",fontWeight = FontWeight.Bold,color = FatemeSouriTitleColor, fontSize = 15.sp, modifier = Modifier.padding(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.heart_shake),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(50.dp)
                    )
                    val heartText = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriTitleColor, fontSize = 15.sp)) {
                            append("96")
                        }
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = FatemeSouriSecondColor, fontSize = 13.sp)) {
                            append(" Bpm")
                        }
                    }
                    Text(text = heartText, modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(0.dp, 0.dp, 0.dp, 5.dp))
                }
            }
        }
    }
}


@Composable
fun FateSouriRoutePath(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier){

        Canvas(modifier = Modifier.size(maxWidth,maxHeight)){


            val paintBelow = Paint().also {
                it.color = FatemeSouriPath
                it.isAntiAlias = true
                it.strokeWidth = 50f
                it.style = PaintingStyle.Stroke
            }
            val paintAbove = Paint().also {
                it.color = FatemeSouriPurple
                it.isAntiAlias = true
                it.strokeWidth = 10f
                it.style = PaintingStyle.Stroke
            }
            val path = Path()
            path.moveTo(maxWidth.toPx()/2,0f)
            path.lineTo(maxWidth.toPx()/2,maxHeight.toPx()/2)
            path.lineTo(maxWidth.toPx(),maxHeight.toPx()/2)
            path.lineTo(maxWidth.toPx(),maxHeight.toPx())

            this.drawIntoCanvas {
                it.nativeCanvas.drawPath(path.asAndroidPath(),paintBelow.asFrameworkPaint())
                it.nativeCanvas.drawPath(path.asAndroidPath(),paintAbove.asFrameworkPaint())
            }

        }
        ShadeCard(onClick = {  }) {
            Box(modifier = Modifier.size(maxWidth/2 - 20.dp,maxWidth/4))
        }

        ShadeCard(
            onClick = {  },
            modifier = Modifier.padding(0.dp,maxWidth/4+5.dp)
        ) {
            Box(modifier = Modifier.size(maxWidth/2 - 20.dp,maxWidth/4))
        }
        ShadeCard(
            onClick = {  },
            modifier = Modifier.offset(0.dp,maxWidth/2 + maxWidth/5+10.dp )
        ) {
            Box(modifier = Modifier.size(maxWidth- 20.dp,maxWidth/5))
        }
        ShadeCard(
            onClick = {  },
            modifier = Modifier.offset(maxWidth/2,maxWidth/2 +12.dp )
        ) {
            Box(modifier = Modifier.size(maxWidth/2- 20.dp,maxWidth/7))
        }
        ShadeCard(
            onClick = {  },
            modifier = Modifier.offset(maxWidth/2+15.dp,0.dp )
        ) {
            Box(modifier = Modifier.size(maxWidth/2- 20.dp,maxWidth/3+10.dp))
        }
    }

}

@Composable
fun FateSouriTopDataBar() {
    Box(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { }
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        ShadeImageButton(
            onClick = { },
            size = 30.dp,
            painter = painterResource(id = R.drawable.more),
            modifier = Modifier.align(Alignment.CenterStart),
            cornerRadius = 10.dp
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .wrapContentSize(

                )
        ) {
            ShadeImageButton(
                onClick = { },
                size = 50.dp,
                painter = painterResource(id = R.drawable.fatemefourihead1),
                modifier = Modifier.align(Alignment.CenterEnd),
                cornerRadius = 50.dp
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset((-12).dp, (-12).dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(FatemeSouriGreen)
            )
        }

    }
}

@Composable
fun FatemeSouriBottomBar(
    modifier: Modifier = Modifier,
    selectedIndex: MutableState<Int>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .wrapContentHeight()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            ShadeRadioButton(
                modifier = Modifier.align(Alignment.Center),
                size = 40.dp,
                isVector = false,
                onSelectedChanged = {
                    selectedIndex.value = 0
                },
                painter = painterResource(id = R.drawable.home),
                iconColor = if (selectedIndex.value == 0) FatemeSouriPurple else FatemeSouriPurpleLIght,
                selected = selectedIndex.value == 0,
                cornerRadius = 40.dp,
                offset = 11f,
                iconPadding = 5.dp,
                blurRadius = 30f

            )
        }
        Box(modifier = Modifier.weight(1f)) {
            ShadeRadioButton(
                modifier = Modifier.align(Alignment.Center),
                size = 40.dp,
                isVector = true,
                onSelectedChanged = {
                    selectedIndex.value = 1
                },
                imageVector = Icons.Filled.Add,
                iconColor = if (selectedIndex.value == 1) FatemeSouriPurple else FatemeSouriPurpleLIght,
                selected = selectedIndex.value == 1,
                cornerRadius = 40.dp,
                offset = 11f,
                iconPadding = 5.dp,
                blurRadius = 30f

            )
        }
        Box(modifier = Modifier.weight(1f)) {
            ShadeRadioButton(
                modifier = Modifier.align(Alignment.Center),
                size = 40.dp,
                isVector = false,
                onSelectedChanged = {
                    selectedIndex.value = 2
                },
                painter = painterResource(id = R.drawable.ranking),
                iconColor = if (selectedIndex.value == 2) FatemeSouriPurple else FatemeSouriPurpleLIght,
                selected = selectedIndex.value == 2,
                cornerRadius = 40.dp,
                offset = 11f,
                iconPadding = 5.dp,
                blurRadius = 30f

            )
        }


    }
}
