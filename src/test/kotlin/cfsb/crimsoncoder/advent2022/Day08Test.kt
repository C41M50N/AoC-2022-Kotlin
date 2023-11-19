package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 8")
class Day08Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day08(resourceAsText("day08_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(21)
        }

        @Test
        fun answer() {
            val answer = Day08(resourceAsText("day08.txt")).solveP1()
            assertThat(answer).isEqualTo(1801)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day08(resourceAsText("day08_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(8L)
        }

        @Test
        fun answer() {
            val answer = Day08(resourceAsText("day08.txt")).solveP2()
            assertThat(answer).isEqualTo(209880L)
        }
    }
}