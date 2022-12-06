fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var sum = 0
        input.forEach { it ->
            if (it.isNotBlank()) {
                sum += it.toInt()
            } else {
                if (sum > max) {
                    max = sum
                }
                sum = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        val sums = mutableListOf<Int>()
        var sum = 0
        input.forEach { it ->
            if (it.isNotBlank()) {
                sum += it.toInt()
            } else {
                sums.add(sum)
                sum = 0
            }
        }
        sums.add(sum)
        sums.sort()
        println ("Sums: ${sums[sums.size-1] + sums[sums.size-2] + sums[sums.size-3]}")

        return sums[sums.size-1] + sums[sums.size-2] + sums[sums.size-3]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}