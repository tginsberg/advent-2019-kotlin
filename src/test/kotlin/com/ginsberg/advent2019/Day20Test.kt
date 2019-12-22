/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 20")
class Day20Test {

    // Given
    val example1 = listOf(
        "         A           ",
        "         A           ",
        "  #######.#########  ",
        "  #######.........#  ",
        "  #######.#######.#  ",
        "  #######.#######.#  ",
        "  #######.#######.#  ",
        "  #####  B    ###.#  ",
        "BC...##  C    ###.#  ",
        "  ##.##       ###.#  ",
        "  ##...DE  F  ###.#  ",
        "  #####    G  ###.#  ",
        "  #########.#####.#  ",
        "DE..#######...###.#  ",
        "  #.#########.###.#  ",
        "FG..#########.....#  ",
        "  ###########.#####  ",
        "             Z       ",
        "             Z       "
    )

    private val example2 = listOf(
        "             Z L X W       C                 ",
        "             Z P Q B       K                 ",
        "  ###########.#.#.#.#######.###############  ",
        "  #...#.......#.#.......#.#.......#.#.#...#  ",
        "  ###.#.#.#.#.#.#.#.###.#.#.#######.#.#.###  ",
        "  #.#...#.#.#...#.#.#...#...#...#.#.......#  ",
        "  #.###.#######.###.###.#.###.###.#.#######  ",
        "  #...#.......#.#...#...#.............#...#  ",
        "  #.#########.#######.#.#######.#######.###  ",
        "  #...#.#    F       R I       Z    #.#.#.#  ",
        "  #.###.#    D       E C       H    #.#.#.#  ",
        "  #.#...#                           #...#.#  ",
        "  #.###.#                           #.###.#  ",
        "  #.#....OA                       WB..#.#..ZH",
        "  #.###.#                           #.#.#.#  ",
        "CJ......#                           #.....#  ",
        "  #######                           #######  ",
        "  #.#....CK                         #......IC",
        "  #.###.#                           #.###.#  ",
        "  #.....#                           #...#.#  ",
        "  ###.###                           #.#.#.#  ",
        "XF....#.#                         RF..#.#.#  ",
        "  #####.#                           #######  ",
        "  #......CJ                       NM..#...#  ",
        "  ###.#.#                           #.###.#  ",
        "RE....#.#                           #......RF",
        "  ###.###        X   X       L      #.#.#.#  ",
        "  #.....#        F   Q       P      #.#.#.#  ",
        "  ###.###########.###.#######.#########.###  ",
        "  #.....#...#.....#.......#...#.....#.#...#  ",
        "  #####.#.###.#######.#######.###.###.#.#.#  ",
        "  #.......#.......#.#.#.#.#...#...#...#.#.#  ",
        "  #####.###.#####.#.#.#.#.###.###.#.###.###  ",
        "  #.......#.....#.#...#...............#...#  ",
        "  #############.#.#.###.###################  ",
        "               A O F   N                     ",
        "               A A D   M                     "

    )

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example 1`() {
            // When
            val answer = Day20(example1).solvePart1()

            // Then
            assertThat(answer).isEqualTo(23)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day20.txt")

            // When
            val answer = Day20(input).solvePart1()

            // Then
            assertThat(answer).isEqualTo(454)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example 1`() {
            // When
            val answer = Day20(example1).solvePart2()

            // Then
            assertThat(answer).isEqualTo(26)
        }

        @Test
        fun `Matches example 2`() {
            // When
            val answer = Day20(example2).solvePart2()

            // Then
            assertThat(answer).isEqualTo(396)
        }

        @Test
        fun `Actual answer`() {
            // Given
            val input = resourceAsList("day20.txt")

            // When
            val answer = Day20(input).solvePart2()

            // Then
            assertThat(answer).isEqualTo(5_744)
        }
    }
}