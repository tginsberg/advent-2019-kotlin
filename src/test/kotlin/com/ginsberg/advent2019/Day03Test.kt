/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example 1`() {
            // Given
            val input = listOf(
                "R8,U5,L5,D3",
                "U7,R6,D4,L4"
            )

            // When
            val answer = Day03(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(6)
        }

        @Test
        fun `Matches example 2`() {
            // Given
            val input = listOf(
                "R75,D30,R83,U83,L12,D49,R71,U7,L72",
                "U62,R66,U55,R34,D71,R55,D58,R83"
            )

            // When
            val answer = Day03(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(159)
        }

        @Test
        fun `Matches example 3`() {
            // Given
            val input = listOf(
                "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
            )

            // When
            val answer = Day03(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(135)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = Resources.resourceAsList("day3.txt")

            // When
            val answer = Day03(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(1225)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            // Given
            val input = listOf(
                "R8,U5,L5,D3",
                "U7,R6,D4,L4"
            )

            // When
            val answer = Day03(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(30)
        }

        @Test
        fun `Matches example 2`() {
            // Given
            val input = listOf(
                "R75,D30,R83,U83,L12,D49,R71,U7,L72",
                "U62,R66,U55,R34,D71,R55,D58,R83"
            )

            // When
            val answer = Day03(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(610)
        }

        @Test
        fun `Matches example 3`() {
            // Given
            val input = listOf(
                "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51",
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
            )

            // When
            val answer = Day03(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(410)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = Resources.resourceAsList("day3.txt")

            // When
            val answer = Day03(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(107_036)
        }
    }
}