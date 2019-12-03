/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 3 - Crossed Wires
 * Problem Description: http://adventofcode.com/2019/day/3
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day3/
 */
package com.ginsberg.advent2019

import java.lang.IllegalArgumentException

class Day03(input: List<String>) {

    private val wire1: List<Point2D> = parseWire(input[0])
    private val wire2: List<Point2D> = parseWire(input[1])
    private val intersections: Set<Point2D> = wire1.intersect(wire2)

    fun solvePart1(): Int =
        intersections.map { it.distanceTo(Point2D.ORIGIN) }.min()!!

    fun solvePart2(): Int =
        intersections.map { cross ->
            wire1.indexOf(cross) + wire2.indexOf(cross) + 2
        }.min()!!


    private fun parseWire(line: String): List<Point2D> {
        var current = Point2D.ORIGIN
        return line.split(",").flatMap {
            val direction = it.first()
            val steps = it.drop(1).toInt()
            (0 until steps).map {
                val next = when(direction) {
                    'U' -> current.up
                    'D' -> current.down
                    'L' -> current.left
                    'R' -> current.right
                    else -> throw IllegalArgumentException("Invalid direction: $direction")
                }
                current = next
                next
            }
        }
    }

}
