package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_CERTIFICATE_PATH
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_DROP_PENDING_UPDATES
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_IP_ADDRESS
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_MAX_CONNECTIONS
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL_HOST
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL_PATH
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ResourceContent
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import io.github.dehuckakpyt.telegrambot.receiver.WebhookUpdateReceiver
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.support.GenericApplicationContext


/**
 * @author Denis Matytsin
 */
@ConditionalOnProperty(name = [TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER], havingValue = "true", matchIfMissing = false)
class WebhookUpdateReceiverInitializationConfig {

    @Bean
    //TODO create custom @ConditionalOnMissingBean
    @ConditionalOnMissingBean(name = ["webhookUpdateReceiver"], parameterizedContainer = [ConfigExpression::class])
    fun webhookUpdateReceiver(
        applicationContext: GenericApplicationContext,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL_HOST:#{null}}") urlHost: String? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL_PATH:#{null}}") urlPath: String? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_CERTIFICATE_PATH:#{null}}") certificatePath: String? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_IP_ADDRESS:#{null}}") ipAddress: String? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_MAX_CONNECTIONS:#{null}}") maxConnections: Int? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_DROP_PENDING_UPDATES:#{null}}") dropPendingUpdates: Boolean? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN:#{null}}") secretToken: String? = null,
    ): ConfigExpression<UpdateReceiver> = ConfigExpression { telegramBotBuildingConfig ->
        WebhookUpdateReceiver(
            applicationContext = applicationContext,
            bot = telegramBotBuildingConfig.telegramBot,
            updateResolver = telegramBotBuildingConfig.receiving.updateResolver,
            config = WebhookConfig(
                urlHost = urlHost,
                urlPath = urlPath,
                certificate = certificatePath?.let(::ResourceContent),
                ipAddress = ipAddress,
                maxConnections = maxConnections,
                dropPendingUpdates = dropPendingUpdates,
                secretToken = secretToken,
            )
        )
    }
}
