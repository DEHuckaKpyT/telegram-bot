package io.github.dehuckakpyt.telegrambot.controller.admin.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.holder.AdminPanelConfigHolder
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Denis Matytsin
 */
@RestController
@RequestMapping("/admin-panel")
class AdminPanelConfigController(
    bot: TelegramBot,
    adminPanelConfigHolder: AdminPanelConfigHolder,
    @Value("\${telegram-bot.administration.admin-api.prefix:/api}") backendApiPrefix: String,
    @Value("\${telegram-bot.administration.admin-panel.prefix:/admin-ui}") adminPanelPrefix: String,
) {
    private val frontendConfigJson = mapOf(
        "apiPrefix" to backendApiPrefix,
        "adminPanelPrefix" to adminPanelPrefix,
        "telegramBotUsername" to bot.username,

        "defaultPageSize" to adminPanelConfigHolder.defaultPageSize,
    )

    @GetMapping(value = ["/config"], produces = ["application/json"])
    fun config(): Map<String, Any?> = frontendConfigJson
}