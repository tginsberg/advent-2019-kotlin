/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsList
import kotlinx.coroutines.FlowPreview
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 18")
@FlowPreview
class Day18Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // Given
            val input = listOf(
                "#########",
                "#b.A.@.a#",
                "#########"
            )

            // When
            val answer = Day18(input).solve()

            // Then
            assertThat(answer).isEqualTo(8)
        }

        @Test
        fun `Matches example 2`() {
            // Given
            val input = listOf(
                "########################",
                "#f.D.E.e.C.b.A.@.a.B.c.#",
                "######################.#",
                "#d.....................#",
                "########################"
            )

            // When
            val answer = Day18(input).solve()

            // Then
            assertThat(answer).isEqualTo(86)
        }

        @Test
        fun `Matches example 3`() {
            // Given
            val input = listOf(
                "#################",
                "#i.G..c...e..H.p#",
                "########.########",
                "#j.A..b...f..D.o#",
                "########@########",
                "#k.E..a...g..B.n#",
                "########.########",
                "#l.F..d...h..C.m#",
                "#################"
            )

            // When
            val answer = Day18(input).solve()

            // Then
            assertThat(answer).isEqualTo(136)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day18-part1.txt")

            // When
            val answer = Day18(input).solve()

            // Then
            assertThat(answer).isEqualTo(4_042)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example 1`() {
            // Given
            val input = listOf(
                "#############",
                "#g#f.D#..h#l#",
                "#F###e#E###.#",
                "#dCba@#@BcIJ#",
                "#############",
                "#nK.L@#@G...#",
                "#M###N#H###.#",
                "#o#m..#i#jk.#",
                "#############"
            )

            // When
            val answer = Day18(input).solve()

            // Then
            assertThat(answer).isEqualTo(72)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day18-part2.txt")

            // When
            val answer = Day18(input).solve()

            // Then
            assertThat(answer).isEqualTo(2_014)
        }
    }
}