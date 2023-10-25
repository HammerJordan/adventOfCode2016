import day02.Day02
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day02Test{
    val testInput = """ULL
RRDDD
LURDL
UUUUD"""
    @Test
    fun partOne(){
        Day02().partOne(testInput) shouldBe 1985
    }

    @Test
    fun partTwo(){
        Day02().partTwo(testInput) shouldBe "5DB3"
    }
}