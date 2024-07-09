package io.github.dehuckakpyt.telegrambot.test

import com.github.database.rider.spring.api.DBRider
import io.github.dehuckakpyt.telegrambot.test.TestContainerInitializer
import java.lang.annotation.Inherited


/**
 * Created on 02.04.2024.
 *
 * @author Denis Matytsin
 */
@DBRider
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
annotation class EnablePostgresTestContainer {
    companion object {
        init {
            TestContainerInitializer
        }
    }
}
