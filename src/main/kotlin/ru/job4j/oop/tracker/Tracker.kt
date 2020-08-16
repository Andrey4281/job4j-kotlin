package ru.job4j.oop.tracker

class Tracker: ITracker {
    private val storage = ArrayList<Item>()
    private var ids: Long = 1;

    override fun add(item: Item): Item {
        item.id = this.ids;
        storage.add(item)
        ids++
        return item
    }

    override fun replace(id: Long, item: Item) {
        storage.singleOrNull { it.id == id }?.name = item.name
    }

    override fun delete(id: Long): Boolean = storage.removeIf { it.id == id  }

    override fun findAll(): Array<Item> = storage.toTypedArray()


    override fun findByName(key: String): Array<Item> = storage.filter { it.name == key }.toTypedArray()

    override fun findById(id: Long): Item = storage.single { it.id == id }
}
