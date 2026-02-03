package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.auth.InMemoryAdminApiTokenStore
import io.github.dehuckakpyt.telegrambot.auth.TelegramAdminApiTokenStore
import io.github.dehuckakpyt.telegrambot.config.holder.AdminPanelConfigHolder
import io.github.dehuckakpyt.telegrambot.controller.admin.auth.AuthAdminController
import io.github.dehuckakpyt.telegrambot.controller.admin.config.AdminPanelConfigController
import io.github.dehuckakpyt.telegrambot.controller.frontend.FrontendController
import io.github.dehuckakpyt.telegrambot.util.auth.TelegramAuthChecker
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import java.time.Duration
import kotlin.time.toKotlinDuration


/**
 * @author Denis Matytsin
 */
@AutoConfiguration
@Import(value = [
    FrontendController::class,
    AuthAdminController::class,
    AdminPanelConfigController::class,
])
@ConfigurationPropertiesScan(basePackageClasses = [AdminPanelConfigHolder::class])
class AdminPanelConfig {

    @Bean
    @ConditionalOnMissingBean
    fun telegramAdminApiTokenStore(
        @Value("\${telegram-bot.administration.admin-api.token-store.token-ttl:PT30M}") ttl: Duration,
    ): TelegramAdminApiTokenStore = InMemoryAdminApiTokenStore(ttl.toKotlinDuration())

    @Bean
    @ConditionalOnMissingBean
    fun telegramAuthChecker(
        @Value("\${telegram-bot.token}") token: String,
    ) = TelegramAuthChecker(token)
}
