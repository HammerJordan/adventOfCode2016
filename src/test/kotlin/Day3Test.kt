import day03.Day3
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day3Test {
    val day = Day3()

    @Test
    fun isValidTriangle() {
        val tri = "5 10 25"
        val triangle = day.parseTriangle(tri)

        day.isValidTriangle(triangle) shouldBe false
    }

    @Test
    fun splitByColumn() {
        val testData = """101 301 501
102 302 502
103 303 503
201 401 601
202 402 602
203 403 603"""

        val result = day.splitByColumn(testData)
        result[0] shouldBe listOf(101, 102, 103)
        result[1] shouldBe listOf(301, 302, 303)
        result[2] shouldBe listOf(501, 502, 503)
        result[3] shouldBe listOf(201, 202, 203)

    }


}