package ru.job4j.collection

import java.util.*

fun toStringCampaingList(list: List<Campaing>) : List<String> {
    return list.map { "Name: ${it.name}, Date: ${it.created}, " +
            "Sity: ${it.address.sity}, Street: ${it.address.street}, " +
            "HouseNumber: ${it.address.houseNumber}" }
}

fun main() {
    val address = Address("Yekaterinburg", "Lenin street", "1/24")
    val list = listOf(Campaing("MyCampaing", address, Date()))
    println(toStringCampaingList(list)[0])
}
