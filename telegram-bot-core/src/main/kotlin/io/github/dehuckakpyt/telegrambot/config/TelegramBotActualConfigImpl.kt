package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.TelegramBotReceiverActualConfig
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.source.message.TelegramMessageSource
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.ktor.client.*
import io.ktor.client.engine.apache.*


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class TelegramBotActualConfigImpl : TelegramBotActualConfig {
    override lateinit var token: String
    override var username: String? = null
    override var clientConfiguration: (HttpClientConfig<ApacheEngineConfig>.() -> Unit)? = null
    override lateinit var telegramMessageSource: TelegramMessageSource<out TelegramMessage<out Any>>
    override lateinit var telegramBot: TelegramBot
    override lateinit var templater: Templater
    override lateinit var receiving: TelegramBotReceiverActualConfig
}