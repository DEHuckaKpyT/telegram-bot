package com.dehucka.telegrambot.plugin.config

import com.dehucka.telegrambot.BotHandling
import com.elbekd.bot.Bot
import freemarker.template.Configuration
import freemarker.template.Version
import io.ktor.server.config.*


/**
 * Created on 17.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class TelegramBotConfig(private val config: ApplicationConfig) {
    var enabled = config.propertyOrNull("enabled")?.getString()?.toBooleanStrict() ?: true
    var token = config.propertyOrNull("token")?.getString()
    var username = config.propertyOrNull("username")?.getString()
    var configureBot: Bot.() -> Unit = {}
    var handling: BotHandling.() -> Unit = {}
    var templateConfig = Configuration(Version("2.3.32"))

    fun configureBot(block: Bot.() -> Unit) {
        configureBot = block
    }

    fun handling(block: BotHandling.() -> Unit) {
        handling = block
    }

    fun configureTemplating(block: Configuration.() -> Unit) {
        templateConfig.block()
    }
}
