package ru.job4j.collection

fun sum(list: List<Int>): Int {
    return list
        .stream()
        .filter{ it % 2 == 0 }
        .map{ it + 1 }
        .reduce{sum, it -> sum + it}.get()
}


fun main() {
    val list = listOf(1, 2, 4)
    println(sum(list))
}
