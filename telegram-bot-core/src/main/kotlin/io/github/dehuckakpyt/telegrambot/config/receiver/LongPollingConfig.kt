package io.github.dehuckakpyt.telegrambot.config.receiver


/**
 * Created on 06.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class LongPollingConfig(

    public var limit: Int? = null,

    /** Max available value now is 10 min because of client timeout is 300_000 milliseconds (it will be in config too) */
    public var timeout: Int? = 30,
)