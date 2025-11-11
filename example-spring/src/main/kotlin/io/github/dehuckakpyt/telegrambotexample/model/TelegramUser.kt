package io.github.dehuckakpyt.telegrambotexample.model

import io.github.dehuckakpyt.telegrambot.model.user.BaseTelegramUser
import jakarta.persistence.Entity


/**
 * @author Denis Matytsin
 */
@Entity
class TelegramUser : BaseTelegramUser() {

    var phone: String? = null
}