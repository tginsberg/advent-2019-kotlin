/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 7")
class Day07Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // Given
            val input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"

            // When
            val answer = Day07(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(43_210)
        }

        @Test
        fun `Matches example 2`() {
            // Given
            val input = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"

            // When
            val answer = Day07(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(54_321)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsString("day7.txt")

            // When
            val answer = Day07(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(359_142)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            val input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"

            // When
            val answer = Day07(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(139_629_729)
        }

        @Test
        fun `Matches example 2`() {
            val input = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"

            // When
            val answer = Day07(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(18_216)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsString("day7.txt")

            // When
            val answer = Day07(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(4_374_895)
        }
    }
}