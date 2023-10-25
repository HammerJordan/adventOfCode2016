import common.Day
import day04.Day04
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class Day04Test {

    val day = Day04()

    @ParameterizedTest
    @CsvSource(
        "aaaaa-bbb-z-y-x-123[abxyz],true",
        "a-b-c-d-e-f-g-h-987[abcde],true",
        "not-a-real-room-404[oarel],true",
        "totally-real-room-200[decoy],false"
    )
    fun isRealRoom(line: String, result: Boolean) {
        val room = Day04.Room.parseRoom(line)
        day.isValidRoom(room) shouldBe result
    }

    @Test
    fun partOne() {
        val data = """aaaaa-bbb-z-y-x-123[abxyz]
            |a-b-c-d-e-f-g-h-987[abcde]
            |not-a-real-room-404[oarel]
            |totally-real-room-200[decoy]
        """.trimMargin()

        day.partOne(data) shouldBe 1514
    }

    @Test
    fun decrypt () {
        val room = Day04.Room("",343, listOf(),"qzmt-zixmtkozy-ivhz")
        day.decrypt(room) shouldBe "very encrypted name"
    }


}