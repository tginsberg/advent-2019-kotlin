/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 2 - 1202 Program Alarm
 * Problem Description: http://adventofcode.com/2019/day/2
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day2/
 */
package com.ginsberg.advent2019

class Day02IntCode(input: String) {

    private val memory: IntArray = input.split(",").map { it.toInt() }.toIntArray()

    fun solvePart1(noun: Int = memory[1], verb: Int = memory[2]): Int =
        runProgram(memory, noun, verb)

    fun solvePart2(target: Int = 19_690_720): Int {
        (0..99).forEach { noun ->
            (0..99).forEach { verb ->
                if (runProgram(memory, noun, verb) == target) {
                    return (noun * 100) + verb
                }
            }
        }
        throw IllegalStateException("Cannot find starting noun/verb that end up with $target")
    }

    private fun runProgram(memory: IntArray, noun: Int, verb: Int): Int {
        val memoryCopy = memory.copyOf().apply {
            this[1] = noun
            this[2] = verb
        }

        IntCodeComputer(memoryCopy).run()
        return memoryCopy.first()
    }


}