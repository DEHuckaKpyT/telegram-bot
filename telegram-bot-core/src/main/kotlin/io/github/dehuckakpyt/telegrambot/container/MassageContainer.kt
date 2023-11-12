package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


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
    chainSource: ChainSource,
    contentConverter: ContentConverter,
    bot: TelegramBot,
) : Container(chatId, content, chainSource, contentConverter, bot) {

    override val from = message.from!!
    val messageId get() = message.messageId
    val chat get() = message.chat

    companion object {
        val TEXT = TextMassageContainer::class
        val PHOTO = PhotoMassageContainer::class
        val AUDIO = AudioMassageContainer::class
        val VOICE = VoiceMessageContainer::class
        val CONTACT = ContactMessageContainer::class
        val DOCUMENT = DocumentMessageContainer::class
    }
}