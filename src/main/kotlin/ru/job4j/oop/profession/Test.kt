package ru.job4j.oop.profession

fun main() {
    val programmer = Programmer(salary = "2000$", language = "java")
    println(programmer.salary)
    println(programmer.language)

    val doctor = Doctor(salary = "$3000$", doctorType = "surgeon")
    println(doctor.salary)
    println(doctor.doctorType)
}
