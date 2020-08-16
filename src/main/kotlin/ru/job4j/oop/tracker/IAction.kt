package ru.job4j.oop.tracker

interface IAction {
    fun execute(tracker: ITracker, console: IConsole): Boolean
}
