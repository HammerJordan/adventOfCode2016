package day01

import common.Day
import util.Point


fun main() = Day01().run()

class Day01 : Day(1){

    enum class Direction(val point: Point) {
        North(Point.up()),
        West(Point.left()),
        East(Point.right()),
        South(Point.down());

        fun turnLeft(): Direction {
            return when (this) {
                North -> West
                West -> South
                East -> North
                South -> East
            }
        }

        fun turnRight(): Direction {
            return when (this) {
                North -> East
                West -> North
                East -> South
                South -> West
            }
        }
    }

    enum class Turn {
        R,
        L
    }


    data class Instruction(val turn: Turn, val distance: Int) {
        companion object {
            fun parse(input: String): List<Instruction> {
                return input
                    .split(", ")
                    .map {
                        val turn = when (it[0]) {
                            'R' -> Turn.R
                            'L' -> Turn.L
                            else -> throw Error()
                        }
                        val distance = it.substring(1).trim().toInt()
                        Instruction(turn, distance)
                    }

            }
        }
    }

    override fun partOne(input: String): Any {
        val instructions = Instruction.parse(input)
        var start = Point()
        var facing = Direction.North



        for (instruction in instructions) {
            facing = when (instruction.turn) {
                Turn.R -> facing.turnRight()
                Turn.L -> facing.turnLeft()
            }
            val move = facing.point * instruction.distance
            start += move
        }

        return start.distanceInBlocks(Point())
    }

    override fun partTwo(input: String): Any {
        //val instructions = Instruction.parse("R8, R4, R4, R8")
        val instructions = Instruction.parse(input)
        var start = Point()
        var facing = Direction.North
        val visited = mutableSetOf(start)


        for (instruction in instructions) {
            facing = when (instruction.turn) {
                Turn.R -> facing.turnRight()
                Turn.L -> facing.turnLeft()
            }
            var found = false

            for (i in 1..instruction.distance) {
                start += facing.point
                if (!visited.add(start)) {
                    found = true
                    break
                }
            }
            if (found)
                break

        }

        return start.distanceInBlocks(Point())
    }
}