package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.controller.admin.message.TelegramMessageAdminController
import io.github.dehuckakpyt.telegrambot.controller.admin.user.TelegramUserAdminController
import io.github.dehuckakpyt.telegrambot.manager.access.admin.AdminApiAccessManager
import io.github.dehuckakpyt.telegrambot.mapper.controller.admin.message.TelegramMessageAdminMapper
import io.github.dehuckakpyt.telegrambot.mapper.controller.admin.user.TelegramUserAdminMapper
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver


/**
 * Admin spring api config.
 *
 * @author Denis Matytsin
 */
@AutoConfiguration
@Import(value = [
    TelegramMessageAdminController::class,
    TelegramUserAdminController::class,
])
@ComponentScan(basePackageClasses = [TelegramUserAdminMapper::class, TelegramMessageAdminMapper::class])
class AdminApiInitializationConfig {

    /** Default resolver for Pageable in controller. */
    @Bean
    @ConditionalOnMissingBean(ReactivePageableHandlerMethodArgumentResolver::class)
    fun reactivePageableHandlerMethodArgumentResolver(): HandlerMethodArgumentResolver =
        ReactivePageableHandlerMethodArgumentResolver()

    @Bean
    @ConditionalOnMissingBean(AdminApiAccessManager::class)
    fun adminUIAccessManager(): AdminApiAccessManager = object : AdminApiAccessManager {}
}