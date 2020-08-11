package ru.job4j.tracker

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec


internal class TrackerTest: StringSpec({
    val tracker = Tracker()

    "When add new item then should get it" {
        val item = Item(1, "Task1")
        tracker.add(item)
        tracker.findById(1) shouldBe item
        tracker.findByName("Task1")[0] shouldBe item
        tracker.findAll()[0] shouldBe item
    }

    "When delete item then should not get it" {
        shouldThrow<NoSuchElementException> {
            val item = Item(2, "Task2")
            tracker.add(item)
            tracker.delete(2)
            tracker.findById(2) shouldBe null
        }
    }

    "When replace item then should get a new value" {
        val item = Item(3, "Task3")
        tracker.add(item)
        val newItem = Item(3, "newTask")
        tracker.replace(3, newItem)
        println(tracker.findById(3)?.name)
    }
})
