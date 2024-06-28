package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.ext.context.currentContainerContextOrNull


/**
 * Created on 20.07.2023.
 *
 * Interface for saving messages only.
 *
 * Maybe useful for restore dialogs with users.
 * Maybe useful for collect statistics about the steps the user was able to reach in a long chain.
 * Maybe useful for getting most popular commands/steps.
 *
 * You can implement this interface and filter saving message or/and add a methods for collecting statistics.
 *
 * @author Denis Matytsin
 */
interface MessageSource {

    /**
     * Save message.
     *
     * @param chatId from which chat
     * @param fromId from which user (in private chats equals to chatId)
     * @param fromBot true if bot sent message
     * @param messageId
     * @param type type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in TelegramBotImpl)
     * @param step the step when the message saving
     * @param stepContainerType type of the container, which process user message
     * @param text content of the message
     *
     * @see io.github.dehuckakpyt.telegrambot.TelegramBotImpl
     */
    suspend fun save(
        chatId: Long,
        fromId: Long,
        fromBot: Boolean,
        messageId: Long,
        type: String,
        step: String? = null,
        stepContainerType: String? = null,
        text: String? = null,
        fileIds: List<String>? = null,
    )

    /**
     * Save message.
     *
     * @param chatId from which chat
     * @param fromId from which user (in private chats equals to chatId)
     * @param fromBot true if bot sent message
     * @param messageId
     * @param type type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in TelegramBotImpl)
     * @param step the step when the message saving
     * @param stepContainerType type of the container, which process user message
     * @param text content of the message
     *
     * @see io.github.dehuckakpyt.telegrambot.TelegramBotImpl
     */
    suspend fun save(
        chatId: Long,
        fromId: Long,
        fromBot: Boolean,
        messageId: Long,
        type: String,
        text: String? = null,
        fileIds: List<String>? = null,
    ) {
        val currentContainer = currentContainerContextOrNull()

        save(
            chatId = chatId,
            fromId = fromId,
            fromBot = fromBot,
            messageId = messageId,
            type = type,
            step = currentContainer?.step,
            stepContainerType = currentContainer?.type,
            text = text,
            fileIds = fileIds
        )
    }

    companion object
}