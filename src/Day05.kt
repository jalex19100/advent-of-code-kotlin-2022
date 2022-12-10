import java.util.*
import kotlin.collections.ArrayList

class StacksOfCrates() {
    private val stacks = HashMap<Int, LinkedList<Char>>()

    fun add(stackNumber: Int, crate: Char) {
        if (stacks.containsKey(stackNumber)) stacks?.get(stackNumber)?.add(crate)
        else {
            val stack = LinkedList<Char>()
            stack.add(crate)
            stacks[stackNumber] = stack
        }
    }

    fun move(numberOfCrates: Int?, fromStackKey: Int?, toStackKey: Int?, multiMove: Boolean) {
        if (numberOfCrates != null && fromStackKey != null && toStackKey != null) {
            val fromStack = stacks[fromStackKey]
            val toStack = stacks[toStackKey]
            val crates = Stack<Char>()
            if (multiMove) {
                repeat(numberOfCrates) {
                    crates.push(fromStack?.pop())
                }
                repeat(numberOfCrates) {
                    toStack?.push(crates?.pop())
                }
            } else {
                repeat(numberOfCrates) {
                    toStack?.push(fromStack?.pop())
                }
            }
        } else println("Invalid instruction: move $numberOfCrates from $fromStackKey to $toStackKey")
    }

    fun getTopCrates(): List<Char> {
        val topCrates = ArrayList<Char>()
        stacks.keys.sorted().forEach { key ->
            val crate = stacks[key]?.peek()
            if (crate != null) {
                topCrates.add(crate)
            }
        }
        return topCrates
    }

    override fun toString(): String {
        var string = StringBuilder()
        string.append("${this.stacks.size} stacks\n")
        stacks.keys.forEach { index ->
            string.append("Stack $index: ${stacks[index]}\n")
        }
        return string.toString()
    }
}

fun main() {

    fun part1(input: List<String>, multiMove: Boolean = false): String {
        val stacks = StacksOfCrates()
        val cratesPattern = ".*\\[[A-Z]\\].*".toRegex()
        val instructionPattern = "move\\s(\\d+)\\sfrom\\s(\\d+)\\sto\\s(\\d+)".toRegex()
        input.forEach { line ->
            if (line.matches(cratesPattern)) {
                val chunks = line.chunked(4)
                chunks.forEachIndexed { index, chunk ->
                    if (chunk.isNotBlank() && chunk[0] == '[' && chunk[1].isLetter()) {
                        stacks.add(index + 1, chunk[1])
                    }
                }
            } else if (line.matches(instructionPattern)) {
                val matchGroups = instructionPattern.find(line)?.groups
                if (matchGroups != null && matchGroups.size == 4) {
                    stacks.move(matchGroups[1]?.value?.toInt(), matchGroups[2]?.value?.toInt(), matchGroups[3]?.value?.toInt(), multiMove)

                }
            }
        }
        return stacks.getTopCrates().toString()
    }

    fun part2(input: List<String>): String {
        return part1(input, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "[C, M, Z]")
    check(part2(testInput) == "[M, C, D]")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
