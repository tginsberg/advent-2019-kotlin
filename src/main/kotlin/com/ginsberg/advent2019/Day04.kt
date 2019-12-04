/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 4 - Secure Container
 * Problem Description: http://adventofcode.com/2019/day/4
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day4/
 */
package com.ginsberg.advent2019

class Day04(private val range: IntRange) {

    fun solvePart1(): Int =
        range
            .map { it.toString() }
            .count { isSorted(it) && containsMatchingPair(it) }

    fun solvePart2(): Int =
        range
            .map { it.toString() }
            .count { isSorted(it) && containsIsolatedPair(it) }

    private fun isSorted(input: String): Boolean =
        input.zipWithNext().all { it.first <= it.second }

    private fun containsMatchingPair(input: String): Boolean =
        input.zipWithNext().any { it.first == it.second }

    private fun containsIsolatedPair(input: String): Boolean =
        input.groupBy { it }.any { it.value.size == 2 }

}
