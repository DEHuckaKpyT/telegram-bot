package io.github.dehuckakpyt.telegrambot.plugin.config.receiver


/**
 * Created on 06.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
data class LongPollingConfig(
    public var limit: Int? = null,
    public var timeout: Int? = 30,
)