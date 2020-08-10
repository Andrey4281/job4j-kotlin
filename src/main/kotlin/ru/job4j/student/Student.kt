package ru.job4j.student

class Student(var name: String = "",
              var surname: String = "",
              var phone: String = "",
              var email: String ="") {

    fun show() {
        println("$name $surname $phone $email")
    }
}

fun main() {
    val studentFirst = Student(name = "Andrey", surname = "Semenov")
    studentFirst.show()
    val studentTwo = Student(surname = "Semenov")
    studentTwo.show()
    val studentThird = Student(phone="999")
    studentThird.show()
    val studentFourth = Student("email@mail.ru")
    studentFourth.show()
}
