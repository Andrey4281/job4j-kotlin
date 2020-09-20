package ru.job4j.dsl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InfixFunctions {

    private infix fun Any.eq(expected: Any) = assertEquals(this, expected)


    private infix fun Any.notEq(expected: Any) = assertNotEquals(this, expected)

    private infix fun List<Any>.contains(list: List<Any>) = if (this.containsAll(list)) assertTrue(true) else assertTrue(false)

    @Test
    fun whenCompareTheSameNumbersShouldGetTrue() = 1 eq 1

    @Test
    fun whenCompareTheDifferentNumbersShouldGetFalse() = 1 notEq 2

    @Test
    fun listShouldContainsSomeElements() {
        val list = listOf(1, 2, 3, 4)
        list contains listOf(2, 3)
    }
}
