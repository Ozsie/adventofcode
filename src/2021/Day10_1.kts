import java.io.File

val openers = listOf<Char>('(','{','[','<')
val closers = listOf<Char>(')','}',']','>')

class Chunk(val opener: Char, var closer: Char, val parent: Chunk?, val order: Int, val children: ArrayList<Chunk> = ArrayList()) {
    override fun toString(): String {
        var tabs = ""
        for (i in 0..order) tabs = "$tabs  "
        return if (children.isEmpty()) "$tabs$opener$closer"
        else {
            "$tabs$opener\n${children.joinToString { it.toString() }}\n$closer"
        }
    }
}

val lines = ArrayList<Chunk>()
File("input/2021/day10").forEachLine { line ->
    var current: Chunk? = null
    var order = 0
    line.forEach {
        if (openers.contains(it)) {
            val next = Chunk(it, 'x', current, order++)
            if (current != null) current?.children?.add(next) else lines.add(next)
            current = next
        } else if (closers.contains(it)) {
            order--
            current?.closer = it
            current = current?.parent
        }
    }
}
lines.forEach {
    println(it)
}