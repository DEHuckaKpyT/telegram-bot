package io.github.dehuckakpyt.telegrambot.model

import java.util.*


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface CallbackContent {
    val id: UUID
    val chatId: Long
    val fromId: Long
    val content: String
}