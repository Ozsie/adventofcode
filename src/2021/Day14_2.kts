import java.io.File

val inputs = File("input/2021/day14").readLines()

val polymerTemplate = inputs[0]
val pairInsertionMap = hashMapOf<String, String>()
for (index in 2 until inputs.size) {
    val (first, second) = inputs[index].split("->")
    pairInsertionMap[first.trim()] = second.trim()
}

val polymerMap = mutableMapOf<String, Long>()
for (index in 0 until polymerTemplate.length - 1) {
    val pair = polymerTemplate.slice(index..index + 1)
    polymerMap[pair] = polymerMap.getOrDefault(pair, 0) + 1
}

val steps = 40
repeat(steps) {
    val newPolymerMap = mutableMapOf<String, Long>()
    for (polymer in polymerMap.keys) {
        val itemToInsert = pairInsertionMap[polymer]
        val (first, second) = polymer.mapIndexed { index, c ->
            if (index == 0) "$c$itemToInsert"
            else "$itemToInsert$c"
        }
        newPolymerMap[first] = newPolymerMap.getOrDefault(first, 0) + polymerMap.getOrDefault(polymer,0)
        newPolymerMap[second] = newPolymerMap.getOrDefault(second, 0) + polymerMap.getOrDefault(polymer,0)
    }
    polymerMap.clear()
    polymerMap.putAll(newPolymerMap)
}

val mapOfCharAndCount = mutableMapOf<Char, Long>()
polymerMap.forEach { (key, value) -> mapOfCharAndCount[key[1]] = mapOfCharAndCount.getOrDefault(key[1], 0) + value }
mapOfCharAndCount[polymerMap.keys.first()[0]] = mapOfCharAndCount.getOrDefault(polymerMap.keys.first()[0], 0) + polymerMap.values.first()

val result = mapOfCharAndCount.values.maxOf { it } - mapOfCharAndCount.values.minOf { it }
println(result)