/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    private val sampleInput: List<String> =
        """
            |.#..##.###...#######
            |##.############..##.
            |.#.######.########.#
            |.###.#######.####.#.
            |#####.##.#.##.###.##
            |..#####..#.#########
            |####################
            |#.####....###.#.#.##
            |##.#################
            |#####.##.###..####..
            |..######..##.#######
            |####.##.####...##..#
            |.#####..#.######.###
            |##...#.##########...
            |#.##########.#######
            |.####.#.###.###.#.##
            |....##.##.###..#####
            |.#.#.###########.###
            |#.#.#.#####.####.###
            |###.##.####.##.#..##
        """.trimMargin().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val answer = Day10(sampleInput).solvePart1()

            // Then
            assertThat(answer).isEqualTo(210)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day10.txt")

            // When
            val answer = Day10(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(276)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {

            // When
            val answer = Day10(sampleInput).solvePart2()

            // Then
            assertThat(answer).isEqualTo(802)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day10.txt")

            // When
            val answer = Day10(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(1_321)
        }
    }
}