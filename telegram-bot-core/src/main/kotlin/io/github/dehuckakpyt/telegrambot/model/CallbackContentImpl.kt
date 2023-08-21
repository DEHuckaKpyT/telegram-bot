package io.github.dehuckakpyt.telegrambot.model

import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackContentImpl(
    override val identifier: UUID,
    override val content: String
) : CallbackContent