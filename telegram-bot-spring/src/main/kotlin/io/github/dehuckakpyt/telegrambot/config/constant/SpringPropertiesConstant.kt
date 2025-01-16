package io.github.dehuckakpyt.telegrambot.config.constant


/**
 * @author Denis Matytsin
 */
internal object SpringPropertiesConstant {
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER = "telegram-bot.spring.update-receiver.webhook.enabled"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL = "telegram-bot.spring.update-receiver.webhook.url"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_CERTIFICATE_PATH = "telegram-bot.spring.update-receiver.webhook.certificate-path"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_IP_ADDRESS = "telegram-bot.spring.update-receiver.webhook.ip-address"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_MAX_CONNECTIONS = "telegram-bot.spring.update-receiver.webhook.max-connections"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_DROP_PENDING_UPDATES = "telegram-bot.spring.update-receiver.webhook.drop-pending-updates"
    internal const val TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN = "telegram-bot.spring.update-receiver.webhook.secret-token"
}