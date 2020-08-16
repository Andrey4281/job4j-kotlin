package ru.job4j.oop.tracker

class SimpleConsole: IConsole {
    override fun ask(question: String): String {
        this.print(question)
        return readData()
    }

    override fun print(str: String) {
        println(str)
    }

    override fun readData(): String {
        return readLine().toString()
    }
}
