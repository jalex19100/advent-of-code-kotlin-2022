import com.sun.org.apache.xpath.internal.operations.Bool

class Node(name: String, parent: Node?) {
    val name = name
    val parent = parent
    val children: MutableList<Node> = mutableListOf()
    var size: Int = 0

    fun addChild(child: Node): Boolean {
        val existingChild = getChild(child.name) ?: return children.add(child)
        return false
    }

    fun getChild(name: String): Node? {
        return children.find {
            it.name == name
        }
    }

    fun getRoot(): Node? {
        return parent?.getRoot() ?: this
    }


    fun findDir(criteria: (Int) -> Boolean): List<String> {
        val results: MutableList<String> = mutableListOf()
        if (this.size == 0) {
            val recursiveSize = this.getRecursizeSize()
            if (criteria(recursiveSize)) {
                results.add("${this.name}:$recursiveSize")
                // println("${this.name}: $recursiveSize")
            }
            this.getRecursizeSize()
        } else {
            0
        }
        children.forEach { child ->
            if (child.size == 0) results.addAll(child.findDir(criteria))
        }
        return results
    }


    fun getRecursizeSize(): Int {
        var recursizeSize = size
        children.forEach { child ->
            recursizeSize += child.getRecursizeSize()
        }
        return recursizeSize
    }

    override fun equals(other: Any?): Boolean {
        return (other is Node && other.name == this.name)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        if (this.parent != null) sb.append("${this.parent.name} -> ")
        sb.append("${this.name}\n")
        this.children.forEach { child ->
            sb.append("  ${child.toString()}")
        }
        return sb.toString()
    }
}

fun changeDirectory(currentNode: Node, newDirectory: String): Node {
    return when (newDirectory) {
        "/" -> currentNode.getRoot() ?: currentNode
        ".." -> currentNode?.parent ?: currentNode
        else -> {
            val existingChild = currentNode.getChild(newDirectory)
            if (existingChild == null) {
                val newChild = Node(newDirectory, currentNode)
                currentNode.children.add(newChild)
                newChild
            } else existingChild
        }
    }
}

fun populateTree(input: List<String>): Node {
    val rootNode = Node("/", null)
    var currentNode = rootNode
    input.forEach { line ->
        val splits = line.split(' ')
        if (line.startsWith("$ ")) {
            if (line.startsWith("$ cd")) {
                currentNode = changeDirectory(currentNode, splits[2])
            }
        } else {
            val newChild = Node(splits[1], currentNode)
            if (!line.startsWith("dir ")) newChild.size = splits[0].toInt()
            currentNode.addChild(newChild)
        }
    }
    return rootNode
}

fun main() {
    fun part1(input: List<String>): Int {
        val rootNode = populateTree(input)
        var sum = 0
        rootNode.findDir { x -> x in 1..100000 }.forEach {
            val value = it.split(':')[1].toInt()
            sum += value
        }
        return sum
    }


    fun part2(input: List<String>): Int {
        val rootNode = populateTree(input)
        var result = Int.MAX_VALUE
        rootNode.findDir { x -> x >= 8381165 }.forEach {
            val value = it.split(':')[1].toInt()
            result = result.coerceAtMost(value)
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    println("Part 2: ${part2(testInput)}")
    println("Part 1: ${part1(testInput)}")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
