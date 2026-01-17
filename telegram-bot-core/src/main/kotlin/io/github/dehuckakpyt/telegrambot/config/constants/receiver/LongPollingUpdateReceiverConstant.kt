package io.github.dehuckakpyt.telegrambot.config.constants.receiver

import kotlin.time.Duration.Companion.seconds


/**
 * @author Denis Matytsin
 */
object LongPollingUpdateReceiverConstant {

    public const val LONG_POLLING_RECEIVER_DEFAULT_TIMEOUT = 30
    public const val LONG_POLLING_RECEIVER_DEFAULT_RETRY_DELAY = 5_000L
    public val LONG_POLLING_RECEIVER_DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT = 10.seconds
}