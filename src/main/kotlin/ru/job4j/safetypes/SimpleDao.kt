package ru.job4j.safetypes

import java.sql.Connection
import java.sql.DriverManager

class SimpleDao: AutoCloseable {

    private lateinit var connection: Connection
    private lateinit var dispatcher: Map<String, (String) -> String>

    fun init() {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kotlin_test"
        ,"admin", "xEmtHgW2").apply {
            val select = { sql: String ->
                var count: Int = 0
                createStatement().apply {
                    use {
                        executeQuery(sql).apply {
                            use {
                                while (next()) {
                                    count++
                                }
                            }
                        }
                    }
                }
                "$count row(s) were selected"
            }

            val update = {sql: String ->
                var count = 0
                createStatement().apply {
                    use {
                        count = executeUpdate(sql)
                    }
                }
                "$count row(s) were updated"
            }

            val delete = { sql: String ->
                var count = 0
                createStatement().apply {
                    use {
                        count = executeUpdate(sql)
                    }
                }
                "$count row(s) were deleted"
            }

            val insert = {sql: String ->
                var count = 0
                createStatement().apply {
                    use {
                        count = executeUpdate(sql)
                    }
                }
                "$count row(s) were inserted"
            }

            dispatcher = mapOf("select" to select, "update" to update,
                "delete" to delete, "insert" to insert)
        }
    }

    fun exec(sql: String): String {
        var res: String = ""
        dispatcher.apply {
            sql.apply {
                when {
                    contains("select", ignoreCase = true) -> res = get("select")!!.invoke(this)
                    contains("update", true) -> res = get("update")!!.invoke(this)
                    contains("delete", true) -> res = get("delete")!!.invoke(this)
                    contains("insert", true) -> res = get("insert")!!.invoke(this)
                }
            }
        }
        return res
    }

    override fun close() {
        connection.close()
    }
}

fun main() {
    SimpleDao().apply {
        init()
        println(exec("INSERT INTO entity(name) VALUES('test')"))
        close()
    }
}
