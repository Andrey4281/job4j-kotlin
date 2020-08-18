package ru.job4j.oop.profession

class Programmer(salary: String, name: String = "Programmer", val language: String): Profession(salary, name) {
    override fun action() {
        println("action from programmer")
    }

    override fun getName() {
        println("my name is programmer")
    }
}
