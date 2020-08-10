package ru.job4j.tracker

interface ITracker {
    fun add(item: Item): Item
    fun replace(id: Long, item: Item)
    fun delete(id: Long): Boolean
    fun findAll(): Array<Item>
    fun findByName(key: String): Array<Item>
    fun findById(id: Long): Item
}
