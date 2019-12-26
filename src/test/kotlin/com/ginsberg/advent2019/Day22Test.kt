/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day22.txt")

            // When
            val answer = Day22(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(1_234)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day22.txt")

            // When
            val answer = Day22(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(7_757_787_935_983)
        }
    }
}
