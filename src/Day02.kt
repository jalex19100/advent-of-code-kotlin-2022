enum class HandShape(val description: String, private val defeats: Char, private val ties: Char, private val value: Int) {
    X("Rock", 'C', 'A', 1),
    Y("Paper", 'A', 'B', 2),
    Z("Scissors", 'B', 'C', 3);

    fun play(opponentShape: Char): Int {
        val score = when (opponentShape) {
            this.defeats -> 6
            this.ties -> 3
            else -> 0
        }
        return score + this.value
    }
}

fun main() {
    fun calculateScore(input: List<String>): Int {
        var total = 0
        input.forEach { line ->
            val yourHand = HandShape.values().first { it.name == line.substring(2, 3) }
            val score = yourHand.play(line.toCharArray()[0])
            println("Score: $score")
            total += score
        }
        println("Total: $total")
        return total
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(calculateScore(testInput) == 15)

    val input = readInput("Day02")
    println(calculateScore(input))
}