import java.io.File

File("input/2020/day1").apply {
    forEachLine { a ->
        forEachLine { b ->
            if (a.toInt() + b.toInt() == 2020) {
                println(a.toInt()*b.toInt())
                System.exit(0)
            }
        }
    }
}

