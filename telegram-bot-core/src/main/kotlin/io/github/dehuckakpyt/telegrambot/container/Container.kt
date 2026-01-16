package io.github.dehuckakpyt.telegrambot.container

import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.model.telegram.User


/**
 * @author Denis Matytsin
 */
interface Container {
    val chat: Chat
    val from: User
    val step: String?
    val type: String
}