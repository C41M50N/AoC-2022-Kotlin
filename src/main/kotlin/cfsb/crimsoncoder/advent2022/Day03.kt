package cfsb.crimsoncoder.advent2022

class Day03(input: String) {
    private val rucksacks = parseInput(input)

    fun solveP1(): Int = rucksacks.sumOf {
        val (a, b) = it.midSplit()
        a.findCommon(b).toPriority()
    }

    fun solveP2(): Int = rucksacks.chunked(3).sumOf {
        it.findCommon().toPriority()
    }


    private fun parseInput(input: String): List<String> {
        return input
            .trim()
            .split("\r\n")
    }
}

private fun String.midSplit(): Pair<String, String> {
    val size = this.length
    val midpoint: Int = size / 2
    return Pair(this.substring(0,midpoint), this.substring(midpoint,size))
}

private fun String.findCommon(other: String): Char {
    return this.toSet().intersect(other.toSet()).first()
}

private fun List<String>.findCommon(): Char {
    var l = this[0].toSet()
    for (s in this.subList(1,this.size)) {
        l = l.intersect(s.toSet())
    }
    return l.first()
}

private fun Char.toPriority(): Int {
    return when (this.code) {
        in 65..90 -> this.code - 38
        in 97..122 -> this.code - 96
        else -> 0
    }
}