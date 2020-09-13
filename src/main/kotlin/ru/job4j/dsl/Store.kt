package ru.job4j.dsl

interface Store<T> {
    fun save(model: T): T
}
