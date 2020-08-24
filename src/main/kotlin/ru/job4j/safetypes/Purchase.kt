package ru.job4j.safetypes

import java.util.*

data class Purchase(val name: String, val created: Date = Date(), val address: Address? = null)

fun createHtmlTable(purchases: List<Purchase>): String {
    return with(StringBuilder()) {
        append("<table>\n")
        append("<tr>\n")
        append("<th>Name</th>\n")
        append("<th>Date</th>\n")
        append("<th>Address</th>\n")
        append("</tr>\n")
        purchases.forEach {
            it.apply {
                append("<tr>\n")

                append("<td>")
                append(name)
                append("</td>\n")

                append("<td>")
                append(created)
                append("</td>\n")

                append("<td>")
                append(address?: "")
                append("</td>\n")

                append("</tr>\n")
            }
        }
        append("</table>")
    }.toString()
}

fun main() {
    val purchases = listOf(
        Purchase("purchaseFirst"),
        Purchase("purchaseSecond", address = Address("street", "home", 1))
    )

    println(createHtmlTable(purchases))
}
