/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 18 - Many-Worlds Interpretation
 * Problem Description: http://adventofcode.com/2019/day/18
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day18/
 */
package com.ginsberg.advent2019

import java.util.ArrayDeque

class Day18(input: List<String>) {


    private val maze = Maze.from(input)

    fun solve(): Int =
        maze.minimumSteps()

    class Maze(private val starts: Set<Point2D>, private val keys: Map<Point2D, Char>, private val doors: Map<Point2D, Char>, private val openSpaces: Set<Point2D>) {

        private fun findReachableKeys(from: Point2D, haveKeys: Set<Char> = mutableSetOf()): Map<Char, Pair<Point2D, Int>> {
            val queue = ArrayDeque<Point2D>().apply { add(from) }
            val distance = mutableMapOf(from to 0)
            val keyDistance = mutableMapOf<Char, Pair<Point2D, Int>>()
            while (queue.isNotEmpty()) {
                val next = queue.pop()
                next.neighbors()
                    .filter { it in openSpaces }
                    .filterNot { it in distance }
                    .forEach { point ->
                        distance[point] = distance[next]!! + 1
                        val door = doors[point]
                        val key = keys[point]
                        if (door == null || door.toLowerCase() in haveKeys) {
                            if (key != null && key !in haveKeys) {
                                keyDistance[key] = Pair(point, distance[point]!!)
                            } else {
                                queue.add(point)
                            }
                        }
                    }
            }
            return keyDistance
        }

        private fun findReachableFromPoints(from: Set<Point2D>, haveKeys: Set<Char>): Map<Char, Triple<Point2D, Int, Point2D>> =
            from.map { point ->
                findReachableKeys(point, haveKeys).map { entry ->
                    entry.key to Triple(entry.value.first, entry.value.second, point)
                }
            }.flatten().toMap()

        fun minimumSteps(from: Set<Point2D> = starts,
                         haveKeys: Set<Char> = mutableSetOf(),
                         seen: MutableMap<Pair<Set<Point2D>, Set<Char>>, Int> = mutableMapOf()): Int {
            val state = Pair(from, haveKeys)

            if (state in seen) return seen.getValue(state)

            val answer = findReachableFromPoints(from, haveKeys).map { entry ->
                val (at, dist, cause) = entry.value
                dist + minimumSteps((from - cause) + at, haveKeys + entry.key, seen)
            }.min() ?: 0
            seen[state] = answer
            return answer
        }

        companion object {
            fun from(input: List<String>): Maze {
                val starts = mutableSetOf<Point2D>()
                val keys = mutableMapOf<Point2D, Char>()
                val doors = mutableMapOf<Point2D, Char>()
                val openSpaces = mutableSetOf<Point2D>()

                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        val place = Point2D(x, y)
                        if (c == '@') starts += place
                        if (c != '#') openSpaces += place
                        if (c in ('a'..'z')) keys[place] = c
                        if (c in ('A'..'Z')) doors[place] = c
                    }
                }
                return Maze(starts, keys, doors, openSpaces)
            }
        }
    }
}