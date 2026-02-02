package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramMessages : BaseTelegramMessages()

open class DefaultTelegramMessage(id: EntityID<UUID>) : BaseTelegramMessage(id, DefaultTelegramMessages) {
    companion object : UUIDEntityClass<DefaultTelegramMessage>(DefaultTelegramMessages)
}