/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
@ExperimentalCoroutinesApi
class Day16Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Given
            val input = "12345678"

            // When
            val answer = Day16(input).solvePart1(4)

            // Then
            assertThat(answer).isEqualTo("01029498")
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsString("day16.txt")

            // When
            val answer = Day16(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo("17978331")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {


        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsString("day16.txt")

            // When
            val answer = Day16(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo("19422575")
        }
    }
}