/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 22 - Slam Shuffle
 * Problem Description: http://adventofcode.com/2019/day/22
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day22/
 */
package com.ginsberg.advent2019

import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO
import kotlin.math.absoluteValue

class Day22(private val input: List<String>) {

    fun solvePart1(): Int =
        followInstructions().indexOf(2019)

    fun solvePart2(): BigInteger =
        modularArithmeticVersion(2020.toBigInteger())

    private fun modularArithmeticVersion(find: BigInteger): BigInteger {
        val memory = arrayOf(ONE, ZERO)
        input.reversed().forEach { instruction ->
            when {
                "cut" in instruction ->
                    memory[1] += instruction.getBigInteger()
                "increment" in instruction ->
                    instruction.getBigInteger().modPow(NUMBER_OF_CARDS - TWO, NUMBER_OF_CARDS).also {
                        memory[0] *= it
                        memory[1] *= it
                    }
                "stack" in instruction -> {
                    memory[0] = memory[0].negate()
                    memory[1] = (memory[1].inc()).negate()
                }
            }
            memory[0] %= NUMBER_OF_CARDS
            memory[1] %= NUMBER_OF_CARDS
        }
        val power = memory[0].modPow(SHUFFLES, NUMBER_OF_CARDS)
        return ((power * find) + ((memory[1] * (power + NUMBER_OF_CARDS.dec())) * ((memory[0].dec()).modPow(NUMBER_OF_CARDS - TWO, NUMBER_OF_CARDS)))).mod(NUMBER_OF_CARDS)
    }

    private fun followInstructions(): List<Int> =
        input.fold(createDeck()) { deck, instruction ->
            when {
                "cut" in instruction -> deck.cut(instruction.getInt())
                "increment" in instruction -> deck.deal(instruction.getInt())
                "stack" in instruction -> deck.reversed()
                else -> throw IllegalArgumentException("Invalid instruction: $instruction")
            }
        }

    private fun createDeck(length: Int = 10_007): List<Int> =
        List(length) { it }

    private fun List<Int>.cut(n: Int): List<Int> =
        when {
            n > 0 -> this.drop(n) + this.take(n)
            n < 0 -> this.takeLast(n.absoluteValue) + this.dropLast(n.absoluteValue)
            else -> this
        }

    private fun List<Int>.deal(increment: Int): List<Int> {
        val newDeck = this.toMutableList()
        var place = 0
        this.forEach { card ->
            newDeck[place] = card
            place += increment
            place %= this.size
        }
        return newDeck
    }

    private fun String.getInt(): Int =
        this.split(" ").last().toInt()

    private fun String.getBigInteger(): BigInteger =
        this.split(" ").last().toBigInteger()

    companion object {
        val NUMBER_OF_CARDS = 119315717514047.toBigInteger()
        val SHUFFLES = 101741582076661.toBigInteger()
        val TWO = 2.toBigInteger()
    }
}
