package ru.job4j.dsl

import ru.job4j.oop.tracker.Item

object ItemStore: Store<Item> {
    private val storage = ArrayList<Item>()

    override fun save(model: Item): Item {
        storage.add(model)
        return model
    }

}
