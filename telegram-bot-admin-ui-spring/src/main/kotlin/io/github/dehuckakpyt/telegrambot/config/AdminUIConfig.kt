package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.auth.InMemoryAdminUITokenStore
import io.github.dehuckakpyt.telegrambot.auth.TelegramAdminUITokenStore
import io.github.dehuckakpyt.telegrambot.controller.admin.auth.AuthAdminController
import io.github.dehuckakpyt.telegrambot.util.auth.TelegramAuthChecker
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import java.time.Duration
import kotlin.time.toKotlinDuration


/**
 * @author Denis Matytsin
 */
@AutoConfiguration
@Import(AuthAdminController::class)
class AdminUIConfig {

    @Bean
    @ConditionalOnMissingBean
    fun telegramAdminUITokenStore(
        @Value("\${telegram-bot.admin-ui.token-store.token-ttl:PT30M}") ttl: Duration,
    ): TelegramAdminUITokenStore = InMemoryAdminUITokenStore(ttl.toKotlinDuration())

    @Bean
    @ConditionalOnMissingBean
    fun telegramAuthChecker(
        @Value("\${telegram-bot.token}") token: String,
    ) = TelegramAuthChecker(token)
}
