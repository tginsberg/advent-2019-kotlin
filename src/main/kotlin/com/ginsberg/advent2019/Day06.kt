/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 6 - Universal Orbit Map
 * Problem Description: http://adventofcode.com/2019/day/6
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day6/
 */
package com.ginsberg.advent2019

class Day06(input: List<String>) {

    // Note that this is Orbiting -> Orbited, not Orbited -> Orbiting
    private val orbitPairs: Map<String, String> = input.map { it.split(")") }.map { it.last() to it.first() }.toMap()

    fun solvePart1(): Int =
        orbitPairs.keys.sumBy { pathTo(it).size -1 }

    fun solvePart2(): Int {
        val youToRoot = pathTo("YOU")
        val santaToRoot = pathTo("SAN")
        val intersection = youToRoot.intersect(santaToRoot).first()

        return youToRoot.indexOf(intersection) + santaToRoot.indexOf(intersection) - 2
    }

    private fun pathTo(orbit: String, path: MutableList<String> = mutableListOf(orbit)): List<String> =
        orbitPairs[orbit]?.let { around ->
            path.add(around)
            pathTo(around, path)
        } ?: path
}
