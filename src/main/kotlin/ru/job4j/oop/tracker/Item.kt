package ru.job4j.oop.tracker

import ru.job4j.dsl.ItemStore

data class Item(var id: Long = 0, var name: String = "")

fun Item.save(): Item {
    ItemStore.save(this)
    return this
}

fun main() {
    val item = Item()
    item.save()
}

