package io.github.dehuckakpyt.telegrambot.model.message

import jakarta.persistence.Entity
import jakarta.persistence.Table


/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_message")
class DefaultTelegramMessage : BaseTelegramMessage() 
