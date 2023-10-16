package com.dingmouren.shadecraft.core

/**
 * 描述的是一个物体或曲线表面向外突起的特征
 */

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EnumConvexityClass

@EnumConvexityClass
enum class Convexity {
    CONCAVE,//凹
    CONVEX,//凸
}

/**
 * Toggle的开关状态
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EnumToggleClass

@EnumToggleClass
enum class Toggle {
    ON,//开
    OFF,//关
}


/**
 * 方向
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EnumDirectionClass

@EnumDirectionClass
enum class Direction {
    Horizontal, 
    Vertical,
}