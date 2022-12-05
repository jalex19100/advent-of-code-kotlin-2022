fun main() {
    fun maxOfSums(input: List<String>): Int {
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
        return max;
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(maxOfSums(testInput) == 24000)

    val input = readInput("Day01")
    println(maxOfSums(input))
}