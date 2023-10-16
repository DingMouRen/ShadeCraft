package com.dingmouren.shadecraft.ui.page.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.ConstantColor.shadowColorLightOrange
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.widgets.ShadeCircleProgressConvex
import com.dingmouren.shadecraft.ui.widget.TitleSurface

@Composable
fun ShadeCircleProgressConvexPage(){
    TitleSurface(title = "ShadeCircleProgressConvex") {

        val configuration = LocalConfiguration.current
        val currentOrientation = configuration.orientation

        when (currentOrientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                // 显示横屏布局
               Box(modifier = Modifier.fillMaxSize()){
                   Row(Modifier.wrapContentSize().align(Alignment.Center)) {
                       Spacer(modifier = Modifier.height(30.dp))
                       ShadeCircleProgressConvex(
                           shape = Shape.Circle,
                           progress = 50,
                           progressBgColor = ConstantColor.NeumorphismLightBackgroundColor,
                           progressColor = shadowColorLightOrange,
                           size = 150.dp,
                           fontSize = 36.sp
                       )
                       Spacer(modifier = Modifier.height(10.dp))
                       ShadeCircleProgressConvex(
                           shape = Shape.Circle,
                           progress = 50,
                           progressBgColor = ConstantColor.NeumorphismLightBackgroundColor,
                           progressColor = shadowColorLightOrange,
                           size = 150.dp,
                           fontSize = 36.sp,
                           showProgressText = false,
                           showEndCirclePoint = true,
                           borderWidth = 5.dp,
                           progressColorGradient = true
                       )
                       Spacer(modifier = Modifier.height(10.dp))
                       ShadeCircleProgressConvex(
                           shape = Shape.Circle,
                           progress = 50,
                           progressBgColor = Color.Gray.copy(alpha = 0.1f),
                           progressColor = shadowColorLightOrange,
                           size = 150.dp,
                           fontSize = 36.sp,
                       )
                   }
               }
            }
            else -> {
                // 显示竖屏布局
                Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(30.dp))
                    ShadeCircleProgressConvex(
                        shape = Shape.Circle,
                        progress = 50,
                        progressBgColor = ConstantColor.NeumorphismLightBackgroundColor,
                        progressColor = shadowColorLightOrange,
                        size = 150.dp,
                        fontSize = 36.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ShadeCircleProgressConvex(
                        shape = Shape.Circle,
                        progress = 50,
                        progressBgColor = ConstantColor.NeumorphismLightBackgroundColor,
                        progressColor = shadowColorLightOrange,
                        size = 150.dp,
                        fontSize = 36.sp,
                        showProgressText = false,
                        showEndCirclePoint = true,
                        borderWidth = 5.dp,
                        progressColorGradient = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    ShadeCircleProgressConvex(
                        shape = Shape.Circle,
                        progress = 50,
                        progressBgColor = Color.Gray.copy(alpha = 0.1f),
                        progressColor = shadowColorLightOrange,
                        size = 150.dp,
                        fontSize = 36.sp,
                    )
                }
            }
        }


    }
}