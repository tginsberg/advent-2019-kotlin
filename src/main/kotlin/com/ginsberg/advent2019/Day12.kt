/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 12 - The N-Body Problem
 * Problem Description: http://adventofcode.com/2019/day/12
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day12/
 */
package com.ginsberg.advent2019

import org.apache.commons.math3.util.ArithmeticUtils.lcm

class Day12(input: List<String>) {

    private val moons: List<Moon> = parseInput(input)
    private val moonPairs: List<Pair<Moon, Moon>> = moons.everyPair()

    fun solvePart1(steps: Int): Int {
        repeat(steps) {
            step()
        }
        return moons.sumBy { it.energy() }
    }

    fun solvePart2(): Long {
        val startingX: List<Pair<Int, Int>> = moons.map { it.position.x to it.velocity.x }
        val startingY: List<Pair<Int, Int>> = moons.map { it.position.y to it.velocity.y }
        val startingZ: List<Pair<Int, Int>> = moons.map { it.position.z to it.velocity.z }
        var foundX: Long? = null
        var foundY: Long? = null
        var foundZ: Long? = null
        var stepCount = 0L
        do {
            stepCount += 1
            step()
            foundX = if (foundX == null && startingX == moons.map { it.position.x to it.velocity.x }) stepCount else foundX
            foundY = if (foundY == null && startingY == moons.map { it.position.y to it.velocity.y }) stepCount else foundY
            foundZ = if (foundZ == null && startingZ == moons.map { it.position.z to it.velocity.z }) stepCount else foundZ

        } while (foundX == null || foundY == null || foundZ == null)
        return lcm(foundX, lcm(foundY, foundZ))
    }

    private fun step() {
        moonPairs.forEach {
            it.first.applyGravity(it.second)
            it.second.applyGravity(it.first)
        }
        moons.forEach { it.applyVelocity() }
    }

    private fun parseInput(input: List<String>): List<Moon> =
        input.map { line ->
            line.replace("""[^-\d,]""".toRegex(), "")
                .split(",")
                .map { it.toInt() }
        }.map { Moon(Vector3D(it[0], it[1], it[2])) }


    data class Moon(var position: Vector3D, var velocity: Vector3D = Vector3D.ZERO) {

        fun applyGravity(other: Moon) {
            this.velocity += (this.position diff other.position)
        }

        fun applyVelocity() {
            position += velocity
        }

        fun energy(): Int =
            position.abs * velocity.abs
    }


}
