package com.dingmouren.shadecraft.core

import androidx.annotation.IntDef
import androidx.annotation.RestrictTo

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@IntDef(
    LightSource.LEFT_TOP,
    LightSource.LEFT_BOTTOM,
    LightSource.RIGHT_TOP,
    LightSource.RIGHT_BOTTOM
)
@Retention(AnnotationRetention.SOURCE)
annotation class LightSource {
    companion object {
        const val LEFT_TOP = 0
        const val LEFT_BOTTOM = 1
        const val RIGHT_TOP = 2
        const val RIGHT_BOTTOM = 3

        const val DEFAULT = LEFT_TOP

        fun opposite( lightSource:Int): Int {
            return when (lightSource) {
                LEFT_TOP -> RIGHT_BOTTOM
                RIGHT_TOP -> LEFT_BOTTOM
                LEFT_BOTTOM -> RIGHT_TOP
                RIGHT_BOTTOM -> LEFT_TOP
                else -> {-1}
            }
        }
    }

}
