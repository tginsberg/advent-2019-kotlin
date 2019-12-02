/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 1 - The Tyranny of the Rocket Equation
 * Problem Description: http://adventofcode.com/2019/day/1
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day1/
 */
package com.ginsberg.advent2019

class Day01(input: List<String>) {

    private val modules: List<Int> = input.map { it.toInt() }

    fun solvePart1(): Int =
        modules.sumBy { it.fuel() }

    fun solvePart2(): Int =
        modules.sumBy { it.fuelWithFuel() }

    private fun Int.fuel(): Int = this / 3 - 2

    private fun Int.fuelWithFuel(): Int =
        if(this < 7)  {
            0
        } else {
            val fuel = this.fuel()
            fuel + fuel.fuelWithFuel()
        }
}