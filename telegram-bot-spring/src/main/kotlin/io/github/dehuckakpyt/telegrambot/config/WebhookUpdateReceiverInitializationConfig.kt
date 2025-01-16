package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_CERTIFICATE_PATH
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_DROP_PENDING_UPDATES
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_IP_ADDRESS
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_MAX_CONNECTIONS
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN
import io.github.dehuckakpyt.telegrambot.config.constant.SpringPropertiesConstant.TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL
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
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_URL}") url: String,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_CERTIFICATE_PATH:}") certificatePath: String? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_IP_ADDRESS:}") ipAddress: String? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_MAX_CONNECTIONS:}") maxConnections: Int? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_DROP_PENDING_UPDATES:}") dropPendingUpdates: Boolean? = null,
        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN:}") secretToken: String? = null,
//        @Value("\${$TELEGRAM_BOT_SPRING_WEBHOOK_UPDATE_RECEIVER_SECRET_TOKEN:#{T(java.util.UUID).randomUUID().toString()}}") secretToken: String,
    ): ConfigExpression<UpdateReceiver> = ConfigExpression { telegramBotBuildingConfig ->
        WebhookUpdateReceiver(
            applicationContext = applicationContext,
            bot = telegramBotBuildingConfig.telegramBot,
            updateResolver = telegramBotBuildingConfig.receiving.updateResolver,
            config = WebhookConfig(
                url = url,
                certificate = certificatePath?.let(::ResourceContent),
                ipAddress = ipAddress,
                maxConnections = maxConnections,
                dropPendingUpdates = dropPendingUpdates,
                secretToken = secretToken,
            )
        )
    }
}
