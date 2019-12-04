/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import kotlin.math.abs

data class Point2D(val x: Int, val y: Int) {

    fun up(): Point2D = copy(y = y + 1)

    fun down(): Point2D = copy(y = y - 1)

    fun left(): Point2D = copy(x = x - 1)

    fun right(): Point2D = copy(x = x + 1)

    fun distanceTo(other: Point2D): Int =
        abs(x - other.x) + abs(y - other.y)

    companion object {
        val ORIGIN = Point2D(0, 0)
    }
}