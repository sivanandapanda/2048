package org.example.model

enum class MoveDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ;

    override fun toString(): String =
        when (this) {
            UP -> "W"
            DOWN -> "S"
            LEFT -> "A"
            RIGHT -> "D"
        }
}
