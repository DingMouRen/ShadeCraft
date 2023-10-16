package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import kotlin.math.cos
import kotlin.math.sin
//凸
@Composable
fun ShadeCircleProgressConvex(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    shape:Int = Shape.Rectangle,
    borderWidth :Dp = 20.dp,//Shape.Circle中作为圆环宽度
    progress: Int,
    progressBgColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    progressColor: Color,
    size:Dp,
    fontSize: TextUnit = 20.sp,
    fontColor:Color = Color.Gray,
    showProgressText:Boolean = true,
    showEndCirclePoint:Boolean = false,
    endCirclePointColor:Color = progressColor,
    endCirclePointRadius:Dp = borderWidth * 2,
    progressColorGradient:Boolean = false,

){
    Box(modifier = modifier.size(size + borderWidth)){
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .backgroundShadow(
                    shadowColorLight,
                    shadowColorDark,
                    blurRadius,
                    lightSource,
                    offset,
                    cornerRadius,
                    shape = shape,
                    borderWidth = borderWidth
                ),
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(cornerRadius),
            elevation = 0.dp
        ) {
            Box(
                Modifier
                    .background(Color.Transparent)
                    .size(size),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier
                        .size(size)
                        .background(Color.Transparent)
                        .padding(borderWidth / 2),
                    onDraw = {
                        //背景
                        drawArc(
                            color = progressBgColor,
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(borderWidth.toPx(), cap = StrokeCap.Round)
                        )
                        //进度
                        if (progressColorGradient){
                            val sweepAngle = 360f * progress / 100
                            var startAngle = -90f
                            for(  angle in 0..sweepAngle.toInt()){
                                val alpha = 1f - angle / sweepAngle
                                Log.e("ShadeCircleProgressConvex", "比例: ${angle / sweepAngle} alpha:$alpha")
                                drawArc(
                                    color = progressColor.copy(alpha = if (alpha > 0.2f) alpha else 0.2f),
                                    startAngle = startAngle,
                                    sweepAngle = 1f,
                                    useCenter = false,
                                    style = Stroke(borderWidth.toPx(), cap = StrokeCap.Round)
                                )
                                startAngle += 1
                            }
                        }else{
                            drawArc(
                                color = progressColor,
                                startAngle = -90f,
                                sweepAngle = 360f * progress / 100,
                                useCenter = false,
                                style = Stroke(borderWidth.toPx(), cap = StrokeCap.Round)
                            )
                        }

                    }
                )
                if (showProgressText){
                    Text(
                        text = "$progress",
                        fontSize = fontSize,
                        color = fontColor
                    )
                }

            }

        }
        Box(
            Modifier
                .background(Color.Transparent)
                .size(size),
            contentAlignment = Alignment.Center
        ){
            Canvas(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(size)
                    .background(Color.Transparent)
                    .padding(borderWidth / 2),
                onDraw = {
                    //终点圆圈
                    if (showEndCirclePoint){
                        val radius = (this.size.width - borderWidth.toPx() )/2
                        val centerX = (this.size.width + borderWidth.toPx() )/ 2
                        val centerY = (this.size.width + borderWidth.toPx() )/ 2
                        val angleRadians = Math.toRadians(360.00 * progress / 100 - 90.00)
                        val endX = centerX + radius * cos(angleRadians)
                        val endY = centerY + radius * sin(angleRadians)
                        drawCircle(
                            color = endCirclePointColor,
                            center = Offset(endX.toFloat(), endY.toFloat()),
                            radius = endCirclePointRadius.toPx()
                        )

                    }


                }
            )
        }

    }

}

