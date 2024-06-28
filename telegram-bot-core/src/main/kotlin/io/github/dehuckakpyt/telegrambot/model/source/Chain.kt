package io.github.dehuckakpyt.telegrambot.model.source


/**
 * Created on 21.08.2023.
 *
 * Model of the info about next chain.
 *
 * @author Denis Matytsin
 */
interface Chain {

    /** In which chat dialog */
    val chatId: Long

    /** With which user dialog (in private chats equals to chatId) */
    val fromId: Long

    /** Next step name */
    val step: String?

    /** Next step stringified object (defaults json from JsonContentConverter) */
    val content: String?
}