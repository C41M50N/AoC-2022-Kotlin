package cfsb.crimsoncoder.advent2022

import cfsb.crimsoncoder.advent2022.Resources.resourceAsText
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
class Day10Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun example() {
            val answer = Day10(resourceAsText("day10_ex.txt")).solveP1()
            assertThat(answer).isEqualTo(13140)
        }

        @Test
        fun answer() {
            val answer = Day10(resourceAsText("day10.txt")).solveP1()
            assertThat(answer).isEqualTo(14620)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun example() {
            val answer = Day10(resourceAsText("day10_ex.txt")).solveP2()
            assertThat(answer).isEqualTo("##..##..##..##..##..##..##..##..##..##..\n" +
                                        "###...###...###...###...###...###...###.\n" +
                                        "####....####....####....####....####....\n" +
                                        "#####.....#####.....#####.....#####.....\n" +
                                        "######......######......######......####\n" +
                                        "#######.......#######.......#######.....\n")
        }

        @Test
        fun answer() {
            val answer = Day10(resourceAsText("day10.txt")).solveP2()
            assertThat(answer).isEqualTo("###....##.####.###..#..#.###..####.#..#.\n" +
                                        "#..#....#.#....#..#.#..#.#..#.#....#..#.\n" +
                                        "###.....#.###..#..#.####.#..#.###..#..#.\n" +
                                        "#..#....#.#....###..#..#.###..#....#..#.\n" +
                                        "#..#.#..#.#....#.#..#..#.#.#..#....#..#.\n" +
                                        "###...##..#....#..#.#..#.#..#.#.....##..\n")
        }
    }
}