package ru.job4j.safetypes

import java.sql.Connection
import java.sql.DriverManager

class SimpleDao: AutoCloseable {

    private lateinit var connection: Connection
    private lateinit var dispatcher: Map<String, (String) -> String>

    fun init() {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kotlin_test"
        ,"admin", "xEmtHgW2")

        val select = {sql: String ->
            var count: Int = 0
            connection.createStatement().use { statement ->
                statement.executeQuery(sql).use {resultSet ->
                    while (resultSet.next()) {
                        count++
                    }
                }
            }
            "$count row(s) were selected"
        }

        val update = {sql: String ->
            val count = connection.createStatement().use { statement ->
                statement.executeUpdate(sql)
            }
            "$count row(s) were updated"
        }

        val delete = { sql: String ->
            val count = connection.createStatement().use { statement ->
                statement.executeUpdate(sql)
            }
            "$count row(s) were deleted"
        }

        val insert = { sql: String ->
            val count = connection.createStatement().use { statement ->
                statement.executeUpdate(sql)
            }
            "$count row(s) were inserted"
        }

        dispatcher = mapOf("select" to select, "update" to update,
            "delete" to delete, "insert" to insert)
    }

    fun exec(sql: String): String {
        var res: String = ""
        if (sql.contains("select", ignoreCase = true)) {
            res = dispatcher.get("select")!!.invoke(sql)
        } else if (sql.contains("update", true)) {
            res = dispatcher.get("update")!!.invoke(sql)
        } else if (sql.contains("delete", true)) {
            res = dispatcher.get("delete")!!.invoke(sql)
        } else if (sql.contains("insert", true)) {
            res = dispatcher.get("insert")!!.invoke(sql)
        }
        return res
    }

    override fun close() {
        connection.close()
    }
}

fun main() {
    val dao = SimpleDao()
    dao.init()
    println(dao.exec("INSERT INTO entity(name) VALUES('test')"))
    //println(dao.exec("SELECT * FROM entity"))
    dao.close()
}
