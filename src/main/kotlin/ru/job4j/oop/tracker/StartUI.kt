package ru.job4j.oop.tracker

class StartUI private constructor() {

    companion object {
        private val tracker = Tracker()
        private val console = SimpleConsole()
        private val dispatcher: Map<String, IAction>

        init {
            val addAction: IAction = object : IAction {
                override fun execute(tracker: ITracker, console: IConsole): Boolean {
                    tracker.add(Item(name = console.ask("Enter name")))
                    return true
                }
            }

            var showAction: IAction = object : IAction {
                override fun execute(tracker: ITracker, console: IConsole): Boolean {
                    tracker.findAll().forEach { console.print(it.toString()) }
                    return true
                }
            }

            var exitAction: IAction = object : IAction {
                override fun execute(tracker: ITracker, console: IConsole): Boolean {
                    return false
                }
            }

            dispatcher = mapOf("1" to addAction, "2" to showAction, "3" to exitAction)
        }


        fun init() {
            var isAction = true
            while (isAction) {
                showMenu()
                val input = console.readData()
                val action = dispatcher[input]
                if (action == null) {
                    isAction = false
                    continue
                }
                isAction = action.execute(tracker, console)
            }
        }

        private fun showMenu() {
            console.print("Choose one action (1,2,3):")
            console.print("1) add item")
            console.print("2) show all items")
            console.print("3) exit")
        }
    }
}

fun main() {
    StartUI.init()
}
