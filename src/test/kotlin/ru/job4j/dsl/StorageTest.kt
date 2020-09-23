package ru.job4j.dsl

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

internal class StorageTest: StringSpec({
    val storage = Storage(DSLDataSource
        .driverClassName("org.h2.Driver")
        .url("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1")
        .build())

    storage.execUpdate("CREATE TABLE entity(" +
            "id bigint(20) NOT NULL auto_increment, " +
            "name varchar(255) NOT NULL" +
            ")")

    "When create new object in database then should get it" {
        val expectedEntity = Entity(name = "Hello world!")
        var actualEntity: Entity?
        storage.apply {
            expectedEntity.id = create(expectedEntity)
            actualEntity = findById(expectedEntity.id!!)
        }
        actualEntity shouldBe expectedEntity
    }

    "When update new object in database then should get updated value" {
        val expectedEntity = Entity(name = "ValueOne")
        val actualEntity: Entity?
        storage.apply {
            expectedEntity.id = create(expectedEntity)
            expectedEntity.name = "ValueTwo"
            update(expectedEntity)
            actualEntity = findById(expectedEntity.id!!)
        }
        actualEntity shouldBe expectedEntity
    }

    "When delete an object from database then should get null" {
        val entity = Entity(name = "ValueThree")
        storage.apply {
            val id = create(entity)
            deleteById(id!!)
            findById(id) shouldBe null
        }
    }
})
