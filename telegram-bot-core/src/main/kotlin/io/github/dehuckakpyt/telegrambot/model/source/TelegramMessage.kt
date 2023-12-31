package io.github.dehuckakpyt.telegrambot.model.source

import java.time.LocalDateTime


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramMessage {
    val chatId: Long
    val fromId: Long
    val messageId: Long
    val type: String
    val step: String?
    val text: String?
    val createDate: LocalDateTime
}