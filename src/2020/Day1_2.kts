import java.io.File

File("input/2020/day1").apply {
    forEachLine { a ->
        forEachLine { b ->
            forEachLine { c ->
                if (a.toInt() + b.toInt() + c.toInt() == 2020) {
                    println(a.toInt()*b.toInt()*c.toInt())
                    System.exit(0)
                }
            }
        }
    }
}

