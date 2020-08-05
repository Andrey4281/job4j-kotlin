package ru.job4j.base

fun add(first: Double, second: Double): Double {
    return first + second;
}

fun subtract(first: Double, second: Double): Double {
    return first - second;
}

fun multiplication(first: Double, second: Double): Double {
    return first * second;
}

fun division(first: Double, second: Double): Double {
    return first / second;
}


fun main() {
    val plus = add(1.0, 2.0)
    println("1 + 2 = $plus")

    val minus = subtract(5.0, 4.0)
    println("5 - 4 = $minus")

    val multiplication = multiplication(3.0, 4.0)
    println("3 * 4 = $multiplication")

    val division = division(5.0, 4.0)
    println("5 / 4 = $division")
}
