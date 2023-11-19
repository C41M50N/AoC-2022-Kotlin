package cfsb.crimsoncoder.advent2022

import kotlin.math.abs

typealias Point = Pair<Int, Int>

class Day09(input: String) {
    private val data = parseInput(input)

    fun solveP1(): Int {
        val sim = RopeSimulator()
        println(data)
        sim.processOperations(data)
        println(sim.tailVisits)
        return sim.tailVisits.size
    }

    fun solveP2(): Int = 0


    private fun parseInput(input: String): List<RopeOperation> {
        return input
            .trim()
            .split("\r\n")
            .map { line ->
                val (a,b) = line.split(" ", limit = 2)
                val direction: RopeOperationDirection = when (a) {
                    "L" -> RopeOperationDirection.LEFT
                    "R" -> RopeOperationDirection.RIGHT
                    "U" -> RopeOperationDirection.UP
                    "D" -> RopeOperationDirection.DOWN
                    else -> RopeOperationDirection.LEFT
                }
                val times: Int = b.toInt()
                RopeOperation(direction, times)
            }
    }
}

enum class RopeOperationDirection {
    LEFT, RIGHT, UP, DOWN
}
data class RopeOperation (val direction: RopeOperationDirection, val times: Int)

class RopeSimulator {
    private var head: Point = Pair(0,0)
    private var tail: Point = Pair(0,0)

    val tailVisits = mutableSetOf<Point>(Pair(0,0))

    fun processOperations(operations: List<RopeOperation>) {
        for (op in operations) {
            when (op.direction) {
                RopeOperationDirection.LEFT -> {
                    repeat(op.times) {
                        stepLeft()
                    }
                }
                RopeOperationDirection.RIGHT -> {
                    repeat(op.times) {
                        stepRight()
                    }
                }
                RopeOperationDirection.UP -> {
                    repeat(op.times) {
                        stepUp()
                    }
                }
                RopeOperationDirection.DOWN -> {
                    repeat(op.times) {
                        stepDown()
                    }
                }
            }
        }
    }

    private fun stepLeft() {
        // move head
        head = Pair(head.first - 1, head.second)
        // check if tail needs to be moved
        if (!isAdjacent(head, tail)) {
            // calculate next tail
            tail = Pair(head.first + 1, head.second)
            // add to tailVisits
             tailVisits.add(tail)
        }
    }

    private fun stepRight() {
        // move head
        head = Pair(head.first + 1, head.second)
        // check if tail needs to be moved
        if (!isAdjacent(head, tail)) {
            // calculate next tail
            tail = Pair(head.first - 1, head.second)
            // add to tailVisits
            tailVisits.add(tail)
        }
    }

    private fun stepUp() {
        // move head
        head = Pair(head.first, head.second + 1)
        // check if tail needs to be moved
        if (!isAdjacent(head, tail)) {
            // calculate next tail
            tail = Pair(head.first, head.second - 1)
            // add to tailVisits
            tailVisits.add(tail)
        }
    }

    private fun stepDown() {
        // move head
        head = Pair(head.first, head.second - 1)
        // check if tail needs to be moved
        if (!isAdjacent(head, tail)) {
            // calculate next tail
            tail = Pair(head.first, head.second + 1)
            // add to tailVisits
            tailVisits.add(tail)
        }
    }

    private fun isAdjacent(p1: Point, p2: Point): Boolean {
        val xDif = abs( p1.first - p2.first )
        val yDif = abs( p1.second - p2.second )
        if (xDif == 1 && yDif == 1) {
            return true
        } else if (xDif == 1 && yDif == 0) {
            return true
        } else if (xDif == 0 && yDif == 1) {
            return true
        }
        return false
    }
}
