package ru.job4j.oop.list

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec


internal class SimpleLinkedListTest: StringSpec({

    "When add new elements then should get it" {
        val list: SimpleLinkedList<Int> = SimpleLinkedList()

        list.add(1)
        list.add(2)
        val iterator = list.iterator()

        iterator.next() shouldBe 2
        iterator.next() shouldBe 1
    }

    "When modify list during iteration should throw ConcurrentModificationException" {
        val list: SimpleLinkedList<Int> = SimpleLinkedList()

        list.add(3)
        var iterator = list.iterator()
        list.add(4)

        shouldThrow<ConcurrentModificationException> {
            iterator.next() }
    }

    "When try get element from empty list should throw NoSuchElementException" {
        val list: SimpleLinkedList<Int> = SimpleLinkedList()

        shouldThrow<NoSuchElementException> {
            list.iterator().next()
        }
    }
})
