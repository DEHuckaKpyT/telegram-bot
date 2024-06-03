package io.github.dehuckakpyt.telegrambot.factory.button

import io.github.dehuckakpyt.telegrambot.container.Container
import io.github.dehuckakpyt.telegrambot.ext.container.chatId
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardButton
import io.github.dehuckakpyt.telegrambot.model.telegram.KeyboardButton


/**
 * Created on 25.12.2023.
 *
 * Factory for creating buttons.
 *
 * @author Denis Matytsin
 */
interface ButtonFactory {

    /**
     * Create button with callback data.
     *
     * @param text text on the button
     * @param next name of the callback handler
     *
     * @return inline button
     */
    fun callbackButton(text: String, next: String): InlineKeyboardButton

    /**
     * Create button with callback data.
     *
     * @param text text on the button
     * @param next name of the callback handler
     * @param content any object for transfer to the callback handler
     *
     * @return inline button
     */
    suspend fun Container.callbackButton(text: String, next: String, content: Any): InlineKeyboardButton =
        callbackButton(chatId, from.id, text, next, content)

    /**
     * Create button with callback data.
     *
     * @param chatId for which chat
     * @param fromId for which user (in private chats equals to chatId)
     * @param text text on the button
     * @param next name of the callback handler
     * @param content any object for transfer to the callback handler
     *
     * @return inline button
     */
    suspend fun callbackButton(chatId: Long, fromId: Long, text: String, next: String, content: Any): InlineKeyboardButton

    /**
     * Create button with contact request.
     *
     * @param text text on the button
     *
     * @return button
     */
    fun contactButton(text: String): KeyboardButton = KeyboardButton(text, requestContact = true)

    /**
     * Create button with login url (auth).
     *
     * @param text text on the button
     * @param authUrl url for authenticate user
     * @param requestWriteAccess flag for request write access
     *
     * @return inline button
     */
    suspend fun authButton(text: String, authUrl: String, requestWriteAccess: Boolean = false): InlineKeyboardButton
}