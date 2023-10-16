package com.dingmouren.shadecraft.core

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * 默认的模糊半径
 */
object BlurProvider {

    const val MAX_RADIUS = 25f

    fun getDefaultBlurRadius(context:Context):Float{
        val densityStable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            DisplayMetrics.DENSITY_DEVICE_STABLE / DisplayMetrics.DENSITY_DEFAULT.toFloat()
        } else {
            context.resources.displayMetrics.density
        }
        return min(MAX_RADIUS, (densityStable * 10))
    }
}