package cfsb.crimsoncoder.advent2022

class Day06(input: String) {
    private val data = parseInput(input)

    fun solveP1(): Int {
        for ((idx, window) in data.windowed(4).withIndex()) {
            if (window.isUnique()) return idx + 4
        }
        return -1
    }

    fun solveP2(): Int {
        for ((idx, window) in data.windowed(14).withIndex()) {
            if (window.isUnique()) return idx + 14
        }
        return -1
    }


    private fun parseInput(input: String): String {
        return input.trim()
    }
}

private fun String.isUnique(): Boolean {
    for (c in this) {
        if (this.count { it == c } > 1) return false
    }
    return true
}