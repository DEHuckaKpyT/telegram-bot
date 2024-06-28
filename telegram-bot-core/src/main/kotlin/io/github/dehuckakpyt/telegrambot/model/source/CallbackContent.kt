package io.github.dehuckakpyt.telegrambot.model.source

import java.util.*


/**
 * Created on 21.08.2023.
 *
 * Model of the info about callback content.
 *
 * @author Denis Matytsin
 */
interface CallbackContent {

    /** Id for searching callback content. */
    val callbackId: UUID

    /** Which chat to save content. */
    val chatId: Long

    /** Which userId to save content. */
    val fromId: Long

    /**
     * Content for saving (maybe any format) (default json from JsonContentConverter).
     *
     * @see io.github.dehuckakpyt.telegrambot.converter.JsonContentConverter
     */
    val content: String
}