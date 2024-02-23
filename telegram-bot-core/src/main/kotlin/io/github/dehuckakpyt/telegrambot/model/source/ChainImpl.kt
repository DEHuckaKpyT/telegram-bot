package io.github.dehuckakpyt.telegrambot.model.source


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class ChainImpl(
    override val chatId: Long,
    override val fromId: Long,
    override var step: String? = null,
    override var content: String? = null
) : Chain