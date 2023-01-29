fun main() {

    fun buildGrid(input: List<String>): Array<IntArray> {
        val trees = Array(input[0].length) { IntArray(input.size) }
        var rowNum = 0
        input.forEach { line ->
            val rowOfTrees = line.toCharArray().map { c -> c.digitToInt() }
            trees[rowNum++] = rowOfTrees.toIntArray()
        }
        return trees
    }

    fun getSurroundingTrees(trees: Array<IntArray>, colPos: Int, rowPos: Int, rows: Int, columns: Int): List<List<Int>> {
        // west, east, north, south outward from perspective of the current position
        return listOf(
            (0..(colPos - 1)).toList().reversed().map { col -> trees[rowPos][col] },
            (colPos + 1..columns - 1).toList().map { col -> trees[rowPos][col] },
            (0..rowPos - 1).toList().reversed().map { row -> trees[row][colPos] },
            (rowPos + 1..rows - 1).toList().map { row -> trees[row][colPos] }
        )
    }

    fun isTreeVisibleFromOutside(trees: Array<IntArray>, rowNum: Int, colNum: Int): Boolean {
        var visible = false
        val tree = trees[rowNum][colNum]
        // west, east, north, south
        val peripheralTrees = getSurroundingTrees(trees, colNum, rowNum, trees.size, trees[rowNum].size)
        if (tree > peripheralTrees[0].max() ||
            tree > peripheralTrees[1].max() ||
            tree > peripheralTrees[2].max() ||
            tree > peripheralTrees[3].max()
        ) visible = true
        return visible
    }

    fun numberOfTreesVisibleFromInside(heightOfCurrentPosition: Int, treeLine: List<Int>): Int {
        val limitingTreePosition = treeLine.indexOfFirst { nextTree -> nextTree >= heightOfCurrentPosition }
        return if (limitingTreePosition == -1) treeLine.size else limitingTreePosition + 1
    }

    fun getScenicScore(trees: Array<IntArray>, rowNum: Int, colNum: Int): Int {
        val tree = trees[rowNum][colNum]
        var score = 1
        // west, east, north, south
        val peripheralTrees = getSurroundingTrees(trees, colNum, rowNum, trees.size, trees[rowNum].size)
        peripheralTrees.forEach { treeLine ->
            score *= numberOfTreesVisibleFromInside(tree, treeLine)
        }
        return score
    }

    fun part1(input: List<String>): Int {
        val trees = buildGrid(input)
        val exteriorTreeCount = (trees.size - 1) * 2 + (trees[0].size - 1) * 2
        var interiorVisibleTreeCount = 0
        for (i1 in 1..trees.size - 2) {
            for (i2 in 1..trees[i1].size - 2) {
                if (isTreeVisibleFromOutside(trees, i1, i2)) interiorVisibleTreeCount++
            }
        }
        return exteriorTreeCount + interiorVisibleTreeCount
    }

    fun part2(input: List<String>): Int {
        val trees = buildGrid(input)
        var maxScenicScore = 1
        val i1 = 1
        val i2 = 1
        for (i1 in 1..trees.size - 2) {
            for (i2 in 1..trees[i1].size - 2) {
                val scenicScore = getScenicScore(trees, i1, i2)
                maxScenicScore = maxOf(maxScenicScore, scenicScore)
            }
        }
        return maxScenicScore
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    println("Part 1 test: ${part1(testInput)}")
    println("Part 2 test: ${part2(testInput)}")


    val input = readInput("Day08")
    println("Part 1: ${part1(input)}")
    println("part 2: ${part2(input)}")
}
