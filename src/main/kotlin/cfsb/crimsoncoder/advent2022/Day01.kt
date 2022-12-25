package cfsb.crimsoncoder.advent2022

class Day01(input: String) {
    private val calories = parseInput(input)

    fun solveP1(): Int = calories[0]

    fun solveP2(): Int = calories.take(3).sum()


    private fun parseInput(input: String): List<Int> {
        return input
            .trim()
            .split("\r\n\r\n")
            .map { it.lines().sumOf(String::toInt) }
            .sortedDescending()
    }
}