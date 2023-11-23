package cfsb.crimsoncoder.advent2022

import kotlin.math.abs

open class Instruction
class Noop(): Instruction()
data class Addx(val value: Int): Instruction()

class Day10(input: String) {
    private val data = parseInput(input)

    fun solveP1(): Int {
        val emulator = Emulator(listOf(20, 60, 100, 140, 180, 220))
        return emulator.processInstructions(data)
    }

    fun solveP2(): String {
        val emulator = Emulator(listOf(40, 80, 120, 160, 200, 240))
        return emulator.process(data)
    }


    private fun parseInput(input: String): List<Instruction> {
        return input
            .trim()
            .lines()
            .map { line ->
                when {
                    line.startsWith("noop") -> Noop()
                    line.startsWith("addx") -> {
                        val (instruction, value) = line.split(" ", limit = 2)
                        Addx(value.toInt())
                    }
                    else -> throw Exception("Bad input")
                }
            }
    }
}


class Emulator (private val specialCycles: List<Int>) {
    private var cycle: Int = 1
    private var x: Int = 1

    fun processInstructions(instructions: List<Instruction>): Int {
        var sum: Int = 0
        var wait: Int = 0
        var idx = 0
        while (idx < instructions.size) {
            println("During cycle $cycle, X is $x")
            if (cycle in specialCycles) {
                sum += (cycle * x)
            }

            val instruction = instructions[idx]
            if (instruction is Addx) {
                if (wait == 0) {
                    wait = 1
                } else if (wait == 1) {
                    println("$x ${instruction.value}")
                    x += instruction.value
                    wait = 0
                    idx++
                } else {
                    wait--
                }
            } else {
                idx++
            }

            cycle++
        }
        return sum
    }

    fun process(instructions: List<Instruction>): String {
        var res: String = ""
        var pos = 0
        var wait: Int = 0
        var idx = 0
        while (idx < instructions.size) {
            println("During cycle $cycle, X is $x, pos is $pos")
            println("Is ${pos + 1} in ${(x-1)..(x+1)}")
            if ((pos) in (x-1)..(x+1)) {
                res += "#"
            } else {
                res += "."
            }
            pos++

            if (cycle in specialCycles) {
                res += "\n"
                pos = 0
            }

            val instruction = instructions[idx]
            if (instruction is Addx) {
                if (wait == 0) {
                    wait = 1
                } else if (wait == 1) {
                    println("$x ${instruction.value}")
                    x += instruction.value
                    wait = 0
                    idx++
                } else {
                    wait--
                }
            } else {
                idx++
            }

            cycle++
        }
        return res
    }
}
