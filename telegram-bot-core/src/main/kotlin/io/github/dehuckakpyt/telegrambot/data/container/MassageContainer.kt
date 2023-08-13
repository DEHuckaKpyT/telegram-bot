package io.github.dehuckakpyt.telegrambot.data.container

import com.elbekd.bot.types.Message


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
abstract class MassageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
) : Container(chatId, content) {

    val messageId get() = message.messageId
    val chat get() = message.chat
    val from get() = message.from

    companion object {
        val TEXT = TextMassageContainer::class
        val PHOTO = PhotoMassageContainer::class
        val AUDIO = AudioMassageContainer::class
    }
}