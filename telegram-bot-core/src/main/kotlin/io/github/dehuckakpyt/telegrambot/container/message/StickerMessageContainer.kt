package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.Sticker


/**
 * Created on 09.12.2024.
 *
 * @author Denis Matytsin
 */
class StickerMessageContainer(message: Message, step: String?, content: String?) :
    MessageContainer(message, step, content) {

    val sticker: Sticker get() = message.sticker!!

    override val type: String = "STICKER"
}