package io.github.dehuckakpyt.telegrambot.config.receiver

import kotlin.time.Duration


/**
 * Created on 06.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class LongPollingConfig(

    /** Limits the number of updates to be retrieved. Values between 1-100 are accepted. Defaults to 100. */
    public var limit: Int? = null,

    /** Max available value now is 10 min because of client timeout is 300_000 milliseconds (it will be in config too). Defaults to 30. */
    public var timeout: Int? = null,

    /** Delay between when internal error while long polling. Defaults to 5_000. */
    public var retryDelay: Long? = null,

    /** Delay between when internal error while long polling. Defaults to 5_000. */
    public var gracefulShutdownTimeout: Duration? = null,
)