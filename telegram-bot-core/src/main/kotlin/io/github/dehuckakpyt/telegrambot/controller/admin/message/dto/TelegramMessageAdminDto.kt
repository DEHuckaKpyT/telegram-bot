package io.github.dehuckakpyt.telegrambot.controller.admin.message.dto

import io.github.dehuckakpyt.telegrambot.TelegramBotImpl
import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import java.time.LocalDateTime
import java.util.*


/**
 * @author Denis Matytsin
 */
data class TelegramMessageAdminDto(

    val id: UUID,

    /** Which chat was sent message (from [Message.chat]). */
    val chatId: Long,

    /** Who was sent message (from [Message.from]). */
    val fromId: Long?,

    /** True if bot sent message. */
    val fromBot: Boolean,

    /** Message id (from [Message.messageId]). */
    val messageId: Long,

    /** Type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in [TelegramBotImpl]). */
    val type: String,

    /** The step when the message saving (from [Container]). */
    val step: String?,

    /** Type of the container, which process user message (from [Container]). */
    val stepContainerType: String?,

    /** Content of the message. */
    val text: String?,

    /** File ids which are sent. */
    val fileIds: List<String>?,

    /** Date/time when message was sent. */
    val createdAt: LocalDateTime,
)
