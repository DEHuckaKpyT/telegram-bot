package io.github.dehuckakpyt.telegrambot.container.message.factory

import io.github.dehuckakpyt.telegrambot.container.message.MessageContainer
import io.github.dehuckakpyt.telegrambot.container.message.MessageType
import io.github.dehuckakpyt.telegrambot.container.message.StickerMessageContainer
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import kotlin.reflect.KClass


/**
 * Created on 09.12.2024.
 *
 * @author Denis Matytsin
 */
class StickerMessageContainerFactory : MessageContainerFactory {

    override fun matches(message: Message): Boolean = message.sticker != null

    override fun create(message: Message, step: String?, content: String?): MessageContainer =
        StickerMessageContainer(message, step, content)

    override fun getMessageText(message: Message): String? = null

    override fun getMessageFileIds(message: Message): List<String> = listOf(message.sticker!!.fileId)

    override val type: KClass<out MessageContainer> = MessageType.STICKER

    override val messageType: String = "STICKER"
}
