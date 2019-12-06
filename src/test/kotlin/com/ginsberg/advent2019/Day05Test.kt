/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsString("day5.txt")

            // When
            val answer = Day05(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(9_654_885)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsString("day5.txt")

            // When
            val answer = Day05(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(7_079_459)
        }
    }
}