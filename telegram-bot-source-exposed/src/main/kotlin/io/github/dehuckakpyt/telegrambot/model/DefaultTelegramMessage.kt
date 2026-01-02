package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramMessages : AbstractTelegramMessages()

open class DefaultTelegramMessage(id: EntityID<UUID>) : AbstractTelegramMessage(id, DefaultTelegramMessages) {
    companion object : UUIDEntityClass<DefaultTelegramMessage>(DefaultTelegramMessages)
}