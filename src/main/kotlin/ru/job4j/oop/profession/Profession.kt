package ru.job4j.oop.profession

open class Profession(var salary: String, private val name: String) {
    open fun action() {
        println("execute some actions")
    }

    open fun getName() {
        println("some name")
    }
}
