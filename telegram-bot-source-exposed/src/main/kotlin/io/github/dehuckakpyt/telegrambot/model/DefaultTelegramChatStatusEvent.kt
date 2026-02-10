package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramChatStatusEvents : BaseTelegramChatStatusEvents()

open class DefaultTelegramChatStatusEvent(id: EntityID<UUID>) : BaseTelegramChatStatusEvent(id, DefaultTelegramChatStatusEvents) {
    companion object : UUIDEntityClass<DefaultTelegramChatStatusEvent>(DefaultTelegramChatStatusEvents)
}