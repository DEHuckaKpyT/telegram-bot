package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.Message
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class PhotoMassageContainer(
    chatId: Long,
    private val message: Message,
    content: String?,
    chainSource: ChainSource,
    bot: TelegramBot,
) : MassageContainer(chatId, message, content, chainSource, bot) {

    val caption get() = message.caption
    val photos get() = message.photo
}