package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorLightOrange
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@Composable
fun ShadeSlider(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset:Float = 10f,
    progressBarHeight : Dp = 6.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    progress:Float = 0f,
    maxProgress:Float = 100f,
    progressColor:Color = shadowColorLightOrange,
    onProgress:(progress:Float)->Unit
){
    val TAG = "ShadeSlider"


    var sliderWidth by remember {
        mutableStateOf(0f)
    }
    var currentProgress by remember {
        mutableStateOf(progress)
    }
    LaunchedEffect(progress) { // 使用LaunchedEffect监听offset的变化
        currentProgress = progress
    }
    val paddingHorizontal = 20.dp
    val paddingHorizontalPx = with(LocalDensity.current){paddingHorizontal.toPx()}

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = paddingHorizontal)
        .height(progressBarHeight * 5)
        .onGloballyPositioned { coordinates ->
            sliderWidth = coordinates.size.width.toFloat() - paddingHorizontalPx
        }) {
        //进度条背景
        Card(
            modifier = Modifier
                .foregroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    offset,
                    progressBarHeight / 2,
                )
                .align(Alignment.Center),

            shape = RoundedCornerShape(progressBarHeight/2),
            elevation = 0.dp
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(progressBarHeight))
        }

        //当前进度，并设定颜色颜色
        Card(
            Modifier.align(Alignment.CenterStart),
            shape = RoundedCornerShape(CornerSize(progressBarHeight/2),  CornerSize(0),CornerSize(0),CornerSize(progressBarHeight/2)),
        ) {
            Box(
                Modifier
                    .width((currentProgress/LocalDensity.current.density).dp)
                    .height(progressBarHeight)
                    .background(progressColor),

                )
        }


        //图标
        val outCircleSize = progressBarHeight * 3
        val inCircleSize = progressBarHeight * 1.5f
        Card(
            modifier = Modifier
                .offset { IntOffset(currentProgress.toInt(), 0) }
                .backgroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius * 2,
                    lightSource,
                    offset ,
                    outCircleSize / 2,
                )
                .align(Alignment.CenterStart)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        currentProgress += delta
                        currentProgress = currentProgress.coerceIn(0f, sliderWidth)

                        onProgress.invoke(currentProgress/sliderWidth * maxProgress)
                        Log.e(TAG,"currentProgress:$currentProgress sliderWidth：$sliderWidth")
                    }
                ),
            shape = RoundedCornerShape(outCircleSize/2),
            elevation = 0.dp
        ) {
            Box(
                Modifier
                    .background(backgroundColor)
                    .size(outCircleSize)
            ) {

                Card(
                    modifier = Modifier
                        .foregroundShadow(
                            shadowColorLight,
                            shadowColorDark,
                            blurRadius,
                            lightSource,
                            offset,
                            inCircleSize / 2,
                        )
                        .align(Alignment.Center),
                    shape = RoundedCornerShape(inCircleSize/2),
                    elevation = 0.dp
                ) {
                    Box(
                        Modifier
                            .background(backgroundColor)
                            .size(inCircleSize))

                }
            }

        }

    }

}