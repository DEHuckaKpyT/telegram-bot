package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramMessages : BaseTelegramMessages()

open class DefaultTelegramMessage(id: EntityID<UUID>) : BaseTelegramMessage(id, DefaultTelegramMessages) {
    companion object : UUIDEntityClass<DefaultTelegramMessage>(DefaultTelegramMessages)
}