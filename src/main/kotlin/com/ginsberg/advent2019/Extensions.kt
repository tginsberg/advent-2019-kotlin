/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import kotlinx.coroutines.channels.Channel
import java.math.BigInteger
import java.util.BitSet


fun <T> List<T>.permutations(): List<List<T>> =
    if (this.size <= 1) listOf(this)
    else {
        val elementToInsert = first()
        drop(1).permutations().flatMap { permutation ->
            (0..permutation.size).map { i ->
                permutation.toMutableList().apply { add(i, elementToInsert) }
            }
        }
    }

fun <T> List<T>.toChannel(capacity: Int = Channel.UNLIMITED): Channel<T> =
    Channel<T>(capacity).also { this.forEach { e -> it.offer(e) } }

fun <T> Collection<Collection<T>>.flattenRoundRobin(): List<T> =
    this.flatMap { it.withIndex() }.sortedBy { it.index }.map { it.value }

fun <T> List<T>.everyPair(): List<Pair<T, T>> =
    this.mapIndexed { idx, left ->
        this.drop(idx + 1).map { right ->
            Pair(left, right)
        }
    }.flatten()

fun <T, R> MutableMap<T, R>.copyOf(): MutableMap<T, R> =
    mutableMapOf<T, R>().also { it.putAll(this) }

fun <T> MutableList<T>.copyOf(): MutableList<T> =
    mutableListOf<T>().also { it.addAll(this) }

fun List<String>.getOrNull(point: Point2D): Char? =
    this.getOrNull(point.y)?.getOrNull(point.x)

fun <T> Map<Point2D, T>.print() {
    val maxX = this.keys.maxBy { it.x }!!.x
    val maxY = this.keys.maxBy { it.y }!!.y

    (0..maxY).forEach { y ->
        (0..maxX).forEach { x ->
            print(this.getOrDefault(Point2D(x, y), ' '))
        }
        println()
    }
}

fun BitSet.toBigInteger(): BigInteger =
    BigInteger(1, this.toByteArray().reversedArray())