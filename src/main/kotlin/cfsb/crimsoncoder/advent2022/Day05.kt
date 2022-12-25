package cfsb.crimsoncoder.advent2022

import java.util.*

class Day05(input: String) {
    private val data = parseInput(input)

    fun solveP1(): String {
        val structure = Structure.fromInput(data.first)
        val operations = data.second.map { Operation(it.first, it.second, it.third) }

        operations.forEach {
            structure.processOperation(it)
        }

        return structure.getTops()
    }

    fun solveP2(): String {
        val structure = Structure.fromInput(data.first)
        val operations = data.second.map { Operation(it.first, it.second, it.third) }

        operations.forEach {
            structure.processOperation2(it)
        }

        return structure.getTops()
    }


    private fun parseInput(input: String): Pair<List<Stack<Char>>, List<Triple<Int, Int, Int>>> {
        val (crateStacks, operations) = input.split("\r\n\r\n", limit = 2)

        val numOfStacks = crateStacks.split("\r\n").last().length / 4 + 1
        val crateStacksList = buildList<Stack<Char>> { repeat(numOfStacks) { add(Stack()) } }
        for (line in crateStacks.split("\r\n")) {
            for ((i, c) in line.withIndex()) {
                if (c.isLetter() && c != ' ' && c != '[' && c != ']') {
                    val stackIndex: Int = i / 4
                    (crateStacksList[stackIndex]).push(c)
                }
            }
        }

        val operationsList = mutableListOf<Triple<Int, Int, Int>>()
        val regex = Regex("move (\\d+) from (\\d) to (\\d)")
        for (line in operations.split("\r\n")) {
            val (numCrates, src, dst) = regex.find(line)!!.destructured
            operationsList.add(Triple(numCrates.toInt(), src.toInt(), dst.toInt()))
        }

        return Pair(crateStacksList, operationsList)
    }
}

data class Operation (val numCrates: Int, val src: Int, val dst: Int)

class Structure private constructor (
    private val stacks: List<Stack<Char>>
) {

    fun processOperation (operation: Operation) {
        val (numCrates, src, dst) = operation
        repeat(numCrates) {
            stacks[dst-1].push(stacks[src-1].pop())
        }
    }

    fun processOperation2 (operation: Operation) {
        val (numCrates, src, dst) = operation
        val itemsToMove = sequence {
            repeat(numCrates) {
                yield(stacks[src-1].pop())
            }
        }.toList()

        stacks[dst-1].push(itemsToMove)
    }

    fun getTops(): String = stacks.fold("") { acc, it -> "$acc${it.peek()}" }

    companion object {
        fun fromInput(input: List<Stack<Char>>): Structure {
            val stacks = mutableListOf<Stack<Char>>()
            for ((i,stack) in input.withIndex()) {
                stacks.add(Stack())
                while (stack.isNotEmpty()) {
                    stacks[i].push(stack.pop())
                }
            }
            println("New Stacks")
            println(stacks)
            return Structure(stacks)
        }
    }
}

private fun Stack<Char>.push(items: List<Char>) {
    for (item in items.asReversed()) {
        this.push(item)
    }
}
