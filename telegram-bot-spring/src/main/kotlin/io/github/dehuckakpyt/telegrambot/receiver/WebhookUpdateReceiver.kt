package io.github.dehuckakpyt.telegrambot.receiver

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.getSecretTokenOrGenerateOrNull
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.getValidUrl
import io.github.dehuckakpyt.telegrambot.ext.config.receiver.urlPathOrDefault
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.coRouter


/**
 * @author Denis Matytsin
 */
internal class WebhookUpdateReceiver(
    applicationContext: GenericApplicationContext,
    private val bot: TelegramBot,
    private val updateResolver: UpdateResolver,
    private val config: WebhookConfig,
) : UpdateReceiver {

    private val logger: Logger = LoggerFactory.getLogger(WebhookUpdateReceiver::class.java)
    private val url: String = config.getValidUrl()
    private val secretToken: String? = config.getSecretTokenOrGenerateOrNull()

    init {
        applicationContext.registerBean { receiveUpdatesRoute() }
    }

    override fun start(): Unit = runBlocking(Dispatchers.Default) {
//        bot.setWebhook(
//            url = url,
//            certificate = config.certificate,
//            ipAddress = config.ipAddress,
//            maxConnections = config.maxConnections,
//            allowedUpdates = updateResolver.allowedUpdates,
//            dropPendingUpdates = config.dropPendingUpdates,
//            secretToken = secretToken,
//        )
        logger.info("Started webhook update receiver.")
    }

    private fun receiveUpdatesRoute() = coRouter {
        if (secretToken == null) {
            POST(config.urlPathOrDefault) { request ->
                updateResolver.processUpdate(request.awaitBody<Update>())

                return@POST OK_RESPONSE
            }
        } else {
            POST(config.urlPathOrDefault) { request ->
                if (secretToken != request.secretToken) {
                    logger.warn("Invalid secret token '${request.secretToken}' received.")
                    return@POST FORBIDDEN_RESPONSE
                }

                updateResolver.processUpdate(request.awaitBody<Update>())

                return@POST OK_RESPONSE
            }
        }

        onError<Throwable> { throwable, request ->
            logger.error("Error processing update:\n${request.awaitBody<String>()}", throwable)

            return@onError INTERNAL_SERVER_ERROR_RESPONSE
        }
    }

    override fun stop(): Unit = runBlocking(Dispatchers.Default) {
//        bot.deleteWebhook(
//            dropPendingUpdates = config.dropPendingUpdates
//        )
    }

    private val ServerRequest.secretToken: String? get() = headers().firstHeader("X-Telegram-Bot-Api-Secret-Token")

    private companion object {
        private val OK_RESPONSE = ServerResponse.ok().build().block()!!
        private val FORBIDDEN_RESPONSE = ServerResponse.status(HttpStatus.FORBIDDEN).build().block()!!
        private val INTERNAL_SERVER_ERROR_RESPONSE = ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build().block()!!
    }
}
