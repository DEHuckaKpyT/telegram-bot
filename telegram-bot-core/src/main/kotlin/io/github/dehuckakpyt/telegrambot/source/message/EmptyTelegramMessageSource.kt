package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message


/**
 * Created on 21.08.2023.
 *
 * Implementation for ignoring saving messages.
 *
 * @author Denis Matytsin
 */
internal class EmptyTelegramMessageSource : TelegramMessageSource<TelegramMessage> {
    override suspend fun save(message: Message, fromBot: Boolean, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?) {}
    override suspend fun save(message: Message, fromBot: Boolean, type: String, text: String?, fileIds: List<String>?) {}
}