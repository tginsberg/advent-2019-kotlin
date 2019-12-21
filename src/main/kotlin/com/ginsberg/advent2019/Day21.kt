/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 21 - Springdroid Adventure
 * Problem Description: http://adventofcode.com/2019/day/21
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day21/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Day21(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun solvePart1(): Int {
        val input = listOf(
            "NOT A J",
            "NOT B T",
            "AND D T",
            "OR T J",
            "NOT C T",
            "OR T J",
            "AND D J",
            "WALK"
        )
        return runWithInput(input)
    }

    fun solvePart2(): Int {
        val input = listOf(
            "NOT C J",
            "AND D J",
            "NOT H T",
            "NOT T T",
            "OR E T",
            "AND T J",
            "NOT A T",
            "OR T J",
            "NOT B T",
            "NOT T T",
            "OR E T",
            "NOT T T",
            "OR T J",
            "RUN"
        )
        return runWithInput(input)
    }

    private fun runWithInput(input: List<String>): Int = runBlocking {
        val computer = IntCodeComputerMk2(program)
        val cpu = launch {
            computer.runProgram()
        }
        input.forEach { line ->
            line.forEach { c ->
                computer.input.send(c.toLong())
            }
            computer.input.send(10)
        }
        cpu.join()

        computer.output.receive().toInt()
    }

}
