/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day11(resourceAsString("day11.txt")).solvePart1()

            // Then
            assertThat(answer).isEqualTo(1_964)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Print results`() {
            // Yeah, I'm not asserting anything. Try and stop me.
            Day11(resourceAsString("day11.txt")).solvePart2()
        }
    }
}