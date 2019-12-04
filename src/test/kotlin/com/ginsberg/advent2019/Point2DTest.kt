/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Point2DTest {

    @Nested
    @DisplayName("Distance")
    inner class DistanceTests {
        @Test
        @DisplayName("Origin only")
        fun manhattanDistance() {
            assertThat(Point2D.ORIGIN.distanceTo(Point2D.ORIGIN)).isZero()
        }

        @Test
        @DisplayName("Simple distance")
        fun simpleDistance() {
            assertThat(Point2D.ORIGIN.distanceTo(Point2D.ORIGIN.up())).isOne()
            assertThat(Point2D.ORIGIN.distanceTo(Point2D.ORIGIN.down())).isOne()
            assertThat(Point2D.ORIGIN.distanceTo(Point2D.ORIGIN.left())).isOne()
            assertThat(Point2D.ORIGIN.distanceTo(Point2D.ORIGIN.right())).isOne()
        }

        @Test
        @DisplayName("Longer distance")
        fun longerDistance() {
            assertThat(Point2D.ORIGIN.distanceTo(Point2D(100, 100))).isEqualTo(200)
            assertThat(Point2D.ORIGIN.distanceTo(Point2D(100, -100))).isEqualTo(200)
            assertThat(Point2D.ORIGIN.distanceTo(Point2D(-100, 100))).isEqualTo(200)
            assertThat(Point2D.ORIGIN.distanceTo(Point2D(-100, -100))).isEqualTo(200)
        }
    }

    @Nested
    @DisplayName("Directions")
    inner class DirectionTests {
        @Test
        @DisplayName("Up")
        fun upMovesUp() {
            assertThat(Point2D.ORIGIN.up()).isEqualTo(Point2D(0, 1))
        }

        @Test
        @DisplayName("Down")
        fun downMovesDown() {
            assertThat(Point2D.ORIGIN.down()).isEqualTo(Point2D(0, -1))
        }

        @Test
        @DisplayName("Left")
        fun leftMovesLeft() {
            assertThat(Point2D.ORIGIN.left()).isEqualTo(Point2D(-1, 0))
        }

        @Test
        @DisplayName("Right")
        fun rightMovesRight() {
            assertThat(Point2D.ORIGIN.right()).isEqualTo(Point2D(1, 0))
        }
    }
}