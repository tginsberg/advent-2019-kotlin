/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day06Test {

    // Given
    private val inputs = listOf(
        "COM)B",
        "B)C",
        "C)D",
        "D)E",
        "E)F",
        "B)G",
        "G)H",
        "D)I",
        "E)J",
        "J)K",
        "K)L"
    )

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day06(inputs).solvePart1()

            // Then
            assertThat(answer).isEqualTo(42)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day06(Resources.resourceAsList("day6.txt")).solvePart1()

            // Then
            assertThat(answer).isEqualTo(119_831)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Given
            val inputWithSanta = inputs + "K)YOU" + "I)SAN"

            // When
            val answer = Day06(inputWithSanta).solvePart2()

            // Then
            assertThat(answer).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day06(Resources.resourceAsList("day6.txt")).solvePart2()

            // Then
            assertThat(answer).isEqualTo(322)
        }
    }
}