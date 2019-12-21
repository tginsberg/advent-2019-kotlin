/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import java.util.ArrayDeque

fun findPaths(from: Point2D,
              to: Point2D?,
              validNeighbor: (Point2D) -> Boolean): Sequence<List<Point2D>> = sequence {
    val visited = mutableSetOf<Point2D>()
    val queue = ArrayDeque<MutableList<Point2D>>().apply { add(mutableListOf(from)) }
    while (queue.isNotEmpty()) {
        val thisPath = queue.pop()
        if (thisPath.last() == to) {
            yield(thisPath)
        }
        if (thisPath.last() !in visited) {
            visited.add(thisPath.last())
            val neighbors = thisPath.last().neighbors().filter { validNeighbor(it) }.filter { it !in visited }
            if (neighbors.isEmpty() && to == null) {
                yield(thisPath)
            } else {
                neighbors.forEach { neighbor ->
                    queue.add(thisPath.copyOf().apply { add(neighbor) })
                }
            }
        }
    }
}
