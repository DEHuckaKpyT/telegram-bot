package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.controller.admin.user.TelegramUserAdminController
import io.github.dehuckakpyt.telegrambot.mapper.controller.admin.user.TelegramUserAdminMapperImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver


/**
 * Admin spring api config.
 *
 * @author Denis Matytsin
 */
@Configuration
@Import(value = [
    TelegramUserAdminController::class,
    TelegramUserAdminMapperImpl::class,
])
class AdminApiInitializationConfig {

    /** Default resolver for Pageable in controller. */
    @Bean
    fun reactivePageableHandlerMethodArgumentResolver(): HandlerMethodArgumentResolver =
        ReactivePageableHandlerMethodArgumentResolver()
}