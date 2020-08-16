package ru.job4j.oop.tracker

class StartUI private constructor() {

    companion object {
        private val tracker = Tracker()
        private val console = SimpleConsole()

        fun init() {
            var input = "";
            while (input != "3") {
                console.print("Choose one action (1,2,3):")
                console.print("1) add item")
                console.print("2) show all items")
                console.print("3) exit")
                input = console.readData()
                if (input == "1") addItem()
                if (input == "2") showAllItems()
            }
        }

        private fun addItem() {
            console.print("Enter item's name")
            val name = console.readData()
            val item = Item(name = name)
            tracker.add(item)
        }

        private fun showAllItems() {
            tracker.findAll().forEach { println(it) }
        }
    }
}

fun main() {
    StartUI.init()
}
