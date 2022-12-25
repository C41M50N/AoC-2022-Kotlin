package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day02Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day02(resourceAsText("day02_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(15)
        }

        @Test
        fun answer() {
            val answer = Day02(resourceAsText("day02.txt")).solveP1()
            assertThat(answer).isEqualTo(11873)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day02(resourceAsText("day02_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(12)
        }

        @Test
        fun answer() {
            val answer = Day02(resourceAsText("day02.txt")).solveP2()
            assertThat(answer).isEqualTo(12014)
        }
    }
}
