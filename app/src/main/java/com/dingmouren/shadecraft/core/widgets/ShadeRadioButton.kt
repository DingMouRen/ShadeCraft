package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.core.BlurProvider
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.ConstantColor.NeumorphismLightBackgroundColor
import com.dingmouren.shadecraft.core.ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK
import com.dingmouren.shadecraft.core.ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.Shape
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShadeRadioButton(
    modifier: Modifier = Modifier,
    shadowColorLight: Color = Color(THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 22f,
    cornerRadius: Dp = 0.dp,
    backgroundColor: Color = NeumorphismLightBackgroundColor,
    size:Dp,
    imageVector: ImageVector? = null,
    isVector:Boolean ,
    painter: Painter? = null,
    iconColor:Color = Color.Black,
    iconPadding:Dp = 0.dp,
    onSelectedChanged: (selected:Boolean) -> Unit,
    selected: Boolean = false,
    selectedEnable:Boolean = true
) {
    val selectedPrivate = remember { mutableStateOf(selected) }
    selectedPrivate.value = selected



    Card(
        modifier = modifier
            .foregroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                if (selectedPrivate.value) 22f else 0f,
                cornerRadius,
            )
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                if (selectedPrivate.value) 0f else 11f,
                cornerRadius,
            )
            .pointerInteropFilter {
                if (selectedEnable) {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selectedPrivate.value = !selectedPrivate.value
                            onSelectedChanged.invoke(selectedPrivate.value)

                        }
                        MotionEvent.ACTION_UP -> {

                        }
                    }
                }
                true
            },
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .padding(size / 5)
        ) {
            if (imageVector != null || painter != null){
                if(isVector){
                    Icon(imageVector = imageVector!!,
                        contentDescription = "ShadeImageButton",
                        modifier = Modifier
                            .size(size)
                            .align(Alignment.Center)
                            .padding(iconPadding),
                        tint = iconColor
                    )
                }else{
                    Image(
                        painter = painter!!,
                        contentDescription = null,
                        modifier = Modifier
                            .size(size)
                            .align(Alignment.Center)
                            .padding(iconPadding),
                        colorFilter = ColorFilter.tint(iconColor)
                    )
                }
            }


        }

    }

}

