package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramChats : BaseTelegramChats()

open class DefaultTelegramChat(id: EntityID<UUID>) : BaseTelegramChat(id, DefaultTelegramChats) {
    companion object : UUIDEntityClass<DefaultTelegramChat>(DefaultTelegramChats)
}
