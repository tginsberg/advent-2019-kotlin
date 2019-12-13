/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 13 - Care Package
 * Problem Description: http://adventofcode.com/2019/day/13
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day13/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.sign

@ExperimentalCoroutinesApi
class Day13(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun solvePart1(): Int = runBlocking {
        val board: MutableMap<Point2D, Int> = mutableMapOf()
        val computer = IntCodeComputerMk2(program = program, output = Channel(Channel.UNLIMITED))
        computer.runProgram()

        while (!computer.output.isClosedForReceive) {
            board[Point2D(
                computer.output.receive().toInt(),
                computer.output.receive().toInt()
            )] = computer.output.receive().toInt()
        }
        board.count { it.value == block }
    }

    fun solvePart2(): Int = runBlocking {
        program[0] = freePlay // We r l33t H4x0rz now.
        val computer = IntCodeComputerMk2(program = program, output = Channel(Channel.UNLIMITED))

        launch {
            computer.runProgram()
        }
        async {
            var paddleX = 0
            var score = 0
            while (!computer.output.isClosedForReceive) {
                val x = computer.output.receive().toInt()
                computer.output.receive()
                val item = computer.output.receive().toInt()
                when {
                    x == -1 -> score = item
                    item == paddle -> paddleX = x
                    item == ball -> {
                        computer.input.send((x - paddleX).sign.toLong())
                    }
                }
            }
            score
        }.await()
    }

    companion object {
        private const val block = 2
        private const val paddle = 3
        private const val ball = 4
        private const val freePlay = 2L
    }
}
