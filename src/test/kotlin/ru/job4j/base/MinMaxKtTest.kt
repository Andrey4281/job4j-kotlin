package ru.job4j.base

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec


internal class MinMaxKtTest: StringSpec({
    "max(1,2) = 2" {
        max(1, 2) shouldBe 2
    }

    "max(-1, 5, 10) = 10" {
        max(-1, 5, 10) shouldBe 10
    }
})
