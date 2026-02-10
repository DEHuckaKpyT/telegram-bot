package io.github.dehuckakpyt.telegrambotexample.plugin

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.migration.jdbc.MigrationUtils

/**
 * Created on 29.11.2022.
 *<p>
 *
 * @author Denis Matytsin
 */
fun Application.configureDatabase() {

    val config = environment.config.config("database")

    fun connect() {
        val hikariConfig = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = config.property("url").getString()
            username = config.property("username").getString()
            password = config.property("password").getString()
            maximumPoolSize = 64
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_READ_COMMITTED"
            validate()
        }

        Database.connect(HikariDataSource(hikariConfig))
    }

    fun createTables() {
        transaction {
            MigrationUtils.statementsRequiredForDatabaseMigration(

            )
        }
    }

    connect()
    createTables()
}