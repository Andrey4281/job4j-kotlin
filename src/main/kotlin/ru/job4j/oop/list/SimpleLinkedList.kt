package ru.job4j.oop.list

class SimpleLinkedList<T>: Iterable<T> {
    private var head : Node<T>? = null
    @Volatile private var modeCount = 0
    private var size = 0

    fun add(value: T) {
        if (head == null) {
            head = Node<T>(value)
        } else {
            val current = Node<T>(value)
            current.next = head
            head = current
        }
        modeCount++
        size++
    }

    override fun iterator(): Iterator<T> {
        return LinkedIt()
    }

    inner class LinkedIt : Iterator<T> {
        private var pointer: Node<T>? = head
        private val modCountIt: Int = modeCount

        override fun hasNext(): Boolean {
            if (modeCount != modCountIt) {
                throw ConcurrentModificationException()
            }
            return pointer != null
        }

        override fun next(): T {
            if (modeCount != modCountIt) {
                throw ConcurrentModificationException()
            }
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            val value = pointer!!.value
            pointer = pointer?.next
            return value
        }
    }

    class Node<K>(val value: K, var next: Node<K>? = null)
}
