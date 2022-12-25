package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
class Day03Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day03(resourceAsText("day03_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(157)
        }

        @Test
        fun answer() {
            val answer = Day03(resourceAsText("day03.txt")).solveP1()
            assertThat(answer).isEqualTo(8139)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day03(resourceAsText("day03_ex.txt")).solveP2()
            assertThat(answer).isEqualTo(70)
        }

        @Test
        fun answer() {
            val answer = Day03(resourceAsText("day03.txt")).solveP2()
            assertThat(answer).isEqualTo(2668)
        }
    }
}
