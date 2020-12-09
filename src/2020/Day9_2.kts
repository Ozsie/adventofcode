import java.io.File

val input = ArrayList<Long>()

File("input/2020/day9").forEachLine {
    input.add(it.toLong())
}

val window = 25

input.forEachIndexed { i, current -> if (i >= window && !check(input.subList(i - window, i), current)) crack(current) }

fun check(subList: List<Long>, current: Long): Boolean {
    val sums = HashSet<Long>()
    subList.forEach { a ->
        subList.forEach { b -> sums.add(a+b) }
    }
    return sums.contains(current)
}

fun crack(magic: Long) = input.subList(0,input.indexOf(magic))
    .apply {
        forEachIndexed { start, _ ->
            var index = start
            var sum = 0L
            while (index < size && sum < magic) {
                sum += get(index)
                if (sum == magic) break else index++
            }
            if (sum == magic) println("${(subList(start, index).min() ?: 0)+(subList(start, index).max() ?: 0)}")
        }
    }
