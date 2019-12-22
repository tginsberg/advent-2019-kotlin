/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 16 - Flawed Frequency Transmission
 * Problem Description: http://adventofcode.com/2019/day/16
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day16/
 */
package com.ginsberg.advent2019

import kotlin.math.absoluteValue

class Day16(private val input: String) {

    private val signal: IntArray = input.map { Character.getNumericValue(it) }.toIntArray()

    fun solvePart1(phases: Int = 100): String =
        (1..phases).fold(signal) { carry, _ -> phase(carry) }.joinToString(separator = "").take(8)

    fun solvePart2(phases: Int = 100): String {
        val offset = input.take(7).toInt()
        val stretchedInput = (offset until 10_000 * input.length).map { signal[it % input.length] }.toIntArray()
        repeat(phases) {
            stretchedInput.indices.reversed().fold(0) { carry, idx ->
                (stretchedInput[idx] + carry).lastDigit().also { stretchedInput[idx] = it }
            }
        }
        return stretchedInput.take(8).joinToString(separator = "")
    }

    private fun phase(input: IntArray): IntArray =
        (1..input.size).map { element ->
            val cycle: IntArray = base.cycle(element, input.size)
            input.mapIndexed { index, inputElement -> (inputElement * cycle[index]) }.sum().lastDigit()
        }.toIntArray()

    private fun Int.lastDigit(): Int =
        (this % 10).absoluteValue

    private fun IntArray.cycle(perCycle: Int, length: Int): IntArray =
        (0..length / perCycle).flatMap { idx ->
            List(perCycle) { this[idx % this.size] }
        }.drop(1).toIntArray()

    companion object {
        val base: IntArray = intArrayOf(0, 1, 0, -1)
    }
}

