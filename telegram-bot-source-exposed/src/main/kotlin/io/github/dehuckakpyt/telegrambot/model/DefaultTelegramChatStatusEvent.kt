package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramChatStatusEvents : AbstractTelegramChatStatusEvents()

open class DefaultTelegramChatStatusEvent(id: EntityID<UUID>) : AbstractTelegramChatStatusEvent(id, DefaultTelegramChatStatusEvents) {
    companion object : UUIDEntityClass<DefaultTelegramChatStatusEvent>(DefaultTelegramChatStatusEvents)
}