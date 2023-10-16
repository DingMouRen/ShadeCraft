package com.dingmouren.shadecraft.ui.page.sample

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.widgets.*
import com.dingmouren.shadecraft.ext.toast
import com.dingmouren.shadecraft.route.LocalNavController
import com.dingmouren.shadecraft.route.RoutePath

val MusicAlenaYurinaTitleColor = Color(0xff711524)
val MusicAlenaYurinaSubColor = Color(0xffbc959a)
val MusicAlenaYurinaPrimaryColor = Color(0xfff78da1)
val MusicAlenaYurinaSecondColor = Color(0xfff1d4d8)
val MusicAlenaYurinaGrayColor = Color(0xffd7d7d5)

data class MusicAlenaYurinaBean(val song:String,val author:String, @DrawableRes val img : Int)

val listMusic = mutableListOf<MusicAlenaYurinaBean>(
    MusicAlenaYurinaBean("Forever after all","Luke Combs",R.drawable.music1),
    MusicAlenaYurinaBean("Just like magic","Ariana Grande",R.drawable.music2),
    MusicAlenaYurinaBean("What else is there","Moonbeam",R.drawable.music3),
    MusicAlenaYurinaBean("Tyler Herro","Jack Harlow",R.drawable.music4),
    MusicAlenaYurinaBean("Monster mash","Bobby \"Boris\" Pickett",R.drawable.music5),
    MusicAlenaYurinaBean("Positions","Ariana Grande",R.drawable.music6),
    MusicAlenaYurinaBean("Forever after all","Luke Combs",R.drawable.music1),
    MusicAlenaYurinaBean("Just like magic","Ariana Grande",R.drawable.music2),
    MusicAlenaYurinaBean("What else is there","Moonbeam",R.drawable.music3),
    MusicAlenaYurinaBean("Tyler Herro","Jack Harlow",R.drawable.music4),
    MusicAlenaYurinaBean("Monster mash","Bobby \"Boris\" Pickett",R.drawable.music5),
    MusicAlenaYurinaBean("Positions","Ariana Grande",R.drawable.music6),
)

@Composable
fun MusicAlenaYurinaPage(){
    var playingIndex by remember { mutableStateOf(-1) }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            MusicAlenaYurinaSearchBar()
            Spacer(modifier = Modifier.height(20.dp))
            MusicAlenaYurinaTabLayout(
                playingIndex = {
                    playingIndex = it
                }
            )
        }
        if (playingIndex > -1){
            Box(modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter)
                .padding(10.dp, 0.dp, 10.dp, 20.dp)
            ){
                MusicFloatBar(listMusic[playingIndex])
            }
        }
    }
}

@Composable
fun MusicAlenaYurinaSearchBar() {
    ShadeSearchBar(
        value = "",
        onValueChange = {

        },
        cornerRadius = 25.dp,
        onSearchClick = {
            App.context().toast("搜索:$it")
        },
        onRightIconClick = {
            App.context().toast("删除")
        },
        convexity = Convexity.CONCAVE,
        leftIcon = R.drawable.search,
        imgSize = 30.dp,
        leftIconTintColor = ColorFilter.tint(color = Color(0xffd7d7d5)),
        placeholderText = "The name of the song or artist...",
        placeholderTextColor = Color(0xffd7d7d5),
        fontSize = 14.sp,

    )
}


@Composable
fun MusicAlenaYurinaTabLayout(playingIndex: (index:Int) -> Unit) {
    var selectedIndex by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ShadeTabPathBar(
            tabSize = 3,
            tabHeight = 50.dp,
            selectedIndex = selectedIndex,
            radiusSize = 20.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Albums",
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { selectedIndex = 0 },
                textAlign = TextAlign.Center,
                color = if (selectedIndex == 0) MusicAlenaYurinaTitleColor else MusicAlenaYurinaSubColor,
                fontWeight = if (selectedIndex == 0) FontWeight.Bold else FontWeight.Normal


            )
            Text(
                text = "Popular",
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { selectedIndex = 1 },
                textAlign = TextAlign.Center,
                color = if (selectedIndex == 1) MusicAlenaYurinaTitleColor else MusicAlenaYurinaSubColor,
                fontWeight = if (selectedIndex == 1) FontWeight.Bold else FontWeight.Normal


            )
            Text(
                text = "Favourites",
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        selectedIndex = 2
                    },
                textAlign = TextAlign.Center,
                color = if (selectedIndex == 2) MusicAlenaYurinaTitleColor else MusicAlenaYurinaSubColor,
                fontWeight = if (selectedIndex == 2) FontWeight.Bold else FontWeight.Normal


            )

        }
        MusicAlenaYurinaList(playingIndex = playingIndex )


    }
}

@Composable
fun MusicFloatBar(music: MusicAlenaYurinaBean) {
    ShadeCard(
        onClick = { },
        cornerRadius = 20.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 20.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${music.song} - ${music.author}",
                    fontSize = 14.sp,
                    color = MusicAlenaYurinaTitleColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )

                ShadeCard(
                    onClick = {
                    },
                    cornerRadius = 40.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.play) ,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp),
                        colorFilter = ColorFilter.tint(MusicAlenaYurinaPrimaryColor)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                ShadeCard(
                    onClick = {
                    },
                    cornerRadius = 40.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.skip_next) ,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(8.dp),
                        colorFilter = ColorFilter.tint(MusicAlenaYurinaPrimaryColor)
                    )
                }

            }
            Box(modifier = Modifier.padding(0.dp,0.dp,90.dp,0.dp)){
                ShadeLinearProgressIndicator(
                    progressBarWidth = 2.dp,
                    maxProgress = 100f,
                    progress = 30f,
                    shadowColorLightProgress = Color(0xffffa2b9),
                    shadowColorDarkProgress = Color(0xffd27889),
                    progressColor = MusicAlenaYurinaPrimaryColor

                )
            }


        }
    }

}

@Composable
fun MusicAlenaYurinaList(
    playingIndex: (index:Int) -> Unit,
) {
    var indexPlaying by remember { mutableStateOf(-1) }
    val navController = LocalNavController.current
    LazyColumn(
        modifier = Modifier
            .wrapContentSize()
            .padding(0.dp, 50.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        itemsIndexed(listMusic) {index, item ->
            Box(modifier = Modifier.wrapContentSize()){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        navController.navigate(RoutePath.MusicAlenaYurinaPlayingPage.replace("{musicIndex}", index.toString()))
                    }
                    .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = item.img),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)

                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp, 0.dp, 0.dp, 0.dp),
                    ) {
                        Text(
                            text = item.song,
                            fontSize = 14.sp,
                            color = MusicAlenaYurinaTitleColor
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = item.author,
                            fontSize = 14.sp,
                            color = MusicAlenaYurinaSubColor
                        )
                    }
                    ShadeCard(
                        onClick = {
                            indexPlaying = if (indexPlaying == index){
                                -1
                            }else{
                                index
                            }

                            playingIndex.invoke(indexPlaying)
                                  },
                        cornerRadius = 40.dp
                    ) {
                        Image(
                            painter = if ( indexPlaying == index) painterResource(id = R.drawable.play) else painterResource(id = R.drawable.stop),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(8.dp),
                            colorFilter = ColorFilter.tint(MusicAlenaYurinaPrimaryColor)
                        )
                    }
                }
                Divider(color = Color(0xffd7d7d5), modifier = Modifier.padding(20.dp,80.dp,20.dp,0.dp))
            }


        }
    }
}
