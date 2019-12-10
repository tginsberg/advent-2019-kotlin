/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 10 - Monitoring Station
 * Problem Description: http://adventofcode.com/2019/day/10
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day10/
 */
package com.ginsberg.advent2019

class Day10(input: List<String>) {

    private val asteroids: List<Point2D> = parseInput(input)

    fun solvePart1(): Int =
        asteroids.map { it.countTargets() }.max()!!

    fun solvePart2(): Int =
        targetingOrder(asteroids.maxBy { it.countTargets() }!!).drop(199).first().run { (x * 100) + y }

    private fun Point2D.countTargets(): Int =
        asteroids.filterNot { it == this }.map { this.angleTo(it) }.distinct().size

    private fun parseInput(input: List<String>): List<Point2D> =
        input.withIndex().flatMap { (y, row) ->
            row.withIndex().filter { it.value == '#' }.map { Point2D(it.index, y) }
        }

    private fun targetingOrder(base: Point2D): List<Point2D> =
        asteroids
            .filterNot { it == base }
            .groupBy { base.angleTo(it) }
            .mapValues { it.value.sortedBy { target -> base.distanceTo(target) } }
            .toSortedMap()
            .values
            .flattenRoundRobin()


}
