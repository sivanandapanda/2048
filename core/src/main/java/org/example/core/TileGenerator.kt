package org.example.core

internal class TileGenerator(
    val probabilityOfFour: Double = 0.1,
) {
    fun generateTile(): Int = if (Math.random() <= probabilityOfFour) 4 else 2
}
