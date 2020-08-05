package ru.job4j.base

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class CalcKtTest: StringSpec({
    "1.0 + 2.0" {
        add(1.0, 2.0) shouldBe 3.0
    }

    "1.0 - 2.0" {
        subtract(1.0, 2.0) shouldBe -1.0
    }

    "5.0 * 5.0" {
        multiplication(5.0, 5.0) shouldBe 25.0
    }

    "5 / 4" {
        division(5.0, 4.0) shouldBe 1.25
    }
})
