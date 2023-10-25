package common

import java.io.File
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

abstract class Day(private val day: Int) {
    private val dayString get() = day.toString().padStart(2, '0')
    private val path: String get() = "src/main/kotlin/day$dayString/input.txt"
    abstract fun partOne(input: String): Any
    abstract fun partTwo(input: String): Any

    fun run() {
        val data = File(path).readText()
        println("Part 1:")
        try {
            var result: Any
            val time = measureTime {
                result = partOne(data)
            }

            println(result)
            println("Took: $time")

        } catch (e: Error) {
            println(e)
        }
        println("-------------------")
        println("Part 2:")
        try {
            var result: Any
            val time = measureTime {
                result = partTwo(data)
            }
            println(result)
            println("Took: $time")
        } catch (e: Error) {
            println(e)
        }

    }
}