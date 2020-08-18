package ru.job4j.oop.profession

class Doctor(salary: String, name: String = "Doctor", var doctorType: String): Profession(salary, name) {
    override fun action() {
        println("action from doctor")
    }

    override fun getName() {
        println("my name is doctor")
    }
}

