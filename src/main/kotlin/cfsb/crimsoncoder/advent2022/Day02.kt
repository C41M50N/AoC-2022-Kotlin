package cfsb.crimsoncoder.advent2022

class Day02(input: String) {
    private val matches = parseInput(input)

    fun solveP1(): Int = matches.map {
        val opponentMove: Move = it[0].toString().toMove()
        val playerMove: Move = it[2].toString().toMove()

        playerMove.calculateScore(opponentMove)
    }.sum()

    fun solveP2(): Int = matches.map {
        val opponentMove: Move = it[0].toString().toMove()
        val matchOutcome: Outcome = it[2].toString().toOutcome()

        opponentMove.calculateScore(matchOutcome)
    }.sum()

    private fun parseInput(input: String): List<String> {
        return input
            .trim()
            .split("\r\n")
    }
}

enum class Move {
    ROCK, PAPER, SCISSORS
}
private fun String.toMove(): Move {
    return when (this) {
        "A", "X" -> Move.ROCK
        "B", "Y" -> Move.PAPER
        "C", "Z" -> Move.SCISSORS
        else -> Move.ROCK
    }
}

private fun Move.calculateScore(other: Move): Int {
    // DRAWS
    if (this == Move.ROCK && other == Move.ROCK) return 1 + 3
    if (this == Move.PAPER && other == Move.PAPER) return 2 + 3
    if (this == Move.SCISSORS && other == Move.SCISSORS) return 3 + 3

    if (this == Move.ROCK) {
        if (other == Move.PAPER) return 1 + 0
        if (other == Move.SCISSORS) return 1 + 6
    }

    if (this == Move.PAPER) {
        if (other == Move.ROCK) return 2 + 6
        if (other == Move.SCISSORS) return 2 + 0
    }

    if (this == Move.SCISSORS) {
        if (other == Move.ROCK) return 3 + 0
        if (other == Move.PAPER) return 3 + 6
    }

    return 0
}

enum class Outcome {
    LOSE, DRAW, WIN
}
private fun String.toOutcome(): Outcome {
    return when (this) {
        "X" -> Outcome.LOSE
        "Y" -> Outcome.DRAW
        "Z" -> Outcome.WIN
        else -> Outcome.DRAW
    }
}
private fun Move.calculateScore(outcome: Outcome): Int {
    if (outcome == Outcome.DRAW) {
        return this.calculateScore(this)
    }

    if (outcome == Outcome.LOSE) {
        if (this == Move.ROCK) return Move.SCISSORS.calculateScore(this)
        if (this == Move.PAPER) return Move.ROCK.calculateScore(this)
        if (this == Move.SCISSORS) return Move.PAPER.calculateScore(this)
    }

    if (outcome == Outcome.WIN) {
        if (this == Move.ROCK) return Move.PAPER.calculateScore(this)
        if (this == Move.PAPER) return Move.SCISSORS.calculateScore(this)
        if (this == Move.SCISSORS) return Move.ROCK.calculateScore(this)
    }

    return 0
}