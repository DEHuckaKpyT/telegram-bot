package io.github.dehuckakpyt.telegrambot.model.user

import jakarta.persistence.Entity
import jakarta.persistence.Table


/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_user")
class DefaultTelegramUser : BaseTelegramUser()