/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
class Day04Test {
    // Given
    private val inputRange = resourceAsString("day4.txt").split("-").let {
        it[0].toInt()..it[1].toInt()
    }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day04(inputRange).solvePart1()

            // Then
            assertThat(answer).isEqualTo(1_694)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day04(inputRange).solvePart2()

            // Then
            assertThat(answer).isEqualTo(1_148)
        }
    }
}