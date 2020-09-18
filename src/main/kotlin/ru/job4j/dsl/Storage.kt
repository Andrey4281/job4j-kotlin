package ru.job4j.dsl

import java.sql.ResultSet
import java.sql.Statement

class Storage(private val dataSource: DSLDataSource) {

    fun execUpdate(update: String): Long? {
        var res: Long? = null
        execSt {
            use {
                executeUpdate(update, Statement.RETURN_GENERATED_KEYS)
                generatedKeys.apply {
                    use {
                        if (next()) {
                            res = getLong(1)
                        }
                    }
                }
            }
        }
        return res
    }

    fun <T> execQuery(query: String, handler: ResultSet.() -> T): T? {
        var res: T? = null
        execSt {
            use {
                executeQuery(query).use {
                    res = it.handler()
                }
            }
        }
        return res
    }

    private fun <R> execSt(handler: Statement.() -> R?): R? {
        var res: R? = null
        dataSource.connection.apply {
            use {
                createStatement().apply {
                    use {
                        res = handler()
                    }
                }
            }
        }
        return res
    }
}

fun Storage.create(entity: Entity): Long? =
    execUpdate("INSERT INTO entity(name) VALUES('${entity.name}')")

fun Storage.update(entity: Entity) =
    execUpdate("UPDATE entity SET name='${entity.name}' WHERE id=${entity.id}")


fun Storage.findById(id: Long): Entity? {
    return execQuery("SELECT * FROM entity WHERE id=$id") {
        var result: Entity? = null
        if (next()) {
            result = Entity(name = getString("name"))
            result.id = getLong("id")
        }
        result
    }
}

fun Storage.deleteById(id: Long) =
    execUpdate("DELETE FROM entity WHERE id=$id")


fun main() {
    val dataSource = DSLDataSource
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .url("jdbc:mysql://localhost:3306/kotlin_test")
        .userName("admin")
        .password("xEmtHgW2")
        .build()

    val storage = Storage(dataSource)

    storage.apply {
        val entity = Entity(name = "Hello world!")
        entity.id = create(entity)
        entity.name = "Kotlin"
        update(entity)

        val otherEntity = findById(entity.id!!)
        println(otherEntity)

        deleteById(entity.id!!)
    }

}
