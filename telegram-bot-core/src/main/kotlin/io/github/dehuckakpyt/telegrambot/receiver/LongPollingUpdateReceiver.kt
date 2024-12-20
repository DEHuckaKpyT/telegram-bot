package io.github.dehuckakpyt.telegrambot.receiver

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import kotlinx.coroutines.*
import org.apache.http.ConnectionClosedException
import org.slf4j.LoggerFactory


/**
 * Created on 06.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class LongPollingUpdateReceiver(
    private val bot: TelegramBot,
    private val updateResolver: UpdateResolver,
    private val config: LongPollingConfig,
) : UpdateReceiver {

    private val scope = CoroutineScope(Dispatchers.Default + CoroutineName("TelegramBot"))
    private val logger = LoggerFactory.getLogger(LongPollingUpdateReceiver::class.java)
    private val delayBetweenTries: Long = 5000
    private var lastUpdateId: Long? = null
    private var running: Boolean = false

    override fun start(): Unit {
        running = true
        scope.launch { retryingReceiveUpdates() }
    }

    private suspend fun retryingReceiveUpdates() {
        while (running) {
            try {
                receiveUpdates()
            } catch (throwable: Throwable) {
                if (running.not()) { // If bot was stopped by stop() method
                    if (throwable is ConnectionClosedException) { // It happens because of bot.client.close() when long polling connection is active
                        logger.info("Telegram-bot's (${bot.username}) client was closed.")
                    }
                    return
                }

                logger.error("Internal error. Receiving updates will be resumed after $delayBetweenTries milliseconds.", throwable)
                delay(delayBetweenTries)
            }
        }
    }

    private suspend fun receiveUpdates() {
        logger.info("Started update receiver")

        val allowedUpdates: Set<String> = updateResolver.allowedUpdates

        while (running) {
            val offset = lastUpdateId?.inc()

            val updates = bot.getUpdates(
                offset = offset,
                allowedUpdates = allowedUpdates,
                timeout = config.timeout,
                limit = config.limit
            )

            if (updates.isEmpty()) continue

            lastUpdateId = updates.last().updateId

            for (update in updates) {
                scope.launch {
                    updateResolver.processUpdate(update)
                }
            }
        }
    }

    override fun stop(): Unit {
        running = false
        bot.client.close()
        scope.cancel()
    }
}