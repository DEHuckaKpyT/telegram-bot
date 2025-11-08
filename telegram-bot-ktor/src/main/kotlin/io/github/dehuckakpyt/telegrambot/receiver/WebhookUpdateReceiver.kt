package io.github.dehuckakpyt.telegrambot.receiver

import com.fasterxml.jackson.module.kotlin.readValue
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.constants.api.client.ApiConstants.DEFAULT_MAPPER
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.context.InternalKoinContext
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.getSecretTokenOrGenerateOrNull
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.getValidUrl
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.secretTokenRandomGenerationPrintOnStartupOrDefault
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.urlPathOrDefault
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import io.ktor.http.HttpStatusCode.Companion.Forbidden
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.koin.core.qualifier.named
import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * @author Denis Matytsin
 */
internal class WebhookUpdateReceiver(
    private val bot: TelegramBot,
    private val updateResolver: UpdateResolver,
    private val config: WebhookConfig,
) : UpdateReceiver {

    private val application: Application = InternalKoinContext.koin.get<Application>(named("application"))
    private val logger: Logger = LoggerFactory.getLogger(WebhookUpdateReceiver::class.java)
    private val url: String = config.getValidUrl()
    private val secretToken: String? = config.getSecretTokenOrGenerateOrNull(config.secretTokenRandomGenerationPrintOnStartupOrDefault)

    init {
        application.routing { receiveUpdatesRoute() }
    }

    override fun start(): Unit = runBlocking(Dispatchers.Default) {
        bot.setWebhook(
            url = url,
            certificate = config.certificate,
            ipAddress = config.ipAddress,
            maxConnections = config.maxConnections,
            allowedUpdates = updateResolver.allowedUpdates,
            dropPendingUpdates = config.dropPendingUpdates,
            secretToken = secretToken,
        )
        logger.info("Started webhook update receiver.")
    }

    private fun Routing.receiveUpdatesRoute() {
        if (secretToken == null) {
            post(config.urlPathOrDefault) {
                val update = DEFAULT_MAPPER.readValue<Update>(call.receiveStream())
                updateResolver.processUpdate(update)

                call.response.status(NoContent)
            }
        } else {
            post(config.urlPathOrDefault) {
                if (secretToken != call.secretToken) {
                    logger.warn("Invalid secret token '${call.secretToken}' received.")
                    call.response.status(Forbidden)
                    return@post
                }
                val update = DEFAULT_MAPPER.readValue<Update>(call.receiveStream())
                updateResolver.processUpdate(update)

                call.response.status(NoContent)
            }
        }
    }

    override fun stop(): Unit = runBlocking(Dispatchers.Default) {
        bot.deleteWebhook(
            dropPendingUpdates = config.dropPendingUpdates
        )
        logger.info("Stopped webhook update receiver.")
    }

    private val RoutingCall.secretToken: String? get() = request.headers["X-Telegram-Bot-Api-Secret-Token"]
}
