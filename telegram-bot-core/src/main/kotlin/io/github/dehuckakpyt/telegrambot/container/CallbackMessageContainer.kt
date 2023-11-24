package io.github.dehuckakpyt.telegrambot.container

import com.elbekd.bot.types.CallbackQuery
import com.elbekd.bot.types.User
import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource


/**
 * Created on 13.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class CallbackMessageContainer(
    chatId: Long,
    private val query: CallbackQuery,
    content: String?,
    chainSource: ChainSource,
    contentConverter: ContentConverter,
    bot: TelegramBot,
) : Container(chatId, content, chainSource, contentConverter, bot) {

    override val from: User = query.from
    val message get() = query.message
    val inlineMessageId get() = query.inlineMessageId
}