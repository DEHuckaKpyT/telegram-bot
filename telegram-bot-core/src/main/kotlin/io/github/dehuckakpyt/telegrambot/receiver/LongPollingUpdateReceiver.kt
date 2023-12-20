package io.github.dehuckakpyt.telegrambot.receiver

import com.dehucka.microservice.logger.Logging
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.context.InternalKoinComponent
import io.github.dehuckakpyt.telegrambot.plugin.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


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
) : UpdateReceiver, InternalKoinComponent, Logging {

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun start(): Unit {
        scope.launch { receiveUpdates() }
    }

    private suspend fun receiveUpdates() {
        while (true) {
            val offset = lastUpdateId?.inc()

            val updates = bot.getUpdates(
                offset = offset,
                allowedUpdates = null,
                timeout = config.timeout,
                limit = config.limit
            )

            if (updates.isEmpty()) continue

            lastUpdateId = updates.last().updateId

            updates.forEach { updateResolver.processUpdate(it) }
        }
    }

    override fun stop(): Unit {
        logger.info("Stopping client.")
        bot.stop()
        logger.info("Client stopped.")
        scope.cancel()
    }

    private companion object {
        @Volatile
        private var lastUpdateId: Int? = null
    }
}