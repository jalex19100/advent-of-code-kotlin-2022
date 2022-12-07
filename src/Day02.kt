// X > C, Z > B, Y > A, X=A, Y=B, Z=C
enum class HandShape(
    val desc: String, private val defeats: Char, private val ties: Char, private val shapeValue: Int
) {
    X("Rock", 'C', 'A', 1),
    Y("Paper", 'A', 'B', 2),
    Z("Scissors", 'B', 'C', 3);

    fun play(opponentShape: Char): Int {
        val score = when (opponentShape) {
            this.defeats -> 6
            this.ties -> 3
            else -> 0
        }
        return score + this.shapeValue
    }
}

/**
## A (rock)
 * 	X (lose:0, scissors:3 = 3)
 * 	Y (draw:3, rock:1 = 4)
 * 	Z (win:6, paper:2 = 8)
## B (paper)
 * 	X (lose:0, rock:1 = 1)
 * 	Y (draw:3, paper:2 = 5)
 * 	Z (win:6,  scissors:3 = 9)
## C (scissors)
 *   X (lose:0, paper:2 = 2)
 * 	Y (draw:3, scissors:3 = 6)
 * 	Z (win:6, rock:1 = 7)
 */
enum class HandShapeSuggestion(val desc: String, val X: Int, val Y: Int, val Z: Int) {
    A("Rock", 3, 4, 8),
    B("Paper", 1, 5, 9),
    C("Scissors", 2, 6, 7);

    fun play(desiredResult: Char): Int {
        val score = when (desiredResult) {
            'X' -> this.X
            'Y' -> this.Y
            'Z' -> this.Z
            else -> 0
        }
        return score
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        input.forEach { line ->
            val yourShape = HandShape.values().first { it.name == line.substring(2, 3) }
            val score = yourShape.play(line.toCharArray()[0])
            total += score
        }
        return total
    }

    // TODO: combine and pass in unique logic via function argument
    fun part2(input: List<String>): Int {
        var total = 0
        input.forEach { line ->
            val opponentsShape = HandShapeSuggestion.values().first { it.name == line.substring(0, 1) }
            val score = opponentsShape.play(line.toCharArray()[2])
            total += score
        }
        return total
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}