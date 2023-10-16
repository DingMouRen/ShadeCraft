package com.dingmouren.shadecraft.core.widgets

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.ext.backgroundShadow
import com.dingmouren.shadecraft.core.ext.foregroundShadow

@Composable
fun ShadeTabRow(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius: Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset: Float = 10f,
    backgroundColor: Color = ConstantColor.NeumorphismLightBackgroundColor,
    cornerRadius: Dp = 0.dp,
    selectedTabIndex:Int = 0,//默认为0
    paddingDp:Dp = 5.dp,
    sliderColor:Color = ConstantColor.NeumorphismLightBackgroundColor,
    shadowColorLightSlider: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDarkSlider: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    tabs: @Composable @UiComposable () -> Unit,
//    tabIndexChangeListener:()->Unit
){
    val TAG = "ShadeTabRow"
    var tabLayoutWidth by remember { mutableStateOf(0f) } //tabLayout的宽度
    val paddingDpCurrent = paddingDp
    val paddingPx = with(LocalDensity.current){paddingDp.toPx()} //tabLayout的内边距
    var sliderWidth by remember { mutableStateOf(0f) }
    var sliderHeight by remember { mutableStateOf(0f) }
    val offsetSlider by animateFloatAsState(
        targetValue = sliderWidth * selectedTabIndex,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        finishedListener = {
        }
    )

    Card(
        modifier = Modifier
            .backgroundShadow(
                shadowColorLight,
                shadowColorDark,
                blurRadius,
                lightSource,
                offset,
                cornerRadius,
            ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = 0.dp
    ) {
        Box(
            Modifier
                .background(backgroundColor)
                .padding(paddingDpCurrent)
        ) {
            //滑块
            Card(
                modifier = Modifier
                    .offset { IntOffset(offsetSlider.toInt(), 0) }
                    .foregroundShadow(
                        shadowColorLightSlider,
                        shadowColorDarkSlider,
                        blurRadius,
                        lightSource,
                        offset,
                        cornerRadius,
                    ),
                shape = RoundedCornerShape(cornerRadius),
                elevation = 0.dp
            ) {
                Box(
                    Modifier
                        .background(sliderColor)
                        .size(
                            (sliderWidth / LocalDensity.current.density).dp,
                            (sliderHeight / LocalDensity.current.density).dp
                        )
                )

            }
            //Tabs
            SubcomposeLayout(Modifier.fillMaxWidth()) { constraints ->
                val tabRowWidth = constraints.maxWidth
                val tabMeasurables = subcompose(TabSlots.Tabs, tabs)
                val tabCount = tabMeasurables.size
                val tabWidth = (tabRowWidth / tabCount)
                sliderWidth = tabWidth.toFloat()
                val tabPlaceables = tabMeasurables.map {
                    it.measure(constraints.copy(minWidth = tabWidth, maxWidth = tabWidth))
                }

                val tabRowHeight = tabPlaceables.maxByOrNull { it.height }?.height ?: 0
                sliderHeight = tabRowHeight.toFloat()
                val tabPositions = List(tabCount) { index ->
                    TabPosition(tabWidth.toDp() * index, tabWidth.toDp())
                }
                tabPositions.forEach {
                    Log.e(TAG,"tabPosition left:${it.left} right:${it.right} width:${it.width}")
                }

                layout(tabRowWidth, tabRowHeight) {
                    tabPlaceables.forEachIndexed { index, placeable ->
                        placeable.placeRelative(index * tabWidth, 0)
                    }

                }
            }
        }

    }

}

@Immutable
class TabPosition internal constructor(val left: Dp, val width: Dp) {
    val right: Dp get() = left + width

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is androidx.compose.material.TabPosition) return false

        if (left != other.left) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }

    override fun toString(): String {
        return "TabPosition(left=$left, right=$right, width=$width)"
    }
}

private enum class TabSlots {
    Tabs,
    Divider,
    Indicator
}

@Composable
fun ShadeTab(
    height:Dp = 38.dp,
    onSelected:()->Unit,
    content: @Composable BoxScope.() -> Unit
){
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .height(height)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onSelected.invoke() }
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
         content()
    }
}