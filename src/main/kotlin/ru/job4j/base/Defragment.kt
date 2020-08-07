package ru.job4j.base

import java.util.*

fun defragment(array: Array<String?>) {
    var borderEmptyEl = -1
    for ((index, element) in array.withIndex()) {
        if (element != null && borderEmptyEl != -1) {
            swap(borderEmptyEl, index, array)
            borderEmptyEl++
        } else if (element == null && borderEmptyEl == -1) {
            borderEmptyEl = index
        }
    }
}

fun swap(indexFirst: Int, indexSecond: Int, array: Array<String?>) {
    val temp = array[indexFirst]
    array[indexFirst] = array[indexSecond]
    array[indexSecond] = temp
}

fun main() {
    val array = arrayOfNulls<String>(5)
    array[0] = "Petr"
    array[1] = "Ga"
    array[2] = "Andrey"
    array[3] = "Olga"
    array[4] = "Boris"
    defragment(array)
    println(Arrays.toString(array))
}
