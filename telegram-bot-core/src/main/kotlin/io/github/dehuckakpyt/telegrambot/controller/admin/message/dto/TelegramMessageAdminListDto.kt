package io.github.dehuckakpyt.telegrambot.controller.admin.message.dto

import io.github.dehuckakpyt.telegrambot.TelegramBotImpl
import java.time.LocalDateTime
import java.util.*


/**
 * @author Denis Matytsin
 */
data class TelegramMessageAdminListDto(

    val id: UUID,

    /** Which chat was sent message. */
    val chatId: Long,

    /** Who was sent message. */
    val fromId: Long?,

    /** True if bot sent message. */
    val fromBot: Boolean,

    /** Message id. */
    val messageId: Long,

    /** Type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in [TelegramBotImpl]). */
    val type: String,

    /** The step when the message saving. */
    val step: String?,

    /** Type of the container, which process user message. */
    val stepContainerType: String?,

    /** Content of the message. */
    val text: String?,

    /** File ids which are sent. */
    val fileIds: List<String>?,

    /** Date/time when message was sent. */
    val createdAt: LocalDateTime,
)
