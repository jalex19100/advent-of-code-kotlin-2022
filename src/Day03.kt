fun main() {
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray() // index + 1

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val firstHalf = line.substring(0, line.length / 2).toCharArray()
            val secondHalf = line.substring(line.length / 2).toCharArray()
            val commonElementSet = firstHalf.toSet().intersect(secondHalf.toSet())
            val commonElementPriority = letters.indexOf(commonElementSet.first()) + 1
            sum += commonElementPriority
        }
        return sum
    }

    // TODO: AIIYYEEE more redundancy - cleanup
    fun part2(input: List<String>): Int {
        var sum = 0
        input.chunked(3).forEach { oneChunk ->
            var commonElementSet = oneChunk.first().toSet()
            oneChunk.forEach { line ->
                commonElementSet = commonElementSet.intersect(line.toSet())
            }
            val commonElementPriority = letters.indexOf(commonElementSet.first()) + 1
            sum += commonElementPriority
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}