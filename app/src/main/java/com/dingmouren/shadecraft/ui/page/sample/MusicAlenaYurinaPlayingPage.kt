package com.dingmouren.shadecraft.ui.page.sample

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.widgets.ShadeCard
import com.dingmouren.shadecraft.core.widgets.ShadeCircleProgressConvex
import com.dingmouren.shadecraft.core.widgets.ShadeImageButton
import com.dingmouren.shadecraft.route.LocalNavController

@Composable
fun MusicAlenaYurinaPlayingPage(musicIndex:Int){
    val music = listMusic[musicIndex]
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current
    val screenWidthPx = with(density) { screenWidthDp.toPx() }
    val screenHeightPx = with(density) { screenHeightDp.toPx() }

    var isPlaying by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column() {
            Spacer(modifier = Modifier.height(35.dp))
            MusicAlenaYurinaPlayingTopBar()
            MusicAlenaYurinaPlayingImgProgress(screenWidthDp,music,isPlaying)
            MusicAlenaYurinaPlayingMusicInfo(music,isPlaying)
            Spacer(modifier = Modifier.height(30.dp))
            MusicAlenaYurinaPlayingActionButtons(isPlaying)
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            MusicAlenaYurinaPlayingBottom()
        }

    }
}

@Composable
fun MusicAlenaYurinaPlayingBottom() {
    Row(modifier = Modifier
        .padding(0.dp,0.dp,0.dp,40.dp)
        .fillMaxWidth()
        .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
            
        ){
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.loop_list),
                contentDescription = null ,
                Modifier.size(35.dp),
                colorFilter = ColorFilter.tint(MusicAlenaYurinaPrimaryColor)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.heart_empty),
                contentDescription = null ,
                Modifier.size(35.dp),
                colorFilter = ColorFilter.tint(MusicAlenaYurinaSecondColor)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.random_list),
                contentDescription = null ,
                Modifier.size(35.dp),
                colorFilter = ColorFilter.tint(MusicAlenaYurinaSecondColor)
            )
        }

    }

}

@Composable
fun MusicAlenaYurinaPlayingActionButtons(isPlaying: Boolean) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

    ) {


        Box(modifier = Modifier.weight(1f)){
            ShadeImageButton(
                size = 35.dp,
                painter = painterResource(id = R.drawable.skip_previous),
                iconColor = MusicAlenaYurinaPrimaryColor,
                shape = Shape.Circle,
                onClick = {},
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(modifier = Modifier.weight(1f)){
            ShadeImageButton(
                size = 70.dp,
                painter = painterResource(id = R.drawable.play),
                iconColor = MusicAlenaYurinaPrimaryColor,
                shape = Shape.Circle,
                onClick = {},
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Box(modifier = Modifier.weight(1f)){
            ShadeImageButton(
                size = 35.dp,
                painter = painterResource(id = R.drawable.skip_next),
                iconColor = MusicAlenaYurinaPrimaryColor,
                shape = Shape.Circle,
                onClick = {},
                modifier = Modifier.align(Alignment.Center)
            )
        }

    }

}

@Composable
fun MusicAlenaYurinaPlayingMusicInfo(music: MusicAlenaYurinaBean, isPlaying: Boolean) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = music.song,
            color = MusicAlenaYurinaTitleColor,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
                
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = music.author,
            color = MusicAlenaYurinaSubColor,
            fontSize = 15.sp,

            )
    }

}

@Composable
fun MusicAlenaYurinaPlayingImgProgress(
    screenWidthDp: Dp,
    music: MusicAlenaYurinaBean,
    isPlaying: Boolean
) {
    val imgSize = screenWidthDp - 20.dp - 20.dp - 70.dp

    val angle by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(60 * 1000, easing = LinearEasing)
        )
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(screenWidthDp)
        .padding(20.dp)
    ){
        ShadeCircleProgressConvex(
            shape = Shape.Circle,
            progress = 30,
            progressBgColor = MusicAlenaYurinaGrayColor.copy(alpha = 0.1f),
            progressColor = MusicAlenaYurinaPrimaryColor,
            size = screenWidthDp - 20.dp,
            showProgressText = false,
            showEndCirclePoint = true,
            endCirclePointColor = MusicAlenaYurinaPrimaryColor,
            borderWidth = 2.dp,
            offset = 0f,
            progressColorGradient = true
        )

        ShadeCard(
            onClick = {  },
            modifier = Modifier.align(Alignment.Center),
            cornerRadius = imgSize/2,
            offset = 15f
        ) {
            Image(
                painter = painterResource(id = music.img),
                contentDescription = null,
                modifier = Modifier
                    .size(imgSize)
                    .clip(CircleShape)
                    .graphicsLayer { rotationZ = if (isPlaying) angle else 0f }

            )
        }
    }

}

@Composable
fun MusicAlenaYurinaPlayingTopBar() {
    val navController = LocalNavController.current
    Box(
        modifier = Modifier
            .clickable (
                interactionSource = remember { MutableInteractionSource() },
                indication = null
                    ){ navController.popBackStack() }
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Image(painter = painterResource(
            id = R.drawable.arrow_left_long),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MusicAlenaYurinaPrimaryColor),
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterStart),

        )
        Image(painter = painterResource(
            id = R.drawable.adjust),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MusicAlenaYurinaPrimaryColor),
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterEnd)

        )
    }
}
