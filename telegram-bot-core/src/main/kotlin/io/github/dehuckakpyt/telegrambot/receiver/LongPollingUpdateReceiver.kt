package io.github.dehuckakpyt.telegrambot.receiver

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.constants.receiver.LongPollingUpdateReceiverConstant.LONG_POLLING_RECEIVER_DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT
import io.github.dehuckakpyt.telegrambot.config.constants.receiver.LongPollingUpdateReceiverConstant.LONG_POLLING_RECEIVER_DEFAULT_RETRY_DELAY
import io.github.dehuckakpyt.telegrambot.config.constants.receiver.LongPollingUpdateReceiverConstant.LONG_POLLING_RECEIVER_DEFAULT_TIMEOUT
import io.github.dehuckakpyt.telegrambot.config.receiver.LongPollingConfig
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver
import kotlinx.coroutines.*
import org.apache.http.ConnectionClosedException
import org.slf4j.LoggerFactory
import kotlin.time.Duration


/**
 * @author Denis Matytsin
 */
internal class LongPollingUpdateReceiver(
    private val bot: TelegramBot,
    private val updateResolver: UpdateResolver,
    config: LongPollingConfig,
) : UpdateReceiver {

    private val limit: Int? = config.limit
    private val timeout: Int = config.timeout ?: LONG_POLLING_RECEIVER_DEFAULT_TIMEOUT
    private val retryDelay: Long = config.retryDelay ?: LONG_POLLING_RECEIVER_DEFAULT_RETRY_DELAY
    private val gracefulShutdownTimeout: Duration = config.gracefulShutdownTimeout ?: LONG_POLLING_RECEIVER_DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT

    private val pollingJob = SupervisorJob()
    private val processingJob = SupervisorJob()
    private val pollingScope = CoroutineScope(Dispatchers.IO + pollingJob + CoroutineName("TelegramBot-Polling"))
    private val processingScope = CoroutineScope(Dispatchers.Default + processingJob + CoroutineName("TelegramBot-Processing"))

    private val logger = LoggerFactory.getLogger(LongPollingUpdateReceiver::class.java)
    private var lastUpdateId: Long? = null

    @Volatile
    private var running: Boolean = false

    override fun start(): Unit {
        running = true

        pollingScope.launch { retryingReceiveUpdates() }
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

                logger.error("Internal error. Receiving updates will be resumed after $retryDelay milliseconds.", throwable)
                delay(retryDelay)
            }
        }
    }

    private suspend fun receiveUpdates() {
        logger.info("Started long polling update receiver.")

        val allowedUpdates: Set<String> = updateResolver.allowedUpdates

        while (running) {
            val offset = lastUpdateId?.inc()

            val updates = bot.getUpdates(
                offset = offset,
                allowedUpdates = allowedUpdates,
                timeout = timeout,
                limit = limit
            )

            if (updates.isEmpty()) continue

            lastUpdateId = updates.last().updateId

            for (update in updates) {
                processingScope.launch {
                    updateResolver.processUpdate(update)
                }
            }
        }
    }

    override fun stop(): Unit {
        // Signal all loops that no more work should be performed
        // (prevents accepting new updates)
        running = false

        // Force interruption of an active long-polling HTTP request
        // This causes bot.getUpdates(...) to fail fast and exit the polling loop
        bot.client.close()
        // Cancel the polling coroutine hierarchy so no new polling cycles start
        pollingJob.cancel()

        // Wait for already received updates to finish processing
        // This enables graceful shutdown instead of killing in-flight handlers
        val startTime = System.currentTimeMillis()
        val activeChildren = processingJob.children.count()

        if (activeChildren > 0) {
            logger.info("Waiting for $activeChildren in-flight update(s) to finish...")

            runBlocking {
                val finished = withTimeoutOrNull(gracefulShutdownTimeout) {
                    // Wait for all active child coroutines only
                    processingJob.children.forEach { it.join() }
                    true
                }

                if (finished != true) {
                    val elapsed = System.currentTimeMillis() - startTime
                    logger.warn(
                        "Timeout while waiting update processing to finish " +
                                "for Telegram-bot '${bot.username}'. " +
                                "Waited ${elapsed}ms, some updates may not have been fully processed."
                    )
                }
            }
        }

        // Final cleanup: cancel remaining processing coroutines
        // (should be no-op if everything finished normally)
        processingJob.cancel()
    }
}