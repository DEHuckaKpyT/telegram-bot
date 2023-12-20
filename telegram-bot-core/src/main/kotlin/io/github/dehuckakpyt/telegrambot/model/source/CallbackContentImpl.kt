package io.github.dehuckakpyt.telegrambot.model.source

import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackContentImpl(
    override val id: UUID,
    override val chatId: Long,
    override val fromId: Long,
    override val content: String
) : CallbackContent