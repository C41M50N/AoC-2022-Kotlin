package cfsb.crimsoncoder.advent2022

import java.util.LinkedList
import kotlin.math.abs

typealias Point = Pair<Int, Int>

data class PointNode (var point: Point, var next: PointNode?)
data class PointsList (var head: PointNode?) {
    fun add(point: Point) {
        var curr: PointNode? = head
        if (curr == null) {
            head = PointNode(point, null)
            return;
        }

        while (curr?.next != null) {
            curr = curr.next
        }
        curr?.next = PointNode(point, null)
    }

    fun print() {
        var curr: PointNode? = head
        while (curr != null) {
            print("${curr.point}")
            if (curr.next != null) print(", ")
            curr = curr.next
        }
    }
}



class Day09(input: String) {
    private val data = parseInput(input)

    fun solveP1(): Int {
        val sim = RopeSimulator(2)
        sim.processOperations(data)
        println(sim.tailVisits)
        return sim.tailVisits.size
    }

    fun solveP2(): Int {
        val sim = RopeSimulator(10)
        sim.processOperations(data)
        println(sim.tailVisits)
        return sim.tailVisits.size
    }


    private fun parseInput(input: String): List<RopeOperation> {
        return input
            .trim()
            .lines()
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
    private var list: PointsList = PointsList(null)
    constructor(knots: Int) {
        repeat(knots) {
            list.add(Point(0,0))
        }
    }

    val tailVisits = mutableSetOf<Point>(Pair(0,0))

    fun processOperations(operations: List<RopeOperation>) {
        for (op in operations) {
            repeat(op.times) {
                processStep(op.direction)
            }
            println("Operation: $op")
            print("State: ")
            list.print()
            println("")
        }
    }

    private fun processStep(direction: RopeOperationDirection) {
        // move head
        when (direction) {
            RopeOperationDirection.LEFT -> {
                list.head!!.point = Pair(list.head!!.point.first - 1, list.head!!.point.second)
            }
            RopeOperationDirection.RIGHT -> {
                list.head!!.point = Pair(list.head!!.point.first + 1, list.head!!.point.second)
            }
            RopeOperationDirection.UP -> {
                list.head!!.point = Pair(list.head!!.point.first, list.head!!.point.second + 1)
            }
            RopeOperationDirection.DOWN -> {
                list.head!!.point = Pair(list.head!!.point.first, list.head!!.point.second - 1)
            }
        }

        // move followers, if necessary
        var current = list.head
        var next = current?.next
        while (current != null && next != null) {
            step(current, next, next.next == null)

            current = current.next
            next = current?.next
        }
    }

    private fun step(leader: PointNode, follower: PointNode, isTail: Boolean) {
        if (!isTouching(leader.point, follower.point)) {
            val xDif = leader.point.first - follower.point.first
            val yDif = leader.point.second - follower.point.second


            if ((yDif >= 2 && xDif <= -1) || (xDif <= -2 && yDif >= 1)) { // up and left
                follower.point = Point(follower.point.first - 1, follower.point.second + 1)
            } else if ((yDif >= 2 && xDif >= 1) || (xDif >= 2 && yDif >= 1)) { // up and right
                follower.point = Point(follower.point.first + 1, follower.point.second + 1)
            } else if ((xDif <= -2 && yDif <= -1) || (yDif <= -2 && xDif <= -1)) { // down and left
                follower.point = Point(follower.point.first - 1, follower.point.second - 1)
            } else if ((xDif >= 2 && yDif <= -1) || (yDif <= -2 && xDif >= 1)) { // down and right
                follower.point = Point(follower.point.first + 1, follower.point.second - 1)
            } else if (xDif <= -2 && yDif == 0) { // move to the left
                follower.point = Point(follower.point.first - 1, follower.point.second)
            } else if (xDif >= 2 && yDif == 0) { // move to the right
                follower.point = Point(follower.point.first + 1, follower.point.second)
            } else if (yDif >= 2 && xDif == 0) { // move up
                follower.point = Point(follower.point.first, follower.point.second + 1)
            } else if (yDif <= -2 && xDif == 0) { // move down
                follower.point = Point(follower.point.first, follower.point.second - 1)
            }

            if (xDif > 2 || xDif < -2 || yDif > 2 || yDif < -2) {
                println("SOMETHINGS WRONG.................")
            }

            if (isTail) tailVisits.add(follower.point)
        }
    }

    private fun isTouching(p1: Point, p2: Point): Boolean {
        val xDif = abs( p1.first - p2.first )
        val yDif = abs( p1.second - p2.second )
        return xDif <= 1 && yDif <= 1
    }
}
