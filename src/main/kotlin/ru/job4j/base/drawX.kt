package ru.job4j.base

fun draw(size: Int) {
    if (size < 0 || size %2 == 0) {
        println("You should give positive and odd number")
        return
    }
    for (i in 0 until size) {
        for (j in 0 until size) {
            if (j == i || j == (size - 1 - i)) print('X') else print(' ')
        }
        println()
    }
}

fun main() {
    draw(5)
}
