package io.github.dehuckakpyt.telegrambotexample.plugin

import io.ktor.server.application.*
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.plugin.module.dsl.withConfiguration


/**
 * @author Denis Matytsin
 */
fun Application.configureDependencyInjection() {
    install(Koin) {
        slf4jLogger()
        withConfiguration<ExampleKtorKoinApp>()
    }
}

@Module
@Configuration
@ComponentScan("io.github.dehuckakpyt.telegrambotexample.handler")
class ExampleKtorHandlerModule

@Module
@Configuration
@ComponentScan("io.github.dehuckakpyt.telegrambotexample.factory")
class ExampleKtorFactoryModule

@Module
@Configuration
@ComponentScan("io.github.dehuckakpyt.telegrambotexample.runner")
class ExampleKtorRunnerModule

@KoinApplication(modules = [ExampleKtorHandlerModule::class, ExampleKtorFactoryModule::class, ExampleKtorRunnerModule::class])
class ExampleKtorKoinApp
