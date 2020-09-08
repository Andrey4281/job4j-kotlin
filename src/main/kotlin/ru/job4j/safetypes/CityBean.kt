package ru.job4j.safetypes

class CityBean {
    val cities: List<String> by lazy { loadCities() }

    private fun loadCities(): List<String> = listOf("Moscow", "St. Petersburg")
}

fun main() {
    println(CityBean().cities)
}
