/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 24")
class Day24Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Given
            val input = listOf(
                "....#",
                "#..#.",
                "#..##",
                "..#..",
                "#...."
            )
            // When
            val answer = Day24(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(2_129_920)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day24.txt")

            // When
            val answer = Day24(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(12_129_040)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // Given
            val input = listOf(
                "....#",
                "#..#.",
                "#..##",
                "..#..",
                "#...."
            )
            // When
            val answer = Day24(input).solvePart2(10)

            // Then
            assertThat(answer).isEqualTo(99)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day24.txt")

            // When
            val answer = Day24(input).solvePart2(200)

            // Then
            assertThat(answer).isEqualTo(2_109)
        }
    }
}
