/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import kotlinx.coroutines.channels.Channel

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