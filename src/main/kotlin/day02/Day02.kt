package day02

import common.Day
import util.Point
import kotlin.math.pow


fun main() = Day02().run()


@Suppress("MemberVisibilityCanBePrivate")
class Day02 : Day(2) {
    enum class Instruction {
        U,
        L,
        R,
        D;

        companion object {
            fun parse(char: Char): Instruction {
                return when (char) {
                    'U' -> U
                    'L' -> L
                    'R' -> R
                    'D' -> D
                    else -> {
                        throw Error()
                    }
                }
            }
        }
    }

    data class KeyPad(val char: Char? = null)


    infix fun Point.move(instruction: Instruction): Point {
        return when (instruction) {
            Instruction.U -> this + Point.down()
            Instruction.L -> this + Point.left()
            Instruction.R -> this + Point.right()
            Instruction.D -> this + Point.up()
        }
    }

    fun Instruction.getMoveDir(): Point {
        return when (this) {
            Instruction.U -> Point.down()
            Instruction.L -> Point.left()
            Instruction.R -> Point.right()
            Instruction.D -> Point.up()
        }
    }

    fun Point.clamp(): Point {
        return this.copy(
            x = x.coerceIn(0, 2),
            y = y.coerceIn(0, 2)
        )
    }

    fun Point.mapToKeypad(): Int {
        return y * 3 + x + 1
    }

    val keyPad2 = listOf(
        listOf(
            KeyPad(), KeyPad(), KeyPad('1'), KeyPad(), KeyPad(),
        ),
        listOf(
            KeyPad(), KeyPad('2'), KeyPad('3'), KeyPad('4'), KeyPad(),
        ),
        listOf(
            KeyPad('5'), KeyPad('6'), KeyPad('7'), KeyPad('8'), KeyPad('9'),
        ),
        listOf(
            KeyPad(), KeyPad('A'), KeyPad('B'), KeyPad('C'), KeyPad(),
        ),
        listOf(
            KeyPad(), KeyPad(), KeyPad('D'), KeyPad(), KeyPad(),
        )

    )

    fun isValidMove(move: Point): Boolean {
        if (move.y < 0 || move.y >= keyPad2.size)
            return false
        if (move.x < 0 || move.x >= 5)
            return false

        println(move)

        val char = keyPad2[move.y][move.x].char
        return char != null
    }


    override fun partOne(input: String): Any {
        val instructions = input
            .lines()
            .map { line -> line.map { Instruction.parse(it) } }

        var start = Point.one()

        var result = 0
        var digit = 10f.pow(instructions.count() - 1).toInt()

        for (instructionList in instructions) {
            for (instruction in instructionList) {
                start = (start move instruction).clamp()
            }
            result += start.mapToKeypad() * digit
            digit /= 10

        }
        return result
    }

    override fun partTwo(input: String): Any {
        val instructions = input
            .lines()
            .map { line -> line.map { Instruction.parse(it) } }

        var start = Point(0, 2)

        var keyCode = ""

        for (instructionList in instructions) {
            for (instruction in instructionList) {
                val moveDir = instruction.getMoveDir()
                val move = moveDir + start
                if (isValidMove(move))
                    start = move
            }
            val code = keyPad2[start.y][start.x]
            keyCode += code.char!!

        }
        return keyCode
    }
}