package ru.job4j.dsl

import java.sql.ResultSet
import java.sql.Statement

/**
 * This class is used for simplification operations with a database.
 * It is simply wrapper over jdbc that allow you don't worry about a resource management.
 * You can simply write your sql operations to the database.
 * You can add extension functions for execution CRUD operations with your entity to class Storage.
 * To perform it you should use Storage's methods execUpdate and execQuery.
 * @author asemenov
 * @since 18.09.2020
 * @version 1
 */
class Storage(private val dataSource: DSLDataSource) {

    /**
     * Execution update operation to a database.
     * @param update sql script of update operation.
     */
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

    /**
     * Execution select operation to database.
     * @param query sql script of select operation.
     * @param handler block of code which is needed for processing ResultSet object.
     */
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

/**
 * It is example how you can use Storage's extension functions for saving your entity to database.
 * @param entity it is your entity that you want to save to a database.
 */
fun Storage.create(entity: Entity): Long? =
    execUpdate("INSERT INTO entity(name) VALUES('${entity.name}')")

/**
 * It is an example how you can use Storage's extension functions for updating your entity in a database.
 * @param entity it is your entity that you want to update in a database.
 */
fun Storage.update(entity: Entity) =
    execUpdate("UPDATE entity SET name='${entity.name}' WHERE id=${entity.id}")

/**
 * It is an example how you can use Storage's extension functions for selecting your entities from a database.
 * @param id we use selecting by id in this example, but you can use your own kind of selecting.
 */
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

/**
 * It is an example how you can use Storage's extension functions for deleting your entities from a database.
 * @param id we use deleting by id in this example, but you can use your own kind of deleting.
 */
fun Storage.deleteById(id: Long) =
    execUpdate("DELETE FROM entity WHERE id=$id")

/**
 * It is a demonstration of using Storage class.
 */
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
