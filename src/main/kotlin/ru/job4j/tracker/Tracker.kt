package ru.job4j.tracker

class Tracker: ITracker {
    private val storage = ArrayList<Item>()

    override fun add(item: Item): Item {
        storage.add(item)
        return item
    }

    override fun replace(id: Long, item: Item) {
        storage.singleOrNull { it.id == id }?.name = item.name
    }

    override fun delete(id: Long): Boolean = storage.removeIf { it.id == id  }

    override fun findAll(): Array<Item> = storage.toTypedArray()


    override fun findByName(key: String): Array<Item> = storage.filter { it.name == key }.toTypedArray()

    override fun findById(id: Long): Item = storage.find { it.id == id }!!
}
