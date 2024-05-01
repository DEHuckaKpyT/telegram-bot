package io.github.dehuckakpyt.telegrambot.source.message


/**
 * Created on 21.08.2023.
 *
 * Implementation for ignoring saving messages.
 *
 * @author Denis Matytsin
 */
class EmptyMessageSource : MessageSource {
    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, text: String?) {}
    override suspend fun save(chatId: Long, fromId: Long, fromBot: Boolean, messageId: Long, type: String, step: String?, stepContainerType: String?, text: String?) {}
}