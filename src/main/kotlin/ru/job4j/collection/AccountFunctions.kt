package ru.job4j.collection

fun filterAccountListByNameAndBalance(list: List<Account>, name: String): List<Account> {
    return list.filter { it.name.contains("Ivan") && it.balance > 0 }
}


