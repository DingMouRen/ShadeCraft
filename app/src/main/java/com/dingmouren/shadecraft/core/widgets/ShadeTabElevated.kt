package com.dingmouren.shadecraft.core.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.Shape

@Composable
fun ShadeTabElevated(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    backgroundColor: Color = Color(0xffF0F0F3),
    unselectedIconColor:Color = Color(0xffE6E6E6),
    selectedIconColor:Color = Color(0xffFA8181),
    onSelectedChange:(index:Int)->Unit,
    initTabIndex : Int = 0
){
    BoxWithConstraints(modifier = modifier
    ) {
        val maxWidthPx = with(LocalDensity.current){maxWidth.toPx()}
        val maxHeightPx = with(LocalDensity.current){maxHeight.toPx()}
        val imgSize = 30.dp
        val currentIndex = remember { mutableStateOf(initTabIndex) }
        val offsetShadowDefault = 20f
        val offsetUp = maxHeight / 2
        //上下移动动画
        val animateOffset_0 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 0) offsetUp else 0.dp,
            animationSpec = tween( durationMillis = 300)
        )
        val animateOffset_1 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 1) offsetUp else 0.dp,
            animationSpec = tween( durationMillis = 300)
        )
        val animateOffset_2 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 2) offsetUp else 0.dp,
            animationSpec = tween( durationMillis = 300)
        )
        val animateOffset_3 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 3) offsetUp else 0.dp,
            animationSpec = tween( durationMillis = 300)
        )
        val animateOffset_4 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 4) offsetUp else 0.dp,
            animationSpec = tween( durationMillis = 300)
        )
        //大小动画
        val animateSize_0 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 0) imgSize * 1.2f else imgSize ,
            animationSpec = tween( durationMillis = 300),
            finishedListener = {

            }
        )
        val animateSize_1 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 1) imgSize * 1.2f else imgSize ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateSize_2 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 2) imgSize * 1.2f else imgSize ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateSize_3 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 3) imgSize * 1.2f else imgSize ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateSize_4 :Dp by animateDpAsState(
            targetValue = if (currentIndex.value == 4) imgSize * 1.2f else imgSize ,
            animationSpec = tween( durationMillis = 300)
        )
        //色值动画
        val animateColor_0 :Color by animateColorAsState(
            targetValue = if (currentIndex.value == 0) selectedIconColor else unselectedIconColor ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateColor_1 :Color by animateColorAsState(
            targetValue = if (currentIndex.value == 1) selectedIconColor else unselectedIconColor ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateColor_2 :Color by animateColorAsState(
            targetValue = if (currentIndex.value == 2) selectedIconColor else unselectedIconColor ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateColor_3 :Color by animateColorAsState(
            targetValue = if (currentIndex.value == 3) selectedIconColor else unselectedIconColor ,
            animationSpec = tween( durationMillis = 300)
        )
        val animateColor_4 :Color by animateColorAsState(
            targetValue = if (currentIndex.value == 4) selectedIconColor else unselectedIconColor ,
            animationSpec = tween( durationMillis = 300)
        )

        Row(modifier = Modifier
            .align(Alignment.Center)
            .fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)){
                ShadeImageButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(0.dp, -animateOffset_0),
                    onClick = {
                        currentIndex.value = 0
                        onSelectedChange.invoke(0)
                    },
                    size = if (currentIndex.value == 0) imgSize * 1.2f else imgSize,
//                    size = animateSize_0,
                    painter = painterResource(id = R.drawable.time),
                    shape = Shape.Circle,
                    iconColor = animateColor_0,
                    offset = if (currentIndex.value == 0) offsetShadowDefault else 0f,
                    backgroundColor = if (currentIndex.value == 0) backgroundColor else Color.White
                )
            }
            Box(modifier = Modifier.weight(1f)){
                ShadeImageButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(0.dp, -animateOffset_1),
                    onClick = {
                        currentIndex.value = 1
                        onSelectedChange.invoke(1)
                    },
                    size = if (currentIndex.value == 1) imgSize * 1.2f else imgSize,
//                    size = animateSize_1,
                    painter = painterResource(id = R.drawable.double_bed),
                    shape = Shape.Circle,
                    iconColor = animateColor_1,
                    offset = if (currentIndex.value == 1) offsetShadowDefault else 0f,
                    backgroundColor = if (currentIndex.value == 1) backgroundColor else Color.White
                )
            }
            Box(modifier = Modifier.weight(1f)){
                ShadeImageButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(0.dp, -animateOffset_2),
                    onClick = {
                        currentIndex.value = 2
                        onSelectedChange.invoke(2)
                    },
                    size = if (currentIndex.value == 2) imgSize * 1.2f else imgSize,
//                    size = animateSize_2,
                    painter = painterResource(id = R.drawable.net),
                    shape = Shape.Circle,
                    iconColor = animateColor_2,
                    offset = if (currentIndex.value == 2) offsetShadowDefault else 0f,
                    backgroundColor = if (currentIndex.value == 2) backgroundColor else Color.White
                )
            }
            Box(modifier = Modifier.weight(1f)){
                ShadeImageButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(0.dp, -animateOffset_3),
                    onClick = {
                        currentIndex.value = 3
                        onSelectedChange.invoke(3)
                    },
                    size = if (currentIndex.value == 3) imgSize * 1.2f else imgSize,
//                    size = animateSize_3,
                    painter = painterResource(id = R.drawable.alarm_clock),
                    shape = Shape.Circle,
                    iconColor = animateColor_3,
                    offset = if (currentIndex.value == 3) offsetShadowDefault else 0f,
                    backgroundColor = if (currentIndex.value == 3) backgroundColor else Color.White
                )
            }
            Box(modifier = Modifier.weight(1f)){
                ShadeImageButton(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(0.dp, -animateOffset_4),
                    onClick = {
                        currentIndex.value = 4
                        onSelectedChange.invoke(4)
                    },
                    size = if (currentIndex.value == 4) imgSize * 1.2f else imgSize,
//                    size = if (currentIndex.value == 4) animateSize_4 else imgSize,
                    painter = painterResource(id = R.drawable.second_watch),
                    shape = Shape.Circle,
                    iconColor = animateColor_4,
                    offset = if (currentIndex.value == 4) offsetShadowDefault else 0f,
                    backgroundColor = if (currentIndex.value == 4) backgroundColor else Color.White
                )
            }
        }
    }
}