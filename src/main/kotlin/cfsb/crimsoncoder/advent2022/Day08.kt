package cfsb.crimsoncoder.advent2022

class Day08(input: String) {
    private val data = parseInput(input)

    fun solveP1(): Int = TreeHeightMap(data).getNumVisibleTrees()

    fun solveP2(): Long = TreeHeightMap(data).getMaxScenicScore()


    private fun parseInput(input: String): List<List<Int>> {
        return input
            .trim()
            .split("\r\n")
            .map { it.split("").filterNot { it == "" }.map { it.toInt() } }
    }
}

class TreeHeightMap(val heightMap: List<List<Int>>) {
    val height: Int = this.heightMap.size
    val width: Int = this.heightMap[0].size

    fun getNumVisibleTrees (): Int {
        var count: Int =  (2 * (this.height + this.width)) - 4

        for (y in 1..height-2) {
            for (x in 1..width-2) {
                val treeHeight = heightMap[y][x]

                // top
                val isTopValid = heightMap.subList(0, y).map { it[x] }.all { treeHeight > it }
                // bottom
                val isBottomValid = heightMap.subList(y+1, height).map { it[x] }.all { treeHeight > it }
                // left
                val isLeftValid = heightMap[y].subList(0, x).all { treeHeight > it }
                // right
                val isRightValid = heightMap[y].subList(x+1, width).all { treeHeight > it }

                 if (isTopValid || isBottomValid || isLeftValid || isRightValid) count++
            }
        }

        return count
    }

    fun getMaxScenicScore (): Long {
        val scenicScores = mutableListOf<Long>()

        for (y in 1..height-2) {
            for (x in 1..width - 2) {
                val treeHeight = heightMap[y][x]

                if (x == 2 && y == 3) {
                    println("Top Trees: ${heightMap.subList(0, y).map { it[x] }.reversed()}")
                    println("Count: ${heightMap.subList(0, y).map { it[x] }.reversed().orderedTreeCount(treeHeight)}")
                    println("Bottom Trees: ${heightMap.subList(y+1, height).map { it[x] }}")
                    println("Count: ${heightMap.subList(y+1, height).map { it[x] }.orderedTreeCount(treeHeight)}")
                    println("Left Trees: ${heightMap[y].subList(0, x).reversed()}")
                    println("Count: ${heightMap[y].subList(0, x).reversed().orderedTreeCount(treeHeight)}")
                    println("Right Trees: ${heightMap[y].subList(x+1, width)}")
                    println("Count: ${heightMap[y].subList(x+1, width).orderedTreeCount(treeHeight)}")
                }

                // top
                val topVisibleTrees = heightMap.subList(0, y).map { it[x] }.reversed().orderedTreeCount(treeHeight)
                // bottom
                val bottomVisibleTrees = heightMap.subList(y+1, height).map { it[x] }.orderedTreeCount(treeHeight)
                // left
                val leftVisibleTrees = heightMap[y].subList(0, x).reversed().orderedTreeCount(treeHeight)
                // right
                val rightVisibleTrees = heightMap[y].subList(x+1, width).orderedTreeCount(treeHeight)

                scenicScores.add(topVisibleTrees * bottomVisibleTrees * leftVisibleTrees * rightVisibleTrees * 1L)
            }
        }

        return scenicScores.max()
    }
}

fun List<Int>.orderedTreeCount(treeHeight: Int): Int {
    // get index of first element that does not meet requirement
    var idx = 0
    for (n in this) {
        if (n >= treeHeight) {
            idx++
            break
        }
        idx++
    }
    return idx
}
