package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day09Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day09(resourceAsText("day09_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(13)
        }

        @Test
        fun answer() {
            val answer = Day09(resourceAsText("day09.txt")).solveP1()
            assertThat(answer).isEqualTo(5874)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day09(resourceAsText("day09_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(36)
        }

        @Test
        fun answer() {
            val answer = Day09(resourceAsText("day09.txt")).solveP2()
            assertThat(answer).isEqualTo(209880L)
        }
    }
}