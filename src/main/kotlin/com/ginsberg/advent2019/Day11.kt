/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 11 - Space Police
 * Problem Description: http://adventofcode.com/2019/day/11
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day11/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class Day11(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    fun solvePart1(): Int =
        paintShip().size

    fun solvePart2() {
        val ship = paintShip(white)
        val min = ship.keys.minWith(Point2D.readerOrder)!!
        val max = ship.keys.maxWith(Point2D.readerOrder)!!
        (min.y..max.y).forEach { y ->
            println(
                (min.x..max.x).map { x ->
                    if (ship[Point2D(x, y)] == white) '#' else ' '
                }.joinToString(separator = "")
            )
        }
    }

    private fun paintShip(startingWith: Long = black) = runBlocking {
        val ship = mutableMapOf(Point2D.ORIGIN to 0L)
        val computer = IntCodeComputerMk2(program)
        coroutineScope {
            launch {
                computer.runProgram()
            }
            launch {
                var location: Point2D = Point2D.ORIGIN
                var screenDirection: ScreenDirection = ScreenDirection.North
                computer.input.send(startingWith)
                while (!computer.output.isClosedForReceive) {
                    val colorMsg = computer.output.receive()
                    ship[location] = colorMsg
                    when (val dir = computer.output.receive()) {
                        left -> screenDirection.turnAndMoveLeft(location)
                        right -> screenDirection.turnAndMoveRight(location)
                        else -> throw IllegalStateException("Invalid direction: $dir")
                    }.apply {
                        screenDirection = first
                        location = second
                    }
                    computer.input.send(ship.getOrDefault(location, black))
                }
            }
        }
        ship
    }

    companion object {
        private const val black: Long = 0L
        private const val white: Long = 1L
        private const val left: Long = 0L
        private const val right: Long = 1L
    }

}
