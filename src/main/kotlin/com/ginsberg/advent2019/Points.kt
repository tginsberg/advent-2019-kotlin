/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import java.lang.Math.toDegrees
import kotlin.math.abs
import kotlin.math.atan2

data class Point2D(val x: Int, val y: Int) {

    fun up(): Point2D = copy(y = y + 1)
    fun down(): Point2D = copy(y = y - 1)
    fun left(): Point2D = copy(x = x - 1)
    fun right(): Point2D = copy(x = x + 1)

    fun distanceTo(other: Point2D): Int =
        abs(x - other.x) + abs(y - other.y)

    // Note: Tested only with x,y in screen mode (upper left)
    fun angleTo(other: Point2D): Double {
        val d = toDegrees(
            atan2(
                (other.y - y).toDouble(),
                (other.x - x).toDouble()
            )
        ) + 90
        return if (d < 0) d + 360 else d
    }

    companion object {
        val ORIGIN = Point2D(0, 0)
        val readerOrder: Comparator<Point2D> = Comparator { o1, o2 ->
            when {
                o1.y != o2.y -> o1.y - o2.y
                else -> o1.x - o2.x
            }
        }
    }
}
