/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 24 - Planet of Discord
 * Problem Description: http://adventofcode.com/2019/day/24
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day24/
 */
package com.ginsberg.advent2019

import java.math.BigInteger
import java.util.BitSet


class Day24(input: List<String>) {

    private val bounds: Point2D = Point2D(input.first().length - 1, input.size - 1)
    private val startGrid: BitSet = parseInput(input)
    private var layers = mutableMapOf(0 to startGrid)
    private val midPoint = Point2D(bounds.x / 2, bounds.y / 2)

    fun solvePart1(): BigInteger =
        firstDuplicate(startGrid).toBigInteger()

    fun solvePart2(minutes: Int): Int =
        (1..minutes).fold(layers) { carry, _ -> carry.next() }.values.sumBy { it.cardinality() }

    private fun firstDuplicate(start: BitSet): BitSet {
        val seen = mutableSetOf<BitSet>()
        var latest = start
        do {
            seen += latest
            latest = latest.next()
        } while (latest !in seen)
        return latest
    }

    private fun BitSet.at(point: Point2D): Boolean =
        this[point.offset()]

    private fun BitSet.bitAt(point: Point2D): Int =
        if (this[point.offset()]) 1 else 0

    private fun BitSet.columnCardinality(x: Int): Int =
        (0..bounds.y).map { y -> this[(y * bounds.y.inc()) + x] }.count { it }

    private fun BitSet.countNeighbors(at: Point2D): Int =
        at.neighbors()
            .filter { it.x >= 0 && it.y >= 0 }
            .filter { it.x <= bounds.x && it.y <= bounds.y }
            .count { this.at(it) }

    private fun BitSet.next(): BitSet =
        BitSet().also { newBitSet ->
            bounds.upTo().forEach { point ->
                val neighbors = this.countNeighbors(point)
                val alive = this[point.offset()]
                newBitSet.setNextRound(point, alive, neighbors)
            }
        }

    private fun BitSet.rowCardinality(y: Int): Int {
        val start = y * (bounds.x.inc())
        return this[start, start + bounds.y + 1].cardinality()
    }

    private fun BitSet.setNextRound(point: Point2D, alive: Boolean, neighbors: Int) {
        this[point.offset()] = when {
            alive -> neighbors == 1
            !alive && neighbors in setOf(1, 2) -> true
            else -> alive
        }
    }

    private fun MutableMap<Int, BitSet>.addEmptyEdges(): MutableMap<Int, BitSet> {
        val min = this.keys.min()!!
        if (!this[min]!!.isEmpty) {
            this[min - 1] = BitSet()
        }

        val max = this.keys.max()!!
        if (!this[max]!!.isEmpty) {
            this[max + 1] = BitSet()
        }
        return this
    }

    private fun MutableMap<Int, BitSet>.next(): MutableMap<Int, BitSet> =
        this.addEmptyEdges()
            .map { (layer, board) ->
                layer to BitSet().also { newBitSet ->
                    bounds.upTo().filterNot { it == midPoint }.forEach { point ->
                        val neighbors = this.countNeighbors(point, layer)
                        val alive = board[point.offset()]
                        newBitSet.setNextRound(point, alive, neighbors)
                    }
                }
            }.toMap().toMutableMap()

    private fun Map<Int, BitSet>.countNeighbors(at: Point2D, layer: Int = 0): Int =
        at.neighbors()
            .filterNot { it == midPoint }
            .filter { it.x >= 0 && it.y >= 0 }
            .filter { it.x <= bounds.x && it.y <= bounds.y }
            .count { this.getValue(layer).at(it) }
            .plus(counterOuterNeighbors(layer, at))
            .plus(this.countInnerNeighbors(layer, at))

    private fun Map<Int, BitSet>.countInnerNeighbors(layer: Int, at: Point2D): Int =
        this[layer+1]?.let {
            when (at) {
                midPoint.north() -> it.rowCardinality(0)
                midPoint.south() -> it.rowCardinality(bounds.y)
                midPoint.east() -> it.columnCardinality(bounds.x)
                midPoint.west() -> it.columnCardinality(0)
                else -> 0
            }
        } ?: 0

    private fun Map<Int, BitSet>.counterOuterNeighbors(layer: Int, at: Point2D): Int =
        this[layer - 1]?.let {
            listOf(
                if (at.x == 0) it.bitAt(midPoint.west()) else 0,
                if (at.x == bounds.x) it.bitAt(midPoint.east()) else 0,
                if (at.y == 0) it.bitAt(midPoint.north()) else 0,
                if (at.y == bounds.y) it.bitAt(midPoint.south()) else 0
            ).sum()
        } ?: 0

    private fun Point2D.offset(): Int =
        this.x + (this.y * (bounds.y + 1))

    private fun Point2D.upTo(): Sequence<Point2D> = sequence {
        (0..y).forEach { y ->
            (0..x).forEach { x ->
                yield(Point2D(x, y))
            }
        }
    }

    private fun parseInput(input: List<String>): BitSet =
        BitSet().also { bitSet ->
            bounds.upTo().forEach { at ->
                bitSet[at.offset()] = input[at.y][at.x] == '#'
            }
        }
}
