package io.github.dehuckakpyt.telegrambot.config.constant


/**
 * @author Denis Matytsin
 */
internal object SpringPropertiesConstant {
    internal const val TELEGRAM_BOT_SPRING_UPDATE_RECEIVER_VARIANT = "telegram-bot.spring.update-receiver" // webhook or long-polling

    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL_HOST = "telegram-bot.spring.update-receiver.webhook.url.host"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL_PATH = "telegram-bot.spring.update-receiver.webhook.url.path"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_CERTIFICATE_PATH = "telegram-bot.spring.update-receiver.webhook.certificate-path"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_IP_ADDRESS = "telegram-bot.spring.update-receiver.webhook.ip-address"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_MAX_CONNECTIONS = "telegram-bot.spring.update-receiver.webhook.max-connections"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_DROP_PENDING_UPDATES = "telegram-bot.spring.update-receiver.webhook.drop-pending-updates"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN = "telegram-bot.spring.update-receiver.webhook.secret-token"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN_RANDOM_GENERATION = "telegram-bot.spring.update-receiver.webhook.secret-token-random-generation"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN_RANDOM_GENERATION_PRINT_ON_STARTUP = "telegram-bot.spring.update-receiver.webhook.secret-token-random-generation.print-on-startup"
}