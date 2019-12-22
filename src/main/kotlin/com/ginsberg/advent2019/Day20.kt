/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 20 - Donut Maze
 * Problem Description: http://adventofcode.com/2019/day/20
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day20/
 */
package com.ginsberg.advent2019

import java.util.ArrayDeque

class Day20(input: List<String>) {

    private val maze: Set<Point2D> = parseMaze(input)
    private val portalData = linkPortals(input)
    private val portals: Map<Point2D, Point2D> = portalData.first
    private val begin: Point2D = portalData.second
    private val end: Point2D = portalData.third
    private val outwardX = setOf(2, input.first().length - 3)
    private val outwardY = setOf(2, input.size - 3)

    fun solvePart1(): Int =
        bfs()

    fun solvePart2(): Int =
        bfsLeveled()

    private fun bfs(): Int {
        val discovered = mutableSetOf<Point2D>().apply {
            add(begin)
        }
        val queue = ArrayDeque<MutableList<Point2D>>().apply {
            add(mutableListOf(begin))
        }

        while (queue.isNotEmpty()) {
            val here = queue.pop()
            if (here.first() == end) {
                return here.size - 1
            }
            here.first().neighborsWithPortals().forEach { neighbor ->
                if (neighbor !in discovered) {
                    discovered.add(neighbor)
                    queue.addLast(here.copyOf().also { it.add(0, neighbor) })
                }

            }
        }
        throw IllegalStateException("No path to end")
    }

    private fun bfsLeveled(): Int {
        val discovered = mutableSetOf<Pair<Point2D, Int>>().apply {
            add(begin to 0)
        }
        val queue = ArrayDeque<MutableList<Pair<Point2D, Int>>>().apply {
            add(mutableListOf(begin to 0))
        }

        while (queue.isNotEmpty()) {
            val path = queue.pop()
            if (path.first() == end to 0) {
                return path.size - 1
            }
            path.first().neighborsWithPortalsAndLevels().filterNot { it in discovered }.forEach { neighbor ->
                discovered.add(neighbor)
                queue.addLast(path.copyOf().also { it.add(0, neighbor) })
            }
        }
        throw IllegalStateException("No path to end")
    }

    private fun Point2D.neighborsWithPortals(): List<Point2D> {
        val neighbors = this.neighbors().filter { it in maze }
        return (neighbors + portals[this]).filterNotNull()
    }

    private fun Pair<Point2D, Int>.neighborsWithPortalsAndLevels(): List<Pair<Point2D, Int>> {
        val neighbors = this.first.neighbors().filter { it in maze }.map { Pair(it, this.second) }.toMutableList()
        portals[this.first]?.let {
            val levelDiff = if (this.first.x in outwardX || this.first.y in outwardY) -1 else 1
            neighbors.add(it to this.second + levelDiff)
        }
        return neighbors.filter { it.second >= 0 }
    }

    private fun parseMaze(input: List<String>): Set<Point2D> =
        input.mapIndexed { y, row ->
            row.mapIndexedNotNull { x, c -> if (c == '.') Point2D(x, y) else null }
        }.flatten().toSet()

    private fun linkPortals(input: List<String>): Triple<Map<Point2D, Point2D>, Point2D, Point2D> {
        val linked = mutableMapOf<Point2D, Point2D>()
        val unlinked = mutableMapOf<String, Point2D>()
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                if (c in letters) {
                    val at = Point2D(x, y)
                    var name: String? = null
                    var portal: Point2D? = null

                    when {
                        input.getOrNull(at.south()) in letters && at.north() in maze -> {
                            name = "$c${input.getOrNull(at.south())}"
                            portal = at.north()
                        }
                        input.getOrNull(at.south()) in letters && at.south().south() in maze -> {
                            name = "$c${input.getOrNull(at.south())}"
                            portal = at.south().south()
                        }
                        input.getOrNull(at.east()) in letters && at.west() in maze -> {
                            name = "$c${input.getOrNull(at.east())}"
                            portal = at.west()
                        }
                        input.getOrNull(at.east()) in letters && at.east().east() in maze -> {
                            name = "$c${input.getOrNull(at.east())}"
                            portal = at.east().east()
                        }
                    }

                    if (name != null && portal != null) {
                        if (name in unlinked) {
                            linked[portal] = unlinked[name]!!
                            linked[unlinked[name]!!] = portal
                            unlinked.remove(name)
                        } else {
                            unlinked[name] = portal
                        }
                    }
                }
            }
        }
        return Triple(linked, unlinked["AA"]!!, unlinked["ZZ"]!!)
    }

    companion object {
        val letters = 'A'..'Z'
    }
}
