/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 9 -Sensor Boost
 * Problem Description: http://adventofcode.com/2019/day/9
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day9/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.runBlocking

class Day09(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun solvePart1(): Long =
        runComputer(1)

    fun solvePart2(): Long =
        runComputer(2)

    private fun runComputer(startState: Long): Long = runBlocking {
        IntCodeComputerMk2(program).run {
            input.send(startState)
            runProgram()
            output.receive()
        }

    }
}