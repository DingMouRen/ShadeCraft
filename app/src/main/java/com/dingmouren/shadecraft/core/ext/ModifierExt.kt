package com.dingmouren.shadecraft.core.ext

import android.graphics.BlurMaskFilter
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.core.BlurProvider
import com.dingmouren.shadecraft.core.ConstantColor
import com.dingmouren.shadecraft.core.LightSource
import com.dingmouren.shadecraft.core.utils.ColorUtils

/**
 * 背景阴影扩展函数
 */
fun Modifier.backgroundShadow(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius:Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset:Float = 10f,
    cornerRadius:Dp = 0.dp,
    shape:Int = com.dingmouren.shadecraft.core.Shape.Rectangle,
    borderWidth :Dp = 20.dp,//Shape.Circle中作为圆环宽度
) = then(object :DrawModifier{
    override fun ContentDrawScope.draw() {

        val TAG = "Modifier.backgroundShadow"
        Log.i(TAG,"backgroundShadow shadowColorLight:$shadowColorLight shadowColorDark:$shadowColorDark blurRadius:$blurRadius lightSource:$lightSource offset:$offset cornerRadius:$cornerRadius size:${size.width}-${size.height}")
        //浅色阴影画笔
        val paintShadowLight = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                    if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //浅色阴影在光源方向的偏移量
        val backgroundShadowLightOffset:Offset = when(lightSource){
            LightSource.LEFT_TOP -> Offset(-offset,-offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {Offset(0f,0f)}
        }
        //深色阴影画笔
        val paintShadowDark = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                    if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //深色阴影在光源方向的偏移量
        val backgroundShadowDarkOffset:Offset = when(LightSource.opposite(lightSource)){
            LightSource.LEFT_TOP -> Offset(-offset,-offset)
            LightSource.LEFT_BOTTOM -> Offset(-offset,offset)
            LightSource.RIGHT_TOP -> Offset(offset, -offset)
            LightSource.RIGHT_BOTTOM -> Offset(offset, offset)
            else -> {Offset(0f,0f)}
        }


        drawIntoCanvas {

            //画布平移绘制浅色阴影
            it.save()
            it.translate(backgroundShadowLightOffset.x,backgroundShadowLightOffset.y)
            when(shape){
                com.dingmouren.shadecraft.core.Shape.Circle ->{
                    paintShadowLight.style = PaintingStyle.Stroke
                    paintShadowLight.strokeWidth = borderWidth.toPx()
                    it.drawCircle(
                        Offset(this.size.width/2,this.size.height/2),
                        (this.size.width - borderWidth.toPx()  )/2,
                        paintShadowLight
                    )
                }
                com.dingmouren.shadecraft.core.Shape.Rectangle ->{
                    it.drawRoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        cornerRadius.toPx(),
                        cornerRadius.toPx(),
                        paintShadowLight
                    )
                }
            }
            it.restore()
            //画布平移绘制深色阴影
            it.save()
            it.translate(backgroundShadowDarkOffset.x,backgroundShadowDarkOffset.y)
            when(shape){
                com.dingmouren.shadecraft.core.Shape.Circle ->{
                    paintShadowDark.style = PaintingStyle.Stroke
                    paintShadowDark.strokeWidth = borderWidth.toPx()
                    it.drawCircle(
                        Offset(this.size.width/2,this.size.height/2),
                        (this.size.width - borderWidth.toPx() )/2,
                        paintShadowDark
                    )
                }
                com.dingmouren.shadecraft.core.Shape.Rectangle ->{
                    it.drawRoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        cornerRadius.toPx(),
                        cornerRadius.toPx(),
                        paintShadowDark
                    )
                }
            }
            it.restore()
        }

        drawContent()
    }
})
/**
 * 前景阴影扩展函数
 */
fun Modifier.foregroundShadow(
    shadowColorLight: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_LIGHT),
    shadowColorDark: Color = Color(ConstantColor.THEME_LIGHT_COLOR_SHADOW_DARK),
    blurRadius:Float = 8f,
    lightSource: Int = LightSource.DEFAULT,
    offset:Float = 22f,
    cornerRadius:Dp = 0.dp,
)  = then(object :DrawModifier{
    override fun ContentDrawScope.draw() {
        drawContent()


        val TAG = "Modifier.backgroundShadow"
        Log.i(TAG,"backgroundShadow shadowColorLight:$shadowColorLight shadowColorDark:$shadowColorDark blurRadius:$blurRadius lightSource:$lightSource offset:$offset cornerRadius:$cornerRadius size:${size.width}-${size.height}")
        //浅色阴影画笔
        val paintShadowLight = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorLight.toArgb() //设置画笔颜色
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.strokeWidth = offset
                    if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //浅色阴影在光源方向的偏移量
        val backgroundShadowLightOffset:Offset = when(LightSource.opposite(lightSource)){
            LightSource.LEFT_TOP -> Offset(offset,offset)
            LightSource.LEFT_BOTTOM -> Offset(offset,-offset)
            LightSource.RIGHT_TOP -> Offset(-offset, offset)
            LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
            else -> {Offset(0f,0f)}
        }
        //深色阴影画笔
        val paintShadowDark = Paint().also { paint: androidx.compose.ui.graphics.Paint ->
            paint.asFrameworkPaint() //将自定义的绘制操作转换成底层渲染引擎能够理解的渲染描述对象，从而实现更加高效和灵活的绘制操作。
                .also {nativePaint: NativePaint ->
                    nativePaint.isAntiAlias = true //设置抗锯齿
                    nativePaint.isDither = true //开启防抖
                    nativePaint.color = shadowColorDark.toArgb() //设置画笔颜色
                    nativePaint.style = android.graphics.Paint.Style.STROKE
                    nativePaint.strokeWidth = offset //设置描边宽度
                    if (offset>0)nativePaint.maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL) //设置模糊滤镜效果
                }
        }
        //深色阴影在光源方向的偏移量
        val backgroundShadowDarkOffset:Offset = when(lightSource){
            LightSource.LEFT_TOP -> Offset(offset,offset)
            LightSource.LEFT_BOTTOM -> Offset(offset,-offset)
            LightSource.RIGHT_TOP -> Offset(-offset, offset)
            LightSource.RIGHT_BOTTOM -> Offset(-offset, -offset)
            else -> {Offset(0f,0f)}
        }


        drawIntoCanvas {
            Log.e("draw","dark offset:$offset x:${backgroundShadowDarkOffset.x} y:${backgroundShadowDarkOffset.y} width:${this.size.width} height:${this.size.height} ")
            Log.e("draw","light offset:$offset x:${backgroundShadowLightOffset.x} y:${backgroundShadowLightOffset.y} width:${this.size.width} height:${this.size.height} ")
            //绘制深色阴影
            it.save()
            val pathShadowDark = Path().also { path ->
                path.moveTo(0f, 0f)
                path.addRoundRect(RoundRect(0f, 0f, this.size.width , this.size.height, cornerRadius.toPx(), cornerRadius.toPx()))
            }
            it.clipPath(pathShadowDark)
            it.translate(backgroundShadowDarkOffset.x,backgroundShadowDarkOffset.y)
            it.drawRoundRect(
                -offset,
                -offset,
                this.size.width + offset,
                this.size.height + offset,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paintShadowDark
            )
            it.restore()
            //绘制浅色阴影
            it.save()
            val pathShadowLight = Path().also { path ->
                path.moveTo(0f, 0f)
                path.addRoundRect(RoundRect(0f, 0f, this.size.width, this.size.height, cornerRadius.toPx(), cornerRadius.toPx()))
            }
            it.clipPath(pathShadowLight)
            it.translate(backgroundShadowLightOffset.x,backgroundShadowLightOffset.y)
            it.drawRoundRect(
                -offset,
                -offset,
                this.size.width + offset,
                this.size.height + offset,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paintShadowLight
            )
            it.restore()



        }

    }
})

