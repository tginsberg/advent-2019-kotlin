/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

sealed class ScreenDirection {
    abstract fun turnAndMoveLeft(from: Point2D): Pair<ScreenDirection, Point2D>
    abstract fun turnAndMoveRight(from: Point2D): Pair<ScreenDirection, Point2D>

    object North : ScreenDirection() {
        override fun turnAndMoveLeft(from: Point2D) = Pair(West, from.left())
        override fun turnAndMoveRight(from: Point2D) = Pair(East, from.right())
    }
    object East : ScreenDirection() {
        override fun turnAndMoveLeft(from: Point2D) = Pair(North, from.down())
        override fun turnAndMoveRight(from: Point2D) = Pair(South, from.up())
    }
    object West : ScreenDirection() {
        override fun turnAndMoveLeft(from: Point2D) = Pair(South, from.up())
        override fun turnAndMoveRight(from: Point2D) = Pair(North, from.down())
    }
    object South : ScreenDirection() {
        override fun turnAndMoveLeft(from: Point2D) = Pair(East, from.right())
        override fun turnAndMoveRight(from: Point2D) = Pair(West, from.left())
    }
}