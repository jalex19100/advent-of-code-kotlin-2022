import kotlin.math.abs

fun main() {
    class Coordinate(val x: Int, val y: Int) {
        fun moveRight(): Coordinate = Coordinate(x+1, y)

        fun moveLeft(): Coordinate = Coordinate(x-1, y)

        fun moveUp(): Coordinate = Coordinate(x, y+1)

        fun moveDown(): Coordinate = Coordinate(x, y-1)

    }

    class Rope(var headPosition: Coordinate, var tailPosition: Coordinate) {
        fun move(direction: Char, numberOfSteps: Int): Coordinate {
            var newHeadPosition = headPosition
            repeat(numberOfSteps) {
                when (direction) {
                    'R' -> newHeadPosition = newHeadPosition.moveRight()
                    'L' -> newHeadPosition = newHeadPosition.moveLeft()
                    'U' -> newHeadPosition = newHeadPosition.moveUp()
                    'D' -> newHeadPosition = newHeadPosition.moveDown()
                }
                tailPosition = moveTail(newHeadPosition, tailPosition)
            }
        }

        fun moveTail(headPosition: Coordinate, tailPosition: Coordinate):Coordinate {
            val xDiff = headPosition.x - tailPosition.y
            val yDiff = headPosition.y - tailPosition.y

            if (abs(xDiff) > 1 || abs(yDiff) > 1) {
                when(tailPosition.compareTo(headPosition)) {
                    -1 ->
                }
            }
        }
    }


    fun part1(input: List<String>): Int {
        val rope = Rope
        input.forEach { line ->
            val (direction, count) = line.split(' ')
            rope.mo
        }
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    // check(part1(testInput) == 1)
    println(part1(testInput))

    val input = readInput("Day09")
    // println(part1(input))
    // println(part2(input))
}
