/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import java.util.ArrayDeque

fun findAllPaths(from: Point2D,
                         to: Point2D?,
                         validNeighbor: (Point2D) -> Boolean): Set<List<Point2D>> {
    val paths = mutableSetOf<List<Point2D>>()
    val visited = mutableSetOf<Point2D>()
    val queue = ArrayDeque<MutableList<Point2D>>().apply { add(mutableListOf(from)) }
    while (queue.isNotEmpty()) {
        val thisPath = queue.pop()
        if (thisPath.last() == to) {
            paths.add(thisPath)
        }
        if (thisPath.last() !in visited) {
            visited.add(thisPath.last())
            val neighbors = thisPath.last().neighbors().filter { validNeighbor(it) }.filter { it !in visited }
            if (neighbors.isEmpty() && to == null) {
                paths.add(thisPath)
            } else {
                neighbors.forEach { neighbor ->
                    queue.push(thisPath.copyOf().apply { add(neighbor) })
                }
            }
        }
    }
    return paths
}
