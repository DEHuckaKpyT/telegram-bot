package io.github.dehuckakpyt.telegrambotexample.test

import com.github.database.rider.core.api.dataset.DataSetExecutor
import com.github.database.rider.core.connection.ConnectionHolderImpl
import com.github.database.rider.core.dataset.DataSetExecutorImpl
import org.hibernate.dialect.PostgreSQLDialect
import org.testcontainers.containers.PostgreSQLContainer


/**
 * Created on 02.04.2024.
 *
 * @author Denis Matytsin
 */
object TestContainerInitializer {

    private val postgres = PostgreSQLContainer("postgres:16")
    val dataSetExecutor: DataSetExecutor

    init {
        postgres.withReuse(true)
        postgres.start()

        System.setProperty("spring.datasource.driver-class-name", postgres.driverClassName)
        System.setProperty("spring.datasource.url", postgres.jdbcUrl)
        System.setProperty("spring.datasource.username", postgres.username)
        System.setProperty("spring.datasource.password", postgres.password)
        System.setProperty("spring.jpa.properties.hibernate.dialect", PostgreSQLDialect::class.java.getCanonicalName())

        dataSetExecutor = DataSetExecutorImpl.instance(ConnectionHolderImpl(postgres.toDataSource().connection))
    }
}