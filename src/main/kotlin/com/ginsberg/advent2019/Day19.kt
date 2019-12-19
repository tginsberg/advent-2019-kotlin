/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 19 - Tractor Beam
 * Problem Description: http://adventofcode.com/2019/day/19
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day19/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.runBlocking

class Day19(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun solvePart1(): Int =
        (0..49L).sumBy { x ->
            (0..49L).count { y ->
                engageTractorBeam(x, y)
            }
        }

    fun solvePart2(): Long {
        var x = 0L
        var y = 0L
        while (true) {
            while (!engageTractorBeam(x, y + 99)) {
                x++
            }
            if (engageTractorBeam(x + 99, y)) {
                return (x * 10_000) + (y)
            }
            y++
        }
    }

    private fun engageTractorBeam(x: Long, y: Long): Boolean = runBlocking {
        IntCodeComputerMk2(program.copyOf()).run {
            input.send(x)
            input.send(y)
            runProgram()
            output.receive() == 1L
        }
    }
}
