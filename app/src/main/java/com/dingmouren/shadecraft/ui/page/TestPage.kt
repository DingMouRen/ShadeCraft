package com.dingmouren.shadecraft.ui.page

import android.graphics.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import com.dingmouren.shadecraft.App
import com.dingmouren.shadecraft.R
import com.dingmouren.shadecraft.utils.PathUtils

@Composable
fun TestPage(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Canvas(modifier = Modifier.align(Alignment.CenterStart) ){
            val paths = PathUtils.getPathFromDrawable(R.drawable.bulb)
            val mPaint = Paint()
            mPaint.isAntiAlias =true
            mPaint.isDither = true
            mPaint.setStyle(Paint.Style.FILL)
            mPaint.setColor(Color.RED)
            this.drawIntoCanvas {

                for (element in paths){
                    it.nativeCanvas.drawPath(PathUtils.scalePath(element,0.5f,0.5f),mPaint)
                }
            }
        }
    }

}

