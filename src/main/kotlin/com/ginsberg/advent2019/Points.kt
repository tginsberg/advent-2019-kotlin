/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import kotlin.math.abs

data class Point2D(val x: Int, val y: Int) {
    fun distanceTo(other: Point2D): Int =
        abs(x - other.x) + abs(y - other.y)

    val up: Point2D by lazy { copy(y = y + 1) }
    val down: Point2D by lazy { copy(y = y - 1) }
    val left: Point2D by lazy { copy(x = x - 1) }
    val right: Point2D by lazy { copy(x = x + 1) }

    companion object {
        val ORIGIN = Point2D(0, 0)
    }
}