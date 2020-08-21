package ru.job4j.collection

class EmailService {
    fun emailTo(message: Message): String {
        return with(StringBuilder()) {
            append("Subject : ")
            append(message.email)
            append("Body : ")
            append("Hello, ")
            append(message.email)
            append(" ")
            append("You win!")
        }.toString()
    }
}
