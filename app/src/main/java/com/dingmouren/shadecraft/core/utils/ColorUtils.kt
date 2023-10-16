package com.dingmouren.shadecraft.core.utils

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

object ColorUtils {

    fun getColor(context: Context, colorResId:Int):Int{
        return ContextCompat.getColor(context,colorResId)
    }
}