package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day05Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day05(resourceAsText("day05_ex.txt")).solveP1()
            assertThat(answer).isEqualTo("CMZ")
        }

        @Test
        fun answer() {
            val answer = Day05(resourceAsText("day05.txt")).solveP1()
            assertThat(answer).isEqualTo("TLFGBZHCN")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day05(resourceAsText("day05_ex.txt")).solveP2()
            assertThat(answer).isEqualTo("MCD")
        }

        @Test
        fun answer() {
            val answer = Day05(resourceAsText("day05.txt")).solveP2()
            assertThat(answer).isEqualTo("QRQFHFWCL")
        }
    }
}
