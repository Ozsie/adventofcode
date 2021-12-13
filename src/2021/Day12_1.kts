import java.io.File

class Cave(val large: Boolean, val name: String, val connections: HashSet<Cave> = HashSet()) {
    override fun toString(): String {
        return "$name"
    }
}

fun Cave.chain() {
    print("$this -> ")
    connections.filter { it != this }.forEach {
        print("$it, ")
        it.chain()
    }
}

val caves = HashSet<Cave>()
File("input/2021/day12").forEachLine { line ->
    val (a,b) = line.split("-")
    val existingB = caves.firstOrNull { it.name == b } ?: Cave(b.matches(Regex("[A-Z]")), b)
    val existingA = caves.firstOrNull { it.name == a } ?: Cave(a.matches(Regex("[A-Z]")), a)
    existingA.connections.add(existingB)
    existingB.connections.add(existingA)
    caves.add(existingA)
    caves.add(existingB)
}
val start = caves.first { it.name == "start" }
start.chain()