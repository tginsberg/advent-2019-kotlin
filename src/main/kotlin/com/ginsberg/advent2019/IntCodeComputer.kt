/*
 * Copyright (c) 2019 by Todd Ginsberg
 */

package com.ginsberg.advent2019

import com.ginsberg.advent2019.IntCodeInstruction.Halt
import kotlin.math.pow

class IntCodeComputer(private val program: IntArray,
                      private val input: MutableList<Int> = mutableListOf()
) {
    // Single input constructor
    constructor(program: IntArray, singleInput: Int) : this(program, mutableListOf(singleInput))

    fun run(): List<Int> {
        var instructionPointer = 0
        val output = mutableListOf<Int>()

        do {
            val nextOp = IntCodeInstruction(instructionPointer, program)
            instructionPointer += nextOp.execute(instructionPointer, program, input, output)
        } while (nextOp !is Halt)

        return output
    }

}

sealed class IntCodeInstruction(internal val nextInstructionOffset: Int) {

    companion object {
        operator fun invoke(pointer: Int, program: IntArray): IntCodeInstruction {
            return when ( val operation = program[pointer] % 100) {
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

    abstract fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int

    object Add : IntCodeInstruction(4) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = program.param(1, pointer) + program.param(2, pointer)
            return nextInstructionOffset
        }
    }

    object Multiply : IntCodeInstruction(4) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = program.param(1, pointer) * program.param(2, pointer)
            return nextInstructionOffset
        }
    }

    object Input : IntCodeInstruction(2) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val writeTo = program[pointer + 1]
            program[writeTo] = input.removeAt(0)
            return nextInstructionOffset
        }
    }

    object Output : IntCodeInstruction(2) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            output.add(program.param(1, pointer))
            return nextInstructionOffset
        }
    }

    object JumpIfTrue : IntCodeInstruction(3) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val a = program.param(1, pointer)
            val b = program.param(2, pointer)
            return if (a != 0) b - pointer else nextInstructionOffset
        }
    }

    object JumpIfFalse : IntCodeInstruction(3) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val a = program.param(1, pointer)
            val b = program.param(2, pointer)
            return if (a == 0) b - pointer else nextInstructionOffset
        }
    }

    object LessThan : IntCodeInstruction(4) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = if (program.param(1, pointer) < program.param(2, pointer)) 1 else 0
            return nextInstructionOffset
        }
    }

    object Equals : IntCodeInstruction(4) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int {
            val writeTo = program[pointer + 3]
            program[writeTo] = if (program.param(1, pointer) == program.param(2, pointer)) 1 else 0
            return nextInstructionOffset
        }
    }

    object Halt : IntCodeInstruction(1) {
        override fun execute(pointer: Int, program: IntArray, input: MutableList<Int>, output: MutableList<Int>): Int = 0
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
