fun main() {
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray() // index + 1

    fun sumPriorities(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val firstHalf = line.substring(0, line.length / 2).toCharArray()
            val secondHalf = line.substring(line.length / 2).toCharArray()
            val commonElementSet = firstHalf.toSet().intersect(secondHalf.toSet())
            val commonElementPriority = letters.indexOf(commonElementSet.first()) + 1
            println("Common Element: ${commonElementSet.first()} with priority $commonElementPriority")
            sum += commonElementPriority
        }
        println("Sum: $sum")
        return sum
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(sumPriorities(testInput) == 157)

    val input = readInput("Day03")
    println(sumPriorities(input))
}