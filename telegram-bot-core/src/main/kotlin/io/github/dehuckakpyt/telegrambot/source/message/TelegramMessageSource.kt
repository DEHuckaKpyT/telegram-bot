package io.github.dehuckakpyt.telegrambot.source.message

import io.github.dehuckakpyt.telegrambot.ext.context.currentContainerContextOrNull
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.Message


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
interface TelegramMessageSource<EntityT : TelegramMessage> {

    /**
     * Save message.
     *
     * @param message sent message
     * @param fromBot true if bot sent message
     * @param type type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in TelegramBotImpl)
     * @param step the step when the message saving
     * @param stepContainerType type of the container, which process user message
     * @param text content of the message
     */
    suspend fun save(
        message: Message,
        fromBot: Boolean,
        type: String,
        step: String? = null,
        stepContainerType: String? = null,
        text: String? = null,
        fileIds: List<String>? = null,
    )

    /**
     * Save message.
     *
     * @param message sent message
     * @param fromBot true if bot sent message
     * @param type type of the message (like 'TEXT', 'PHOTO', 'AUDIO') (can find all in TelegramBotImpl)
     * @param text content of the message
     */
    suspend fun save(
        message: Message,
        fromBot: Boolean,
        type: String,
        text: String? = null,
        fileIds: List<String>? = null,
    ) {
        val currentContainer = currentContainerContextOrNull()

        save(
            message = message,
            fromBot = fromBot,
            type = type,
            step = currentContainer?.step,
            stepContainerType = currentContainer?.type,
            text = text,
            fileIds = fileIds
        )
    }

    companion object
}