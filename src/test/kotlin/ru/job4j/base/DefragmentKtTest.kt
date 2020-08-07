package ru.job4j.base

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class DefragmentKtTest: StringSpec({
    "When input a full array should then get the same" {
        val arrayActual = arrayOfNulls<String>(3)
        arrayActual[0] = "Petr"
        arrayActual[1] = "Ga"
        arrayActual[2] = "Andrey"
        val arrayExpected = arrayOfNulls<String>(3)
        arrayExpected[0] = "Petr"
        arrayExpected[1] = "Ga"
        arrayExpected[2] = "Andrey"
        defragment(arrayActual)
        arrayActual shouldBe arrayExpected
    }

    "When input an empty array should then get the same" {
        val arrayActual = arrayOfNulls<String>(3)
        arrayActual[0] = null
        arrayActual[1] = null
        arrayActual[2] = null
        val arrayExpected = arrayOfNulls<String>(3)
        arrayExpected[0] = null
        arrayExpected[1] = null
        arrayExpected[2] = null
        defragment(arrayActual)
        arrayActual shouldBe arrayExpected
    }

    "When input an array should then get it in suitable form" {
            val arrayActual = arrayOfNulls<String>(5)
            arrayActual[0] = "Petr"
            arrayActual[1] = null
            arrayActual[2] = "Andrey"
            arrayActual[3] = null
            arrayActual[4] = "Olga"
            val arrayExpected = arrayOfNulls<String>(5)
            arrayExpected[0] = "Petr"
            arrayExpected[1] = "Andrey"
            arrayExpected[2] = "Olga"
            arrayExpected[3] = null
            arrayExpected[4] = null
            defragment(arrayActual)
            arrayActual shouldBe arrayExpected
    }
})
