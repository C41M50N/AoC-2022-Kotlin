package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day06Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day06(resourceAsText("day06_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(10)
        }

        @Test
        fun answer() {
            val answer = Day06(resourceAsText("day06.txt")).solveP1()
            assertThat(answer).isEqualTo(1034)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day06(resourceAsText("day06_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(29)
        }

        @Test
        fun answer() {
            val answer = Day06(resourceAsText("day06.txt")).solveP2()
            assertThat(answer).isEqualTo(2472)
        }
    }
}
