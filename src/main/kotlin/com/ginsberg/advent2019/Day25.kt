/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 25 - Cryostasis
 * Problem Description: http://adventofcode.com/2019/day/25
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day25/
 */
package com.ginsberg.advent2019

import com.ginsberg.advent2019.Resources.resourceAsString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


@FlowPreview
@ExperimentalCoroutinesApi
class Day25(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun playGame() = runBlocking {
        val computer = IntCodeComputerMk2(
            program = program.copyOf(),
            output = Channel(Channel.UNLIMITED)
        ).also {
            launch { it.runProgram() }
        }
        launch {
            while (!computer.output.isClosedForReceive) {
                computer.output.receive()
                while (!computer.output.isEmpty) {
                    if (computer.output.isClosedForReceive) {
                        return@launch
                    }
                    print(computer.output.receive().toChar())
                }
                if (!computer.output.isClosedForReceive) {
                    withContext(Dispatchers.Default) {
                        "${readLine()!!}\n"
                    }.forEach { c -> computer.input.send(c.toLong()) }
                }
            }
        }
    }

    fun solvePart1(): String =
        solve(
            listOf(
                "south",
                "take mutex",
                "south",
                "west",
                "west",
                "take klein bottle",
                "east",
                "east",
                "north",
                "east",
                "take mug",
                "east",
                "north",
                "north",
                "take hypercube",
                "south",
                "south",
                "east",
                "east",
                "east",
                "south",
                "west",
                "west"
            )
        )

    private fun solve(commands: List<String>, print: Boolean = false): String = runBlocking {
        val computer = IntCodeComputerMk2(program = program.copyOf(), output = Channel(Channel.UNLIMITED))
        launch { computer.runProgram() }
        commands.forEach { line ->
            computer.output.receive()
            while (!computer.output.isEmpty) {
                computer.output.receive()
            }
            line.forEach { c ->
                computer.input.send(c.toLong())
            }
            computer.input.send(10) // newline
        }
        computer.output.consumeAsFlow()
            .toList()
            .map { it.toChar() }
            .filter { it in ('0'..'9') }
            .joinToString("")
    }

}

@ExperimentalCoroutinesApi
@FlowPreview
fun main() {
    Day25(resourceAsString("day25.txt")).playGame()
}
