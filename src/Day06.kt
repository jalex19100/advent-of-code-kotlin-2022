fun main() {
    fun allUnique(string: String): Boolean {
        val setOfChars = string.toSet().distinct()
        // println("$string <=> $setOfChars: ${string.length == setOfChars.size}")
        return (string.length == setOfChars.size)
    }

    fun part1(input: String, markerSize: Int = 4): Int {
        var index = markerSize
        while (index < input.length) {
            if (allUnique(input.substring(index - markerSize, index))) return index
            index++
        }
        return -1
    }

    fun part2(input: String): Int {
        return part1(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    testInput.forEach { line ->
        println("$line ${part1(line)}")
        println("$line ${part2(line)}")
    }

    val input = readInput("Day06")
    input.forEach { line ->
        println("$input ${part1(line)}")
        println("$input ${part2(line)}")
    }
}
