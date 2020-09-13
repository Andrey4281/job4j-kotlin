package ru.job4j.dsl

import org.apache.commons.dbcp2.BasicDataSource
import java.sql.Connection

class DSLDataSource private constructor(private val driverClassNameValue: String,
                                        private val urlValue: String,
                                        private val userNameValue: String,
                                        private val passwordValue: String,
                                        private val minIdleValue: Int,
                                        private val maxIdleValue: Int,
                                        private val maxOpenPreparedStatementsValue: Int) {

    private val dataSource: BasicDataSource by lazy { init() }
    val connection: Connection
        get() = dataSource.connection

    private fun init(): BasicDataSource {
        return BasicDataSource().apply {
            driverClassName = driverClassNameValue
            url = urlValue
            username = userNameValue
            password = passwordValue
            minIdle = minIdleValue
            maxIdle = maxIdleValue
            maxOpenPreparedStatements = maxOpenPreparedStatementsValue
        }
    }

    companion object Builder {
        private var driverClassNameValue: String? = null
        private var urlValue: String? = null
        private var userNameValue: String? = null
        private var passwordValue: String? = null
        private var minIdleValue: Int = 5
        private var maxIdleValue: Int = 10
        private var maxOpenPreparedStatementsValue: Int = 100

        fun driverClassName(driverClassName: String) = apply { driverClassNameValue  = driverClassName}

        fun url(url: String) = apply { urlValue = url }

        fun userName(userName: String) = apply { userNameValue = userName }

        fun password(password: String) = apply { passwordValue = password }

        fun minIdle(minIdle: Int) = apply { minIdleValue = minIdle }

        fun maxIdle(maxIdle: Int) = apply { maxIdleValue = maxIdle }

        fun maxOpenPreparedStatements(maxOpenPreparedStatements: Int) =
            apply { maxOpenPreparedStatementsValue = maxOpenPreparedStatements}

        fun build(): DSLDataSource = DSLDataSource(
            driverClassNameValue ?: "",
            urlValue?: "",
            userNameValue?: "",
            passwordValue?: "",
            minIdleValue,
            maxIdleValue,
            maxOpenPreparedStatementsValue
        )
    }
}

fun main() {
    val dataSource = DSLDataSource
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .url("jdbc:mysql://localhost:3306/kotlin_test")
        .userName("admin")
        .password("xEmtHgW2")
        .build()

    dataSource.connection.apply {
        use {
            createStatement().apply {
                use {
                   executeQuery("SELECT * FROM entity").apply {
                       use {
                           while (next()) {
                               println(getInt("id"))
                               println(getString("name"))
                           }
                       }
                   }
                }
            }
        }
    }
}
