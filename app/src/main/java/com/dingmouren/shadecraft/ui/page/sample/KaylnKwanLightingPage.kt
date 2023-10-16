package com.dingmouren.shadecraft.ui.page.sample

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.widgets.ShadeCard
import com.dingmouren.shadecraft.core.widgets.ShadeLight
import com.dingmouren.shadecraft.core.widgets.ShadeScaleOption

@Composable
fun KaylnKwanLightingPage(){

    val toggle = remember { mutableStateOf(false) }
    val colorIndex = remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            KaylnKwanLightingTopBar()
            KaylnKwanLightingInfo()
            KaylnKwanLighting(colorIndex,toggle)
            KaylnKwanLightingBottomBar(toggle)
        }
    }
}

@Composable
fun KaylnKwanLightingTopBar() {
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
fun KaylnKwanLightingInfo() {
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "Lighting",
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        color = KaylnKwanTitleColor
    )
    Text(
        text = "Master Bedroom",
        fontSize = 16.sp,
        color = KaylnKwanSubColor
    )

}

@Composable
fun KaylnKwanLighting(colorIndex: MutableState<Int>, toggle: MutableState<Boolean>) {
    Spacer(modifier = Modifier.height(50.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
        ) {
        ShadeScaleOption(
            modifier = Modifier
                .width(100.dp)
                .height(380.dp),
            scales = 4,
            onScaleChange = {
                colorIndex.value = it
            }
        )
        Box(modifier = Modifier.wrapContentSize()){
            ShadeLight(
                modifier = Modifier
                    .size(260.dp),

                )
            BoxWithConstraints(modifier = Modifier
                .offset(70.dp,30.dp)
                .size(120.dp)
            ){
                Canvas(modifier = Modifier.size(maxWidth,maxHeight)){
                    val paint = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
                        paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                            .also {nativePaint: NativePaint ->
                                nativePaint.isAntiAlias = true //设置抗锯齿
                                nativePaint.isDither = true //开启防抖
                                nativePaint.color = if (toggle.value)getLightingColor(colorIndex) else ConstantColor.NeumorphismLightBackgroundColor.toArgb() //设置画笔颜色
                                nativePaint.style = android.graphics.Paint.Style.FILL
                                nativePaint.maskFilter = BlurMaskFilter(150f, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                            }
                    }

                    drawIntoCanvas {
                        it.nativeCanvas.drawCircle(
                            maxWidth.toPx()/2,
                            maxWidth.toPx()/2,
                            maxWidth.toPx()/2,
                            paint.asFrameworkPaint())
                    }

                }
            }
        }

    }
}

fun getLightingColor(colorIndex: MutableState<Int>): Int {
    var color = KaylnKwanYellowColor
    when(colorIndex.value){
        0->{
            color = KaylnKwanYellowColor
        }
        1->{
            color = KaylnKwanOrageColor
        }
        2->{
            color = KaylnKwanBlueColor
        }
        else->{
            color = KaylnKwanPinkColor
        }
    }
    return color.toArgb()
}

@Composable
fun KaylnKwanLightingBottomBar(toggle: MutableState<Boolean>) {
    Spacer(modifier = Modifier.height(40.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f) ){
            ShadeCard(
                modifier = Modifier.align(Alignment.Center),
                onClick = {},
                cornerRadius = 10.dp,
                selectedEnable = true,
                selected = true,
                backgroundColor = KaylnKwanYellowColor,
                offset = 15f,

                ){
                Box(modifier = Modifier.size(16.dp))
            }
        }
        Box(modifier = Modifier.weight(1f) ){
            ShadeCard(
                modifier = Modifier.align(Alignment.Center),
                onClick = {},
                cornerRadius = 10.dp,
                selectedEnable = true,
                selected = true,
                backgroundColor = KaylnKwanOrageColor,
                offset = 15f,

                ){
                Box(modifier = Modifier.size(16.dp))
            }
        }
        Box(modifier = Modifier.weight(4f) ){
            ShadeCard(
                modifier = Modifier.align(Alignment.Center),
                onClick = {
                    toggle.value = !toggle.value
                },
                cornerRadius = 10.dp,
                selectedEnable = true,
                selected = toggle.value,
                offset = 20f,
                blurRadius = 20f,
                ){
                Box(modifier = Modifier.size(80.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.power),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(KaylnKwanIconColor),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(40.dp)
                    )
                }
            }
        }

        Box(modifier = Modifier.weight(1f) ){
            ShadeCard(
                modifier = Modifier.align(Alignment.Center),
                onClick = {},
                cornerRadius = 10.dp,
                selectedEnable = true,
                selected = true,
                backgroundColor = KaylnKwanBlueColor,
                offset = 15f,

                ){
                Box(modifier = Modifier.size(16.dp))
            }
        }
        Box(modifier = Modifier.weight(1f) ){
            ShadeCard(
                modifier = Modifier.align(Alignment.Center),
                onClick = {},
                cornerRadius = 10.dp,
                selectedEnable = true,
                selected = true,
                backgroundColor = KaylnKwanPinkColor,
                offset = 15f,

                ){
                Box(modifier = Modifier.size(16.dp))
            }
        }


    }
}
