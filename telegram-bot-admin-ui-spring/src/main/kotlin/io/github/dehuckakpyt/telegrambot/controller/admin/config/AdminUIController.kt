package io.github.dehuckakpyt.telegrambot.controller.admin.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.holder.AdminUIFrontendConfigHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Denis Matytsin
 */
@RestController
@RequestMapping("/admin-ui")
class AdminUIController(
    bot: TelegramBot,
    adminUIFrontendConfigHolder: AdminUIFrontendConfigHolder,
) {
    private val frontendConfigJs = """
        var TELEGRAM_BOT_HOST = "${adminUIFrontendConfigHolder.host}";
        var TELEGRAM_BOT_USERNAME = "${bot.username}";
    """.trimIndent()
    private val frontendConfigJson = mapOf(
        "host" to adminUIFrontendConfigHolder.host,
        "telegramBotUsername" to bot.username,
    )

//    @GetMapping(value = ["/config"], produces = ["application/javascript"])
//    fun configJs(): String = frontendConfigJs

    @GetMapping(value = ["/config"], produces = ["application/json"])
    fun configJson(): Map<String, Any?> = frontendConfigJson
}