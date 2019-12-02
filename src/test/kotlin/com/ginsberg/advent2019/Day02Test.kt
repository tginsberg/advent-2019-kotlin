/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // Given
            val input = "1,1,1,4,99,5,6,0,99"

            // When
            val answer = Day02(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(30)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = Resources.resourceAsString("day2.txt")

            // When
            val answer = Day02(input).solvePart1(12, 2)

            // Then
            assertThat(answer).isEqualTo(5_110_675)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // Given
            val input = Resources.resourceAsString("day2.txt")

            // When
            val answer = Day02(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(4_847)
        }
    }
}