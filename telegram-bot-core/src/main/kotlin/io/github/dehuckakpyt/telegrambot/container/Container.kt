package io.github.dehuckakpyt.telegrambot.container

import io.github.dehuckakpyt.telegrambot.model.telegram.Chat
import io.github.dehuckakpyt.telegrambot.model.telegram.User


/**
 * Created on 01.05.2024.
 *
 * @author Denis Matytsin
 */
interface Container {
    val chat: Chat
    val from: User
    val step: String?
    val type: String
}