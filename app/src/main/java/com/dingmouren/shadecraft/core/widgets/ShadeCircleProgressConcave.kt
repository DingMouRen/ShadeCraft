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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import org.w3c.dom.Text
import kotlin.math.cos
import kotlin.math.sin
//凹
@Composable
fun ShadeCircleProgressConcave(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    borderWidth :Dp = 20.dp,
    progress: Int,
    progressBgColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    progressColor: Color,
    size:Dp,
    text: AnnotatedString? = null,

    ){
    val circleInnerSize = size -  borderWidth - borderWidth
    Box(modifier = modifier.size(size)){
        Card(
            modifier = Modifier
                .align(Alignment.Center),
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(size / 2),
            elevation = 0.dp
        ) {
            Box(
                Modifier
                    .background(Color.Transparent)
                    .size(size),
                contentAlignment = Alignment.Center
            ) {
                ShadeCard(
                    onClick = {  },
                    modifier = Modifier.align(Alignment.Center),
                    cornerRadius = circleInnerSize,
                    blurRadius = 30f,
                    offset = 15f
                ) {
                    Box(modifier = Modifier.size(circleInnerSize).align(Alignment.Center)){
                        if (text != null){
                            Text(text = text, modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center)
                        }

                    }
                }

                Canvas(
                    modifier = Modifier
                        .size(size)
                        .background(Color.Transparent)
                        .padding(borderWidth / 2),
                    onDraw = {

                        //进度
                        drawArc(
                            color = progressColor,
                            startAngle = -90f,
                            sweepAngle = 360f * progress / 100,
                            useCenter = false,
                            style = Stroke(borderWidth.toPx(), cap = StrokeCap.Round)
                        )

                    }
                )


            }

        }

    }

}

