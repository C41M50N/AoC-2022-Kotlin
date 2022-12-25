package cfsb.crimsoncoder.advent2022

class Day04(input: String) {
    private val assignmentPairs = parseInput(input)

    fun solveP1(): Int = assignmentPairs.count { it.first.fullyContains(it.second) }

    fun solveP2(): Int = assignmentPairs.count { it.first.partiallyContains(it.second) }


    private fun parseInput(input: String): List<Pair<IntRange, IntRange>> {
        return input
            .trim()
            .split("\r\n")
            .map { line ->
                val (a,b,c,d) = line.split("-",",", limit = 4).map { it.toInt() }
                Pair(a..b, c..d)
            }
    }
}

private fun IntRange.fullyContains(other: IntRange): Boolean {
    val result1 = this.toSet().minus(other.toSet())
    val result2 = other.toSet().minus(this.toSet())

    return result1.isEmpty() or result2.isEmpty()
}

private fun IntRange.partiallyContains(other: IntRange): Boolean {
    val firstSet = this.toSet()
    val otherSet = other.toSet()

    val result1 = firstSet.minus(otherSet)
    val result2 = otherSet.minus(firstSet)

    return (result1.size < firstSet.size) or (result2.size < otherSet.size)
}

fun main() {
    val a = IntRange(6,6)
    val b = IntRange(6,6)

    println(a.fullyContains(b))

    val s = "2-4,6-8"
    println(s.split("-",",", limit = 4))
}
