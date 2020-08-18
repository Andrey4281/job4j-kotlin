package ru.job4j.collection

fun main() {
    val dec = {x: Int -> x - 1}
    val square = {x: Double -> x * x}

    println(dec(2))
    println(square(4.0))
}
