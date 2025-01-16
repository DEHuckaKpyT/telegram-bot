package io.github.dehuckakpyt.telegrambot.receiver

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.github.dehuckakpyt.telegrambot.model.telegram.Update
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.registerBean
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter


/**
 * @author Denis Matytsin
 */
class WebhookUpdateReceiver(
    applicationContext: GenericApplicationContext,
    private val bot: TelegramBot,
    private val updateResolver: UpdateResolver,
    private val config: WebhookConfig,
) : UpdateReceiver {

    init {
        applicationContext.registerBean { receiveUpdatesRoute() }
    }

    override fun start(): Unit = runBlocking(Dispatchers.Default) {
//        bot.setWebhook(
//            url = config.url ?: throw IllegalArgumentException("Webhook url is required"),
//            certificate = config.certificate,
//            ipAddress = config.ipAddress,
//            maxConnections = config.maxConnections,
//            allowedUpdates = updateResolver.allowedUpdates,
//            dropPendingUpdates = config.dropPendingUpdates,
//            secretToken = config.secretToken,
//        )
    }

    private fun receiveUpdatesRoute() = coRouter {
        POST("/updates/receive") { request ->
            updateResolver.processUpdate(request.awaitBody<Update>())
            ok().buildAndAwait()
        }
    }

    override fun stop(): Unit = runBlocking(Dispatchers.Default) {
//        bot.deleteWebhook(
//            dropPendingUpdates = config.dropPendingUpdates
//        )
    }
}
