package io.github.dehuckakpyt.telegrambot.model.source


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface Chain {
    val chatId: Long
    val fromId: Long
    val step: String?
    val content: String?
}