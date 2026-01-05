package io.github.dehuckakpyt.telegrambot.source.chain

import io.github.dehuckakpyt.telegrambot.model.source.Chain
import io.github.dehuckakpyt.telegrambot.model.telegram.Update


/**
 * Interface for saving the state of the dialog between the user and the bot.
 * Dialogs are separated by chat and by the user who wrote the message.
 *
 * This means that one user will have different dialogs in different chats.
 * And it means that in a group chat each user will have a different dialog.
 *
 * IMPORTANT: If [Update.message] without from.id, it will be ignored in any dialogs (BotHandling).
 *
 * @author Denis Matytsin
 */
interface ChainSource {

    /**
     * Set the next step in the dialog.
     *
     * @param chatId which chat to set step
     * @param fromId which user in chat to set step (in private chats equals to chatId)
     * @param step name of the next step for selected dialog
     * @param content stringified object for transfer (defaults json from JsonContentConverter)
     *
     * @see io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
     */
    suspend fun save(chatId: Long, fromId: Long, step: String?, content: String?)

    /**
     * Get the next step info by chat and by user.
     *
     * @param chatId which chat to get step
     * @param fromId which user in chat to get step (in private chats equals to chatId)
     *
     * @return object with info about the step.
     */
    suspend fun get(chatId: Long, fromId: Long): Chain?

    companion object
}