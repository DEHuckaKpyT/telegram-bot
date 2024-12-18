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

    private val scope = CoroutineScope(Dispatchers.Default)
    private val logger = LoggerFactory.getLogger(LongPollingUpdateReceiver::class.java)
    private val delayBetweenTries: Long = 5000
    private var lastUpdateId: Long? = null

    override fun start(): Unit {
        scope.launch { retryingReceiveUpdates() }
    }

    private suspend fun retryingReceiveUpdates() {
        while (true) {
            try {
                receiveUpdates()
            } catch (e: ConnectionClosedException) {
                logger.info("Telegram-bot's (${bot.username}) client was closed.")
                return
            } catch (throwable: Throwable) {
                logger.error("Internal error. Receiving updates will be resumed after $delayBetweenTries milliseconds.", throwable)
                delay(delayBetweenTries)
            }
        }
    }

    private suspend fun receiveUpdates() {
        logger.info("Started update receiver")

        val allowedUpdates: Set<String> = updateResolver.allowedUpdates

        while (true) {
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
        bot.client.close()
        scope.cancel()
    }
}