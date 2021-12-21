import java.io.File
import java.util.LinkedList
import java.util.Queue

class UndirectedGraph {
    private val graph = hashMapOf<String, MutableList<String>>()

    private fun addVertex(vertex: String) {
        graph[vertex] = LinkedList<String>()
    }

    fun addEdge(source: String, destination: String) {
        if (!graph.containsKey(source)) addVertex(source)
        if (destination != "end") {
            if (!graph.containsKey(destination)) addVertex(destination)
        }
        graph[source]?.add(destination)
        if (source != "start") {
            graph[destination]?.add(source)
        }
    }

    fun get(key: String): List<String> = graph[key]!!

    fun getTotalPathUsingBFS(source: String, destination: String): Int {
        val queue: Queue<List<String>> = LinkedList()
        val paths: MutableList<List<String>> = mutableListOf()

        get(source).forEach { queue.add(listOf(source, it)) }

        val addInPath: (List<String>, String) -> Unit = { frontElement, vertex ->
            paths.add(mutableListOf<String>().also {
                it.addAll(frontElement)
                it.add(vertex)
            })
        }

        val addInQueue: (List<String>, String) -> Unit = { frontElement, vertex ->
            queue.add(mutableListOf<String>().also {
                it.addAll(frontElement)
                it.add(vertex)
            })
        }

        while (queue.isNotEmpty()) {
            val frontElement = queue.remove()
            get(frontElement.last()).forEach { vertex ->
                if (vertex == destination) {
                    addInPath(frontElement, vertex)
                } else {
                    if (vertex[0].isLowerCase()) {
                        if (!frontElement.contains(vertex)) {
                            addInQueue(frontElement, vertex)
                        } else {
                            frontElement.filter { it[0].isLowerCase() }
                                .let { list ->
                                    val (matching, others) = list.partition { it == vertex }
                                    if (matching.count() == 1 &&
                                        others.groupingBy { it }.eachCount().filter { it.value > 1 }.count() == 0
                                    ) {
                                        addInQueue(frontElement, vertex)
                                    }
                                }
                        }
                    } else {
                        addInQueue(frontElement, vertex)
                    }
                }
            }
        }

        return paths.size
    }
}

val graph = UndirectedGraph()
File("input/2021/day12").forEachLine { line ->
    val (source, destination) = line.split("-").map { it.trim() }
    if (source == "end" || destination == "start") {
        graph.addEdge(destination, source)
    } else {
        graph.addEdge(source, destination)
    }
}

val result = graph.getTotalPathUsingBFS("start", "end")

println(result)