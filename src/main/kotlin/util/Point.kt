package util

import kotlin.math.absoluteValue

data class Point(val x: Int = 0, val y: Int = 0) {

    operator fun times(rhs: Int) = this.copy(x = x * rhs, y = y * rhs)
    operator fun plus(rhs: Point) = this.copy(x = x + rhs.x, y = y + rhs.y)

    fun distanceInBlocks(rhs: Point) = (this.x - rhs.x).absoluteValue + (this.y - rhs.y).absoluteValue

    companion object {
        fun up() = Point(0, 1)
        fun down() = Point(0, -1)
        fun left() = Point(-1, 0)
        fun right() = Point(1, 0)
        fun one() = Point(1, 1)
    }


}
