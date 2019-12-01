/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day01Test {

    // Given
    private val inputs = listOf("12", "14", "1969", "100756")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day01(inputs).solvePart1()

            // Then
            assertThat(answer).isEqualTo(34_241)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day01(Resources.resourceAsList("day1.txt")).solvePart1()

            // Then
            assertThat(answer).isEqualTo(3_223_398)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day01(inputs).solvePart2()

            // Then
            assertThat(answer).isEqualTo(51_316)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day01(Resources.resourceAsList("day1.txt")).solvePart2()

            // Then
            assertThat(answer).isEqualTo(4_832_253)
        }
    }
}