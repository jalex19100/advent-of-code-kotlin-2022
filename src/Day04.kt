fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        input.forEach { line ->
            val pairs = line.split(',', '-')
            val pair1 = (pairs[0].toInt()..pairs[1].toInt()).toSet()
            val pair2 = (pairs[2].toInt()..pairs[3].toInt()).toSet()
            if (pair1.isNotEmpty() && pair2.isNotEmpty() && pair1.containsAll(pair2) || pair2.containsAll(pair1)) count++
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        input.forEach { line ->
            val pairs = line.split(',', '-')
            val pair1 = (pairs[0].toInt()..pairs[1].toInt()).toSet()
            val pair2 = (pairs[2].toInt()..pairs[3].toInt()).toSet()
            if (pair1.isNotEmpty() && pair2.isNotEmpty() && pair1.intersect(pair2).isNotEmpty()) count++
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}