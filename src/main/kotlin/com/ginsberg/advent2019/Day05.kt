/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 5 - Sunny with a Chance of Asteroids
 * Problem Description: http://adventofcode.com/2019/day/5
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day5/
 */
package com.ginsberg.advent2019

class Day05(input: String) {

    private val program: IntArray = input.split(",").map { it.toInt() }.toIntArray()

    fun solvePart1(): Int =
        solve(1)

    fun solvePart2(): Int =
        solve(5)

    private fun solve(instruction: Int): Int =
        IntCodeComputer(program, instruction).run().last()
}
