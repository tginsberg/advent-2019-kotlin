/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import kotlin.math.absoluteValue
import kotlin.math.sign

data class Vector3D(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Vector3D): Vector3D =
        Vector3D(x + other.x, y + other.y, z + other.z)

    infix fun diff(other: Vector3D): Vector3D =
        Vector3D((other.x - this.x).sign, (other.y - this.y).sign, (other.z - this.z).sign)

    val abs: Int =
        x.absoluteValue + y.absoluteValue + z.absoluteValue

    companion object {
        val ZERO = Vector3D(0, 0, 0)
    }
}