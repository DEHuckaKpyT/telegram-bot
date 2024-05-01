package io.github.dehuckakpyt.telegrambot.model.source

import java.time.LocalDateTime


/**
 * Created on 21.08.2023.
 *
 * Model of the info about sent or received message.
 *
 * @author Denis Matytsin
 */
interface TelegramMessage {

    /** Which chat was sent message. */
    val chatId: Long

    /** Who was sent message. */
    val fromId: Long

    /** True if bot sent message. */
    val fromBot: Boolean

    /** Message id. */
    val messageId: Long

    /**
     * Type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in TelegramBotImpl).
     *
     * @see io.github.dehuckakpyt.telegrambot.TelegramBotImpl
     */
    val type: String

    /** The step when the message saving. */
    val step: String?

    /** Type of the container, which process user message. */
    val stepContainerType: String?

    /** Content of the message. */
    val text: String?

    /** Date/time when message was sent. */
    val createDate: LocalDateTime
}