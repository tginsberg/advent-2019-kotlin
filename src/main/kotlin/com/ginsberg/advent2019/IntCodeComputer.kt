/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.IntCodeInstruction.Halt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.runBlocking
import kotlin.math.pow

class IntCodeComputer(private val program: IntArray,
                      val input: Channel<Int>
) {

    val output: Channel<Int> = Channel(Channel.UNLIMITED)

    /**
     * These were constructors before we made this class work with coroutines. In
     * order to not have to change our existing calls, we've defined two new versions
     * of the invoke operator so callers can't tell the difference!
     */
    companion object {
        operator fun invoke(program: IntArray, singleInput: Int) = runBlocking {
            IntCodeComputer(program, mutableListOf(singleInput).toChannel())
        }

        operator fun invoke(program: IntArray, input: MutableList<Int> = mutableListOf()) = runBlocking {
            IntCodeComputer(program, input.toChannel())
        }
    }

    fun run(): List<Int> = runBlocking {
        runSuspending()
        output.toList()
    }

    suspend fun runSuspending() {
        var instructionPointer = 0

        do {
            val nextOp = IntCodeInstruction(instructionPointer, program)
            instructionPointer += nextOp.execute(instructionPointer, program, input, output)
        } while (nextOp !is Halt)

        output.close()
    }

}

sealed class IntCodeInstruction(internal val nextInstructionOffset: Int) {

    companion object {
        operator fun invoke(pointer: Int, program: IntArray): IntCodeInstruction {
            return when (val operation = program[pointer] % 100) {
                1 -> Add
                2 -> Multiply
                3 -> Input
                4 -> Output
                5 -> JumpIfTrue
                6 -> JumpIfFalse
                7 -> LessThan
                8 -> Equals
                99 -> Halt
                else -> throw IllegalArgumentException("Unknown operation: $operation")
            }
        }
    }

    abstract suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int

    object Add : IntCodeInstruction(4) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = program.param(1, pointer) + program.param(2, pointer)
            return nextInstructionOffset
        }
    }

    object Multiply : IntCodeInstruction(4) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = program.param(1, pointer) * program.param(2, pointer)
            return nextInstructionOffset
        }
    }

    object Input : IntCodeInstruction(2) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val writeTo = program[pointer + 1]
            program[writeTo] = input.receive()
            return nextInstructionOffset
        }
    }

    object Output : IntCodeInstruction(2) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            output.send(program.param(1, pointer))
            return nextInstructionOffset
        }
    }

    object JumpIfTrue : IntCodeInstruction(3) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val a = program.param(1, pointer)
            val b = program.param(2, pointer)
            return if (a != 0) b - pointer else nextInstructionOffset
        }
    }

    object JumpIfFalse : IntCodeInstruction(3) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val a = program.param(1, pointer)
            val b = program.param(2, pointer)
            return if (a == 0) b - pointer else nextInstructionOffset
        }
    }

    object LessThan : IntCodeInstruction(4) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = if (program.param(1, pointer) < program.param(2, pointer)) 1 else 0
            return nextInstructionOffset
        }
    }

    object Equals : IntCodeInstruction(4) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = if (program.param(1, pointer) == program.param(2, pointer)) 1 else 0
            return nextInstructionOffset
        }
    }

    object Halt : IntCodeInstruction(1) {
        override suspend fun execute(pointer: Int, program: IntArray, input: ReceiveChannel<Int>, output: Channel<Int>): Int = 0
    }

    internal fun IntArray.param(paramNo: Int, offset: Int): Int =
        this.getRef(this[offset].toParameterMode(paramNo), offset + paramNo)

    private fun Int.toParameterMode(pos: Int): Int =
        this / (10.0.pow(pos + 1).toInt()) % 10

    private fun IntArray.getRef(mode: Int, at: Int): Int =
        when (mode) {
            0 -> this[this[at]]
            1 -> this[at]
            else -> throw IllegalArgumentException("Unknown mode: $mode")
        }

}
