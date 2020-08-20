package ru.job4j.collection

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class AccountFunctionsKtTest: StringSpec({

    "When find some element should find it" {
        val list = listOf(Account("Ivan Ivanov", -1.0),
            Account("Ivan Petrov", 10.0),
            Account("Andrey Semenov", 100.0)
        )

        val filteredList = filterAccountListByNameAndBalance(list, "Ivan")

        filteredList.size shouldBe 1
        filteredList.get(0) shouldBe Account("Ivan Petrov", 10.0)
    }
})
