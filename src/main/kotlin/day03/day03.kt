package day03

import common.Day


fun main() = Day3().run()


@Suppress("MemberVisibilityCanBePrivate")
class Day3 : Day(3) {

    fun parseTriangle(line: String): List<Int> {
        val regex = """(\d+)\s*(\d+)\s*(\d+)""".toRegex()
        val result = regex.find(line)!!


        val (a, b, c) = result.groupValues.drop(1).map { it.toInt() }.toList()
        return listOf(a, b, c)
    }

    fun isValidTriangle(triangle: List<Int>): Boolean {
        val ordered = triangle.sortedDescending()

        val (a, b, c) = ordered

        return b + c > a
    }

    fun splitByColumn(input: String): List<List<Int>> {
        val regex = """(\d+)\s*(\d+)\s*(\d+)""".toRegex()

        val columnOne = mutableListOf<Int>()
        val columnTwo = mutableListOf<Int>()
        val columnThree = mutableListOf<Int>()
        val result = mutableListOf<List<Int>>()

        val lines = input.lines()

        for (i in lines.indices) {
            val regexMatch = regex.find(lines[i])!!

            val (a, b, c) = regexMatch.groupValues.drop(1).map { it.toInt() }.toList()
            columnOne.add(a)
            columnTwo.add(b)
            columnThree.add(c)

            if (columnOne.count() >= 3) {
                result.add(columnOne.toList())
                result.add(columnTwo.toList())
                result.add(columnThree.toList())

                columnOne.clear()
                columnTwo.clear()
                columnThree.clear()
            }
        }

        return result
    }

    override fun partOne(input: String): Any {
        return input
            .lines()
            .map { parseTriangle(it.trim()) }
            .count { isValidTriangle(it) }

    }

    override fun partTwo(input: String): Any {
        return splitByColumn(input)
            .count { isValidTriangle(it) }
    }
}