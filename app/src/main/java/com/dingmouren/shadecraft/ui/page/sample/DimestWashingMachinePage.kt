package com.dingmouren.shadecraft.ui.page.sample

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.R
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.widgets.*
import kotlinx.coroutines.delay

val DimestIconColor = Color(0xff8B8F9F)
val DimestTitleColor = Color(0xff323232)
val DimestSecondColor = Color(0xffB2B6B9)

data class WashingMachineMode(val color:Color,val title :String,val time:String)

val washingMachineModes = mutableListOf(
    WashingMachineMode(Color(0xff356DF2),"Standard","32minutes"),
    WashingMachineMode(Color(0xff20B8FF),"Gentle","32minutes"),
    WashingMachineMode(Color(0xff5A3BFF),"Fast","32minutes"),
    WashingMachineMode(Color(0xff356DF2),"Standard","32minutes"),
    WashingMachineMode(Color(0xff20B8FF),"Gentle","32minutes"),
    WashingMachineMode(Color(0xff5A3BFF),"Fast","32minutes"),
    WashingMachineMode(Color(0xff356DF2),"Standard","32minutes"),
    WashingMachineMode(Color(0xff20B8FF),"Gentle","32minutes"),
    WashingMachineMode(Color(0xff5A3BFF),"Fast","32minutes"),
    WashingMachineMode(Color(0xff356DF2),"Standard","32minutes"),
    WashingMachineMode(Color(0xff20B8FF),"Gentle","32minutes"),
    WashingMachineMode(Color(0xff5A3BFF),"Fast","32minutes"),
)

@Composable
fun DimestWashingMachinePage(){

    val modeIndex = remember { mutableStateOf(-1) }
    val drawerState = remember { mutableStateOf(false) }
    val rotating = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(35.dp))
            WashingMachineTopActionBar(drawerState)
            WashingMachineAction(modeIndex,rotating)

        }
        Box(modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(0.dp, 0.dp, 0.dp, 50.dp)) {
            WashingMachineMode(modeIndex)
        }
        if (drawerState.value){
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.3f)))
        }
        DimestDrawer(drawerState)

    }



}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DimestDrawer(drawerState: MutableState<Boolean>) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }
    val progressCurrent = remember { mutableStateOf(0f) }
    val animatedOffsetX = animateDpAsState(
        targetValue = if (drawerState.value) 0.dp  else -screenWidthDp,
        animationSpec = tween(300)
    ).value

    Box(modifier = Modifier
        .offset(x = animatedOffsetX)
        .fillMaxSize()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            drawerState.value = false
        }
        ){
        Box(modifier = Modifier
            .fillMaxHeight()
            .width(screenWidthDp / 4 * 3)
            .clipToBounds()
            .graphicsLayer(
                clip = true,
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)
            )
            .background(ConstantColor.NeumorphismLightBackgroundColor)
        ){
            Column(modifier = Modifier
                .padding(horizontal = 20.dp)
                .wrapContentSize()) {
                Spacer(modifier = Modifier.height(55.dp))
                ShadeImageButton(
                    onClick = {
                        drawerState.value = !drawerState.value
                    },
                    size = 30.dp,
                    painter = painterResource(id = R.drawable.back),
                    iconColor = DimestIconColor,
                    cornerRadius = 10.dp,
                    iconPadding = 3.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Choose water",
                    color = DimestTitleColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W500
                )
                Text(
                    text = "Please save choice",
                    fontSize = 12.sp,
                    color = DimestTitleColor
                )
            }
            Row(modifier = Modifier
                .wrapContentWidth()
                .height(400.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterStart)) {

                ShadeWaveProgress(
                    width = 86.dp,
                    height = 400.dp,
                    cornerRadius = 43.dp,
                    colors = mutableListOf(
                        Color(0xffFAC20B),
                        Color(0xffF58C08),
                        Color(0xffBD56B1),
                        Color(0xff9949CE),
                        Color(0xff5E54E6),
                    ),
                    onProgress = {
                        progressCurrent.value = it
                    },
                    progress = 300f,
                    maxProgress = 1400f,
                )
                Box(modifier = Modifier.fillMaxSize()){
                    DimestWaterRuler(
                        maxRuler = 1400f,
                        width = 120.dp,
                        height = 400.dp,
                        minInterval = 50,
                        modifier = Modifier.padding(horizontal = 10.dp),
                        start = 200,
                        end = 1200,
                        verticalMargin = 30.dp

                    )

                }
            }
            Row(
                modifier = Modifier
                    .padding(20.dp, 0.dp, 0.dp, 80.dp)
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Current",
                    color = DimestTitleColor,
                    fontSize = 16.sp

                )
                Spacer(modifier = Modifier.width(10.dp))
                progressCurrent.value.toInt().toString().forEach {
                    AnimatedContent(
                        targetState = it,
                        transitionSpec = {
                            slideIntoContainer(AnimatedContentScope.SlideDirection.Up) with
                                    fadeOut() + slideOutOfContainer(AnimatedContentScope.SlideDirection.Up)
                        }
                    ) { char ->
                        Text(text = char.toString(),  fontSize = 18.sp, color = DimestTitleColor, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

}

@Composable
fun DimestWaterRuler(
    modifier: Modifier = Modifier,
    maxRuler:Float,
    minInterval:Int,
    width : Dp,
    height: Dp,
    lineColor: Color = Color(0xffBBBDC4),
    start:Int,
    end:Int,
    verticalMargin:Dp
) {
    val density = LocalDensity.current.density
    val widthPx = width.value * density
    val heightPx = height.value * density
    val textSizePx = 30f



    Canvas(modifier = modifier
        .padding(vertical = verticalMargin)
        .fillMaxSize() ){
        val shortLineLength = widthPx /6
        val longLineLength = widthPx / 6 * 4
        val sizeMinStep = (end - start) / minInterval
        val intervalMin = this.size.height / sizeMinStep
        val paint = Paint().asFrameworkPaint().apply {
            color = lineColor.toArgb()
            textSize = textSizePx


        }

        for(i in 0..sizeMinStep){

            if (i % 4 == 0){
                drawLine(
                    color = lineColor,
                    start = Offset(0f, i * intervalMin),
                    end = Offset(longLineLength,  i * intervalMin),
                    strokeWidth = 2f
                )
                drawContext.canvas.nativeCanvas.drawText((end - i * minInterval).toString(),longLineLength + 10f,i * intervalMin + textSizePx/3,paint)

            }else{
                drawLine(
                    color = lineColor.copy(alpha = 0.6f),
                    start = Offset(0f, i * intervalMin),
                    end = Offset(shortLineLength,  i * intervalMin),
                    strokeWidth = 2f
                )
            }
        }
    }
}

@Composable
fun WashingMachineTopActionBar(drawerState: MutableState<Boolean>) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 20.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ){
            ShadeIconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    drawerState.value = !drawerState.value
                },
                size = 30.dp,
                imageVector = Icons.Filled.Settings,
                iconColor = DimestIconColor,
                cornerRadius = 10.dp,
                iconPadding = 3.dp
            )
            ShadeButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = "10:00",
                onClick = {  },
                convexity = Convexity.CONCAVE,
                textColor = DimestTitleColor,
                cornerRadius = 10.dp,
                height = 40.dp,
                width = 90.dp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = Modifier
            .wrapContentSize()) {
            Text(
                text = "Smart Washing",
                color = DimestTitleColor,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Machine",
                fontSize = 20.sp,
                color = DimestTitleColor
            )
        }
    }

}

@Composable
fun WashingMachineAction(modeIndex: MutableState<Int>, rotating: MutableState<Boolean>) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(screenWidthDp),
    ) {
        WashingMachineActionMenu(modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(horizontal = 20.dp),modeIndex,rotating)
        WashingMachine(modifier = Modifier
            .align(Alignment.CenterStart)
            .offset(80.dp),rotating)
    }

}

@Composable
fun WashingMachine(modifier: Modifier, rotating: MutableState<Boolean>) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp
    val circleSize = screenWidthDp

    // 定义动画参数
    val rotationAngle = remember { Animatable(0f) }

    LaunchedEffect(rotating.value ){
        if (rotating.value){
            while (true) {
                rotationAngle.animateTo(360f, animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ))
            }
        }else{
            if (rotationAngle.value == 0f){
                rotationAngle.animateTo(0f)
            }else{
                rotationAngle.stop()
            }


        }
    }


    Box(modifier = modifier.size(circleSize)){
        ShadeCard(
            onClick = {

            },
            cornerRadius = circleSize/2,
            modifier = Modifier.align(Alignment.Center),
            offset = 15f,
            blurRadius = 15f
        ) {
            Box(modifier = Modifier.size(circleSize - 40.dp))
        }
        WashingCircleBg()
        WashingInnerBorder(modifier = Modifier
            .align(Alignment.Center)
            .size(circleSize - 100.dp))
        Image(
            painter = painterResource(id = R.drawable.machine),
            contentDescription = null,
            modifier = Modifier
                .size(circleSize - 115.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .graphicsLayer {
                    Log.e("测试","rotationAngle:${rotationAngle.value}")
                    rotationZ = rotationAngle.value
                }

        )
    }
}

fun getRotateAddAngle(value: Float): Float {
    return value / 1080f * 50f
}


@Composable
fun WashingInnerBorder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Canvas(modifier = modifier
            .fillMaxSize()
            .align(Alignment.Center)) {
            // 定义渐变起点和终点颜色
            val startColor = Color(0xffCBD3E3)
            val endColor = Color.White

            // 定义渐变
            val gradient = Brush.linearGradient(
                colors = listOf(startColor, endColor),
                start = Offset.Zero,
                end = Offset(size.width, size.height)
            )

            // 绘制圆框
            val strokeWidth = 10.dp.toPx()
            val radius = (size.minDimension / 2) - (strokeWidth / 2)
            val centerX = size.width / 2
            val centerY = size.height / 2

            drawCircle(
                brush = gradient,
                center = Offset(centerX, centerY),
                radius = radius,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                alpha = 1f,
            )
        }
    }
}

@Composable
fun WashingCircleBg() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)) {
            val radius = size.minDimension / 2
            val centerX = size.width / 2
            val centerY = size.height / 2

            drawCircle(
                color = Color.Gray.copy(0.3f),
                center = Offset(centerX, centerY),
                radius = radius,
                style = Stroke(width = 1.dp.toPx())
            )
        }
    }
}

@Composable
fun WashingMachineActionMenu(
    modifier: Modifier,
    modeIndex: MutableState<Int>,
    rotating: MutableState<Boolean>
) {

    val color = if (modeIndex.value > -1) washingMachineModes[modeIndex.value].color else Color.Black

    val sizeCurrent by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
        ) {
        ShadeRadioButton(
            isVector = false,
            onSelectedChanged = {},
            size = 18.dp,
            painter = painterResource(id = R.drawable.point),
            cornerRadius = 20.dp,
            iconPadding = 3.dp,
            offset = 10f,
            iconColor = color.copy(alpha = sizeCurrent)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ShadeRadioButton(
            size = 30.dp,
            painter = painterResource(id = R.drawable.power),
            iconColor = DimestIconColor,
            cornerRadius = 10.dp,
            iconPadding = 5.dp,
            isVector = false,
            onSelectedChanged = {},
            offset = 10f
        )
        Spacer(modifier = Modifier.height(20.dp))
        ShadeRadioButton(
            isVector = false,
            onSelectedChanged = {},
                    size = 30.dp,
            painter = painterResource(id = R.drawable.water_drop),
            iconColor = DimestIconColor,
            cornerRadius = 10.dp,
            iconPadding = 5.dp,
            offset = 10f
        )
        Spacer(modifier = Modifier.height(20.dp))
        ShadeRadioButton(
            isVector = false,
            onSelectedChanged = {
                rotating.value = it
            },
            size = 30.dp,
            painter = painterResource(id = R.drawable.stop),
            iconColor = DimestIconColor,
            cornerRadius = 10.dp,
            iconPadding = 2.dp,
            offset = 10f,
            selected = rotating.value
        )
    }
}

@Composable
fun WashingMachineMode(modeIndex:MutableState<Int>) {

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    val cardWidth = (screenWidthDp - 20.dp - 15.dp * 2)/8*3
    val cardHeight = cardWidth/6*7

    LazyRow(
        modifier = Modifier.padding(20.dp,0.dp,0.dp,0.dp)
    ){
        itemsIndexed(washingMachineModes){index, item->
            ShadeCard(
                modifier = Modifier.padding(5.dp,0.dp,15.dp,0.dp),
                onClick = {
                    modeIndex.value = index
                },
                cornerRadius = 10.dp,
                selectedEnable = true
            ) {
                Box(modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight),
                ){
                    Box(
                        modifier = Modifier
                            .offset(10.dp, 10.dp)
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(item.color.copy(alpha = 0.2f))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.point),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(item.color),
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.Center)
                        )
                    }

                    Column(modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 20.dp)
                        .align(Alignment.BottomStart)) {
                        Text(
                            text = item.title,
                            color = DimestTitleColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.time,
                            fontSize = 12.sp,
                            color = DimestSecondColor
                        )
                    }

                }


            }
        }
    }

}
