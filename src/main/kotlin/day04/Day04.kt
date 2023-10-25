package day04

import common.Day


fun main() = Day04().run()


class Day04 : Day(4) {

    data class Room(val name: String, val id: Int, val checkSum: List<Char>, val fullName: String) {

        companion object {
            fun parseRoom(line: String): Room {
                val regex = """(.+)(-\d+)(\[[a-z]+])""".toRegex()
                val found = regex.find(line)!!
                val (name, id, checkSum) = found.groupValues.drop(1)

                return Room(
                    name = name.replace("-", ""),
                    id = id.removePrefix("-").toInt(),
                    checkSum = checkSum.removePrefix("[").removeSuffix("]").toCharArray().toList(),
                    fullName = name
                )
            }
        }
    }

    fun isValidRoom(room: Room): Boolean {
        val charCount = mutableMapOf<Char, Int>()

        for (char in room.name) {
            charCount[char] = charCount.getOrDefault(char, 0) + 1
        }
        val ordered = charCount.map { it.key to it.value }.sortedByDescending { it.second }.map { it.first }

        for ((i, c) in room.checkSum.windowed(2, 1).withIndex()) {
            if (!charCount.containsKey(c.first()) || !charCount.containsKey(c.last()))
                return false

            if (charCount[c.first()]!! > charCount[c.last()]!!)
                continue
            if (charCount[c.first()]!! == charCount[c.last()]!!) {
                if (c.first() < c.last())
                    continue
            }

            return false

        }


        return true
    }

    fun decrypt(room: Room): String {
        val count = room.id
        val charOffset = count % 26
        var result = ""
        for (ch in room.fullName){
            if (ch == '-') {
                result += " "
                continue
            }
            var newChar = ch + charOffset
            if (newChar > 'z')
                newChar -= 26
            result += newChar

        }

        return result
    }


    override fun partOne(input: String): Any {
        return input
            .lines()
            .map { Room.parseRoom(it) }
            .filter { isValidRoom(it) }
            .sumOf { it.id }

    }

    override fun partTwo(input: String): Any {
        return  input
            .lines()
            .map { Room.parseRoom(it) }
            .filter { isValidRoom(it) }
            .map { it to decrypt(it) }
            .first { it.second == "northpole object storage" }
            .first.id
    }
}