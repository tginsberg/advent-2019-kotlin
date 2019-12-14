/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 14 - Space Stoichiometry
 * Problem Description: http://adventofcode.com/2019/day/14
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day14/
 */
package com.ginsberg.advent2019

import kotlin.math.ceil
import kotlin.math.sign

class Day14(input: List<String>) {

    private val cost: Map<String, Pair<Long, List<Pair<Long, String>>>> = parseInput(input)

    fun solvePart1(): Long =
        calculateCost()

    fun solvePart2(): Long =
        (0L..one_trillion).binarySearchBy { one_trillion.compareTo(calculateCost(amount = it)) }

    private fun calculateCost(material: String = "FUEL",
                              amount: Long = 1,
                              inventory: MutableMap<String, Long> = mutableMapOf()): Long =
        if (material == "ORE") amount
        else {
            val inventoryQuantity = inventory.getOrDefault(material, 0L)
            val needQuantity = if (inventoryQuantity > 0) {
                // We have some in inventory, check it out of inventory and reduce our need.
                inventory[material] = (inventoryQuantity - amount).coerceAtLeast(0)
                amount - inventoryQuantity
            } else amount

            if (needQuantity > 0) {
                val recipe = cost.getValue(material)
                val iterations: Int = ceil(needQuantity.toDouble() / recipe.first).toInt()
                val actuallyProduced = recipe.first * iterations
                if (needQuantity < actuallyProduced) {
                    // Put excess in inventory
                    inventory[material] = inventory.getOrDefault(material, 0) + actuallyProduced - needQuantity
                }
                // Go produce each of our dependencies
                recipe.second.map { calculateCost(it.second, it.first * iterations, inventory) }.sum()
            } else {
                0
            }
        }

    private fun LongRange.binarySearchBy(fn: (Long) -> Int): Long {
        var low = this.first
        var high = this.last
        while (low <= high) {
            val mid = (low + high) / 2
            when (fn(mid).sign) {
                -1 -> high = mid - 1
                1 -> low = mid + 1
                0 -> return mid // Exact match
            }
        }
        return low - 1 // Our next best guess
    }

    private fun parseInput(input: List<String>): Map<String, Pair<Long, List<Pair<Long, String>>>> =
        input.map { row ->
            val split: List<String> = row.split(" => ")
            val left: List<Pair<Long, String>> = split.first().split(",")
                .map { it.trim() }
                .map {
                    it.split(" ").let { r ->
                        Pair(r.first().toLong(), r.last())
                    }
                }
            val (amount, type) = split.last().split(" ")
            type to Pair(amount.toLong(), left)
        }.toMap()

    companion object {
        private const val one_trillion = 1_000_000_000_000L
    }

}
