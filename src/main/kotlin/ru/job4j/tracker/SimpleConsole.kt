package ru.job4j.tracker

class SimpleConsole: IConsole {
    override fun print(str: String) {
        println(str)
    }

    override fun readData(): String {
        return readLine().toString()
    }
}
