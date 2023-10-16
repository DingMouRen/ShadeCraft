package com.dingmouren.shadecraft.core.widgets

import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.Convexity
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@Composable
fun ShadeSearchBar(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 12.sp,
    height: Dp = 50.dp,
    onSearchClick: (String) -> Unit,
    onRightIconClick:()->Unit,
    @DrawableRes leftIcon:  Int? = null,
    @DrawableRes rightIcon:  Int? = null,
    convexity: Convexity = Convexity.CONVEX,
    imgSize :Dp = 30.dp,
    leftIconTintColor: ColorFilter? = null,
    placeholderText:String?=null,
    placeholderTextColor:Color = Color.Gray,
) {
    var text by remember { mutableStateOf(value) }
    val convexityType by remember { mutableStateOf(convexity) }
    var currentOffsetBack by remember { mutableStateOf(offset) }
    var currentOffsetFore by remember { mutableStateOf(0f) }
    LaunchedEffect(offset) { // 使用LaunchedEffect监听offset的变化
        if (convexityType == Convexity.CONVEX){
            currentOffsetBack = offset
            currentOffsetFore = 0f

        }else{
            currentOffsetBack = 0f
            currentOffsetFore = offset
        }

    }
    Card(
        modifier = Modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffsetFore,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                currentOffsetBack,
                cornerRadius,
            ),

        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            Modifier
                .background(backgroundColor)
        ) {
            Row(
                Modifier.wrapContentSize().padding(horizontal = height/3),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leftIcon?.let {
                    Image(
                        painter = painterResource(id = leftIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(imgSize)
                            .height(imgSize),
                        contentScale = ContentScale.Inside,
                        colorFilter = leftIconTintColor

                    )
                }

                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .height(height),
                    value = text,
                    onValueChange = { newValue ->
                        text = newValue
                        onValueChange.invoke(newValue)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions {
                        onSearchClick.invoke(text)
                    },
                    placeholder = {
                        Text(
                            text = placeholderText.toString(),
                            fontSize = fontSize,
                            color = placeholderTextColor,
                            textAlign = TextAlign.Start,
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,

                    )
                rightIcon?.let {
                    Image(
                        painter = painterResource(id = rightIcon),
                        contentDescription = "",
                        modifier = Modifier
                            .width(imgSize)
                            .height(imgSize)
                            .clickable(//去除默认的水波纹效果
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onRightIconClick.invoke()
                            },
                        contentScale = ContentScale.Inside
                    )
                }

            }
        }

    }
}