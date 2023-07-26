package com.dehucka.telegrambot.source.message

import com.dehucka.telegrambot.model.TelegramMessage


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface MessageSource {

    suspend fun save(chatId: Long, fromId: Long?, messageId: Long, text: String?): TelegramMessage
}