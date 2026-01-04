package io.github.dehuckakpyt.telegrambot.model.chat

import jakarta.persistence.Entity
import jakarta.persistence.Table

/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_chat")
class DefaultTelegramChat : BaseTelegramChat()