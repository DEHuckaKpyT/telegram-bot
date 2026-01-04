package io.github.dehuckakpyt.telegrambot.model.chatevent

import jakarta.persistence.Entity
import jakarta.persistence.Table


/**
 * @author Denis Matytsin
 */
@Entity
@Table(name = "telegram_chat_status_event")
class DefaultTelegramChatStatusEvent : BaseTelegramChatStatusEvent()
