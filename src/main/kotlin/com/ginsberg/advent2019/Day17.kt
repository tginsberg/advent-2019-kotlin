/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 17 - Set and Forget
 * Problem Description: http://adventofcode.com/2019/day/17
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day17/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@FlowPreview
class Day17(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun solvePart1(): Int {
        val scaffold = mapScaffold()
        scaffold.print()
        return scaffold
            .filter { it.value == '#' }
            .keys
            .filter { it.neighbors().all { neighbor -> scaffold[neighbor] == '#' } }
            .map { it.x * it.y }
            .sum()
    }

    fun solvePart2(): Int =
        runWithInput(
            listOf(
                "A,C,A,C,B,A,C,B,A,B",
                "R,6,L,10,R,8",
                "L,10,R,6,R,6,L,8",
                "R,8,R,12,L,8,L,8",
                "N" // Do not want live video
            )
        )

    private suspend fun takePicture(computer: IntCodeComputerMk2): Map<Point2D, Char> =
        computer.output.consumeAsFlow()
            .map { it.toChar() }
            .toList()
            .joinToString("")
            .lines()
            .mapIndexed { y, row ->
                row.mapIndexed { x, c -> Point2D(x, y) to c }
            }
            .flatten()
            .toMap()

    private fun mapScaffold(): Map<Point2D, Char> = runBlocking {
        val computer = IntCodeComputerMk2(program = program.copyOf(), output = Channel(Channel.UNLIMITED))
        launch {
            computer.runProgram()
        }
        takePicture(computer)
    }

    private fun runWithInput(input: List<String>): Int = runBlocking {
        val computer = IntCodeComputerMk2(
            program.copyOf().apply { this[0] = 2L } // L33t H4x0rz
        )
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

