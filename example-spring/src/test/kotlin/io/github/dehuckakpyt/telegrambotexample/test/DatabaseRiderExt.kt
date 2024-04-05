package io.github.dehuckakpyt.telegrambotexample.test

import com.github.database.rider.core.configuration.DataSetConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.dehuckakpyt.telegrambotexample.test.TestContainerInitializer.dataSetExecutor
import org.testcontainers.containers.JdbcDatabaseContainer


/**
 * Created on 02.04.2024.
 *
 * @author Denis Matytsin
 */
/**
 * Returns an initialized [HikariDataSource] connected to this [JdbcDatabaseContainer].
 *
 * (Copied from kotest-extensions-testcontainers)
 *
 * @param configure a thunk to configure the [HikariConfig] used to create the datasource.
 */
fun JdbcDatabaseContainer<*>.toDataSource(configure: HikariConfig.() -> Unit = {}): HikariDataSource {
    val config = HikariConfig().apply {
        jdbcUrl = this@toDataSource.jdbcUrl
        username = this@toDataSource.username
        password = this@toDataSource.password
        minimumIdle = 0
        maximumPoolSize = 8
        idleTimeout = 10000
        configure()
    }
    return HikariDataSource(config)
}

fun clearDatabase() {
    dataSetExecutor.clearDatabase(DataSetConfig())
}

fun createDataSet(vararg datasets: String) {
    val dataSetConfig = DataSetConfig(*datasets)
    dataSetExecutor.createDataSet(dataSetConfig)
}

fun compareCurrentDataSetWith(vararg datasets: String, ignoringColumns: Array<String> = emptyArray()) {
    val dataSetConfig = DataSetConfig(*datasets)
    dataSetExecutor.compareCurrentDataSetWith(dataSetConfig, ignoringColumns)
}