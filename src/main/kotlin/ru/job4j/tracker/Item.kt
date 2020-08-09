package ru.job4j.tracker

class Item {
    var name: String
    var id: Long

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }
}
