package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.container.GeneralContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class MessageContainer(val message: Message, step: String?, content: String?) :
    GeneralContainer(step, content) {

    val messageId get() = message.messageId
    override val from get() = message.from!!
    override val chat get() = message.chat
}