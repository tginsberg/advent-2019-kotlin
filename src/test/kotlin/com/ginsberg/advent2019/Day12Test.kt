/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
class Day12Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // Given
            val input: List<String> = """
            |<x=-1, y=0, z=2>
            |<x=2, y=-10, z=-7>
            |<x=4, y=-8, z=8>
            |<x=3, y=5, z=-1>
            """.trimMargin().lines()

            // When
            val answer = Day12(input).solvePart1(10)

            // Then
            assertThat(answer).isEqualTo(179)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day12.txt")

            // When
            val answer = Day12(input).solvePart1(1_000)

            // Then
            assertThat(answer).isEqualTo(9_139)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // Given
            val input: List<String> = """
            |<x=-1, y=0, z=2>
            |<x=2, y=-10, z=-7>
            |<x=4, y=-8, z=8>
            |<x=3, y=5, z=-1>
            """.trimMargin().lines()

            // When
            val answer = Day12(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(2_772)
        }

        @Test
        fun `Matches example 2`() {
            // Given
            val input = """
                <x=-8, y=-10, z=0>
                <x=5, y=5, z=10>
                <x=2, y=-7, z=3>
                <x=9, y=-8, z=-3>
            """.trimIndent().lines()

            // When
            val answer = Day12(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(4_686_774_924)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day12.txt")

            // When
            val answer = Day12(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(420_788_524_631_496)
        }
    }
}