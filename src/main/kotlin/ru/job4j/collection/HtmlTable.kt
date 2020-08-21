package ru.job4j.collection

class HtmlTable {
    fun table(row: Int, cell: Int): String {
        return StringBuilder().apply {
            append("<table>\n")
            for (i in 0 until row) {
                append("<tr>\n")
                for (j in 0 until cell) {
                    append("<td></td>\n")
                }
                append("</tr>\n")
            }
            append("</table>")
        }.toString()
    }
}

fun main() {
    println(HtmlTable().table(3,4))
}
