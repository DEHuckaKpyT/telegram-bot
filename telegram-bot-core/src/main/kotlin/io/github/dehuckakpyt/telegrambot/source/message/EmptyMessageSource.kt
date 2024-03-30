package io.github.dehuckakpyt.telegrambot.source.message


/**
 * Created on 21.08.2023.
 *
 * Implementation for ignoring saving messages.
 *
 * @author Denis Matytsin
 */
class EmptyMessageSource : MessageSource {
    override suspend fun save(chatId: Long, fromId: Long, messageId: Long, type: String, step: String?, text: String?) {}
}