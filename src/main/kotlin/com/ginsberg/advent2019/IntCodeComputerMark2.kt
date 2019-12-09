/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import kotlinx.coroutines.channels.Channel
import kotlin.math.pow

class IntCodeComputerMk2(
    private val program: MutableMap<Long, Long>,
    val input: Channel<Long> = Channel(Channel.UNLIMITED),
    val output: Channel<Long> = Channel(Channel.CONFLATED)
) {

    private var halted = false
    private var instructionPointer = 0L
    private var relativeBase = 0L

    suspend fun runProgram() {
        while (!halted) {
            step()
        }
        output.close()
    }

    private suspend fun step() =
        when (val opId = (program.getValue(instructionPointer) % 100L).toInt()) {
            1 -> { // Add
                program[writeParam(3)] = readParam(1) + readParam(2)
                instructionPointer += 4
            }
            2 -> { // Multiply
                program[writeParam(3)] = readParam(1) * readParam(2)
                instructionPointer += 4
            }
            3 -> { // Input
                program[writeParam(1)] = input.receive()
                instructionPointer += 2
            }
            4 -> { // Output
                output.send(readParam(1))
                instructionPointer += 2
            }
            5 -> { // JumpIfTrue
                if (readParam(1) != 0L) {
                    instructionPointer = readParam(2)
                } else {
                    instructionPointer += 3
                }
            }
            6 -> { // JumpIfFalse
                if (readParam(1) == 0L) {
                    instructionPointer = readParam(2)
                } else {
                    instructionPointer += 3
                }
            }
            7 -> { // LessThan
                program[writeParam(3)] = if (readParam(1) < readParam(2)) 1L else 0L
                instructionPointer += 4
            }
            8 -> { // Equals
                program[writeParam(3)] = if (readParam(1) == readParam(2)) 1L else 0L
                instructionPointer += 4

            }
            9 -> { //AdjustBase
                relativeBase += readParam(1)
                instructionPointer += 2
            }
            99 -> { // Halt
                halted = true
            }
            else -> throw IllegalArgumentException("Unknown operation: $opId")
        }

    private fun readParam(paramNo: Int): Long =
        program.getReadRef(program.getOrDefault(instructionPointer, 0L).toParameterMode(paramNo), instructionPointer + paramNo)

    private fun writeParam(paramNo: Int): Long =
        program.getWriteRef(program.getOrDefault(instructionPointer, 0L).toParameterMode(paramNo), instructionPointer + paramNo)

    private fun Long.toParameterMode(pos: Int): Int =
        (this / (10.0.pow(pos + 1).toInt()) % 10).toInt()

    private fun MutableMap<Long, Long>.getReadRef(mode: Int, at: Long): Long =
        when (mode) {
            0 -> getOrDefault(getOrDefault(at, 0L), 0L)
            1 -> getOrDefault(at, 0L)
            2 -> getOrDefault(getOrDefault(at, 0) + relativeBase, 0)
            else -> throw IllegalArgumentException("Unknown read mode: $mode")
        }

    private fun MutableMap<Long, Long>.getWriteRef(mode: Int, at: Long): Long =
        when (mode) {
            0 -> getOrDefault(at, 0L)
            2 -> getOrDefault(at, 0L) + relativeBase
            else -> throw IllegalArgumentException("Unknown write mode: $mode")
        }

}


