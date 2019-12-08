/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 7 - Amplification Circuit
 * Problem Description: http://adventofcode.com/2019/day/7
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day7/
 */
package com.ginsberg.advent2019

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class Day07(input: String) {

    private val program: IntArray = input.split(",").map { it.toInt() }.toIntArray()

    fun solvePart1(): Int =
        listOf(0, 1, 2, 3, 4).permutations().map { runPhase(it) }.max()
            ?: throw IllegalStateException("No max value, something is wrong")

    fun solvePart2(): Int = runBlocking {
        listOf(5, 6, 7, 8, 9).permutations().map { runAmplified(it) }.max()
            ?: throw IllegalStateException("No max value, something is wrong")
    }

    private fun runPhase(settings: List<Int>): Int =
        (0..4).fold(0) { past, id ->
            IntCodeComputer(program.copyOf(), mutableListOf(settings[id], past)).run().first()
        }

    private suspend fun runAmplified(settings: List<Int>): Int = coroutineScope {

        val a = IntCodeComputer(program.copyOf(), listOf(settings[0], 0).toChannel())
        val b = IntCodeComputer(program.copyOf(), a.output.andSend(settings[1]))
        val c = IntCodeComputer(program.copyOf(), b.output.andSend(settings[2]))
        val d = IntCodeComputer(program.copyOf(), c.output.andSend(settings[3]))
        val e = IntCodeComputer(program.copyOf(), d.output.andSend(settings[4]))
        val channelSpy = ChannelSpy(e.output, a.input)

        val spy: Deferred<Unit> = async { channelSpy.listen() }
        awaitAll(
            async { a.runSuspending() },
            async { b.runSuspending() },
            async { c.runSuspending() },
            async { d.runSuspending() },
            async { e.runSuspending() }
        )
        spy.cancel()
        channelSpy.spy.receive()
    }

    private suspend fun <T> Channel<T>.andSend(msg: T): Channel<T> =
        this.also { send(msg) }
}

class ChannelSpy<T>(
    private val input: Channel<T>,
    private val output: Channel<T>,
    val spy: Channel<T> = Channel(Channel.CONFLATED)) {

    suspend fun listen() = coroutineScope {
        for (received in input) {
            spy.send(received)
            output.send(received)
        }
    }
}