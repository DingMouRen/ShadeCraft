package com.dingmouren.shadecraft.ui.page.widget

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.widgets.ShadeCalorVerticalProgress
import com.dingmouren.shadecraft.ui.page.sample.*
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeCalorVerticalProgressPage(){

    val progress = remember { mutableStateOf(0) }

    TitleSurface(title = "ShadeCalorVerticalProgress") {

        val configuration = LocalConfiguration.current
        val currentOrientation = configuration.orientation

        when (currentOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {

                Row(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Row(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 30.dp, vertical = 20.dp)
                        .wrapContentHeight()) {
                        Box(modifier = Modifier.weight(1f)){
                            Column(
                                modifier = Modifier.wrapContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ShadeCalorVerticalProgress(
                                    progressColor = FatemeSouriPink,
                                    width = 35.dp,
                                    height = 200.dp,
                                    progress = 60,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 7",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 40,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 8",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 0,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 9",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 80,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 10",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 50,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 11",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 30.dp, vertical = 20.dp)
                        .wrapContentHeight()) {
                        Box(modifier = Modifier.weight(1f)){
                            Column(
                                modifier = Modifier.wrapContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ShadeCalorVerticalProgress(
                                    progressColor = FatemeSouriPink,
                                    width = 35.dp,
                                    height = 200.dp,
                                    progress = 60,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 7",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 40,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 8",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 0,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 9",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 80,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 10",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 50,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 11",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }

                }
            }
            else -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 20.dp)
                        .wrapContentHeight()) {
                        Box(modifier = Modifier.weight(1f)){
                            Column(
                                modifier = Modifier.wrapContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ShadeCalorVerticalProgress(
                                    progressColor = FatemeSouriPink,
                                    width = 35.dp,
                                    height = 200.dp,
                                    progress = 60,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 7",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 40,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 8",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 0,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 9",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 80,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 10",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 50,
                                    cornerRadius = 35.dp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 11",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp, vertical = 20.dp)
                        .wrapContentHeight()) {
                        Box(modifier = Modifier.weight(1f)){
                            Column(
                                modifier = Modifier.wrapContentSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ShadeCalorVerticalProgress(
                                    progressColor = FatemeSouriPink,
                                    width = 35.dp,
                                    height = 200.dp,
                                    progress = 60,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 7",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 40,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 8",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 0,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 9",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 80,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 10",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
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
                                    height = 200.dp,
                                    progress = 50,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = "Otc 11",
                                    color = FatemeSouriSecondColor,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }

                }
            }
        }


    }
}

