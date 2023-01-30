package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day07Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day07(resourceAsText("day07_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(95437L)
        }

        @Test
        fun answer() {
            val answer = Day07(resourceAsText("day07.txt")).solveP1()
            assertThat(answer).isEqualTo(2104783L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day07(resourceAsText("day07_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(24933642L)
        }

        @Test
        fun answer() {
            val answer = Day07(resourceAsText("day07.txt")).solveP2()
            assertThat(answer).isEqualTo(5883165L)
        }
    }
}