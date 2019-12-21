/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 15 - Oxygen System
 * Problem Description: http://adventofcode.com/2019/day/15
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day15/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Day15(input: String) {

    private val program: MutableMap<Long, Long> = input
        .split(",")
        .withIndex()
        .associateTo(mutableMapOf()) { it.index.toLong() to it.value.toLong() }

    private val computer = IntCodeComputerMk2(program)
    private val maze: Map<Point2D, Char> = maze()
    private val oxygenLocation: Point2D = maze.entries.first { it.value == oxygen }.key

    fun solvePart1(): Int =
        findPaths(Point2D.ORIGIN, oxygenLocation) { maze.getOrDefault(it, wall) != wall }.first().size - 1

    fun solvePart2(): Int =
        findPaths(oxygenLocation, null) { maze.getOrDefault(it, wall) != wall }.last().size - 1

    private fun maze() = runBlocking {
        val job = async {
            computer.runProgram()
        }
        calculateMaze().also {
            job.cancel()
        }
    }

    private suspend fun calculateMaze(
        at: Point2D = Point2D.ORIGIN,
        mazeSoFar: MutableMap<Point2D, Char> = mutableMapOf(Point2D.ORIGIN to open)
    ): MutableMap<Point2D, Char> {
        at.neighbors().filter { it !in mazeSoFar }.forEach { neighbor ->
            computer.input.send(at.directionTo(neighbor))
            when (val result = computer.output.receive()) {
                foundOxygen, robotMoved -> {
                    mazeSoFar[neighbor] = if (result == robotMoved) open else oxygen
                    calculateMaze(neighbor, mazeSoFar)
                    computer.input.send(neighbor.directionTo(at))
                    computer.output.receive() // Ignore, we know this works.
                }
                pathBlocked -> mazeSoFar[neighbor] = wall
            }
        }
        return mazeSoFar
    }

    private fun Point2D.directionTo(other: Point2D): Long =
        when (other) {
            north() -> 1
            south() -> 2
            west() -> 3
            east() -> 4
            else -> throw IllegalStateException("Unknown relation between $this and $other")
        }

    companion object {
        private const val pathBlocked: Long = 0
        private const val robotMoved: Long = 1
        private const val foundOxygen: Long = 2

        private const val wall: Char = '#'
        private const val open: Char = '.'
        private const val oxygen: Char = 'O'
    }
}
