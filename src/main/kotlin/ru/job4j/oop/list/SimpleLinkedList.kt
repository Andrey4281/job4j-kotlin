package ru.job4j.oop.list

class SimpleLinkedList<T>: Iterable<T>, ListIterator<T> {

    private var head : Node<T>? = null
    private var pointer: Node<T>? = null
    private var currentIndex: Int = 0
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
        pointer = head
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

    override fun hasNext(): Boolean = pointer != null

    override fun hasPrevious(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun next(): T {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        val res = pointer!!.value
        pointer = pointer!!.next
        currentIndex++
        return res
    }

    override fun nextIndex(): Int {
        if (!hasNext())  {
            throw NoSuchElementException()
        }
        return currentIndex + 1
    }

    override fun previous(): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun previousIndex(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun main() {
    val list = SimpleLinkedList<String>()
    list.add("a")
    list.add("b")
    list.add("c")
    for (value in list) {
        println(value)
    }
}
