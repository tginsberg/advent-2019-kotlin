/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

/**
 * Advent of Code 2019, Day 8 -Space Image Format
 * Problem Description: http://adventofcode.com/2019/day/8
 * Blog Post/Commentary: https://todd.ginsberg.com/post/advent-of-code/2019/day8/
 */
package com.ginsberg.advent2019

class Day08(private val input: String) {

    fun solvePart1(): Int =
        input
            .chunked(lineLength)
            .map { layer -> layer.groupingBy { it }.eachCount() }
            .minBy { it.getOrDefault('0', 0) }
            ?.let { it.getOrDefault('1', 0) * it.getOrDefault('2', 0) }
            ?: throw IllegalStateException("Image Corrupted: Check Digital Sender Network Integrity")

    fun solvePart2() {
        val layers: List<String> = input.chunked(lineLength)
        (0 until lineLength)
            .map { layers.pixelAt(it) }
            .chunked(width)
            .forEach {
                println(it.joinToString(separator = ""))
            }
    }

    private fun List<String>.pixelAt(at: Int): Char =
        if(map { it[at] }.firstOrNull { it != '2' } == '1') '#' else ' '

    companion object {
        private const val width = 25
        private const val height = 6
        private const val lineLength = width * height
    }
}