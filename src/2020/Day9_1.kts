import java.io.File

val input = ArrayList<Long>()
val window = 25

File("input/2020/day9").forEachLine { input.add(it.toLong()) }

input.forEachIndexed { i, current ->
    if (i >= window) {
        if (!check(input.subList(i - window, i), current)) {
            println("$current")
            System.exit(0)
        }
    }
}

fun check(subList: List<Long>, current: Long): Boolean {
    val sums = HashSet<Long>()
    subList.forEach { a ->
        subList.forEach { b -> sums.add(a+b) }
    }
    return sums.contains(current)
}
