package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup


/**
 * Created on 21.08.2023.
 *
 * Implementation for ignoring saving messages.
 *
 * @author Denis Matytsin
 */
class EmptyMessageSource : MessageSource {
    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?, replyMarkup: InlineKeyboardMarkup?) {}
    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, text: String?, fileIds: List<String>?, replyMarkup: InlineKeyboardMarkup?) {}
}