package io.github.dehuckakpyt.telegrambot.config.constant


/**
 * Created on 22.06.2024.
 *
 * @author Denis Matytsin
 */
internal object PropertiesConstant {
    internal const val TELEGRAM_BOT_SOURCE_JPA = "telegram-bot.source-jpa.enabled"
    internal const val TELEGRAM_BOT_SOURCE_JPA_MESSAGE_SOURCE = "telegram-bot.source-jpa.message-source.enabled"
    internal const val TELEGRAM_BOT_SOURCE_JPA_CHAIN_SOURCE = "telegram-bot.source-jpa.chain-source.enabled"
    internal const val TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE = "telegram-bot.source-jpa.callback-content-source.enabled"
    internal const val TELEGRAM_BOT_SOURCE_JPA_CALLBACK_CONTENT_SOURCE_PER_USER = "telegram-bot.source-jpa.callback-content-source.per-user:20"
}