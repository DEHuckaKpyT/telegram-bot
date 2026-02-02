package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramChats : BaseTelegramChats()

open class DefaultTelegramChat(id: EntityID<UUID>) : BaseTelegramChat(id, DefaultTelegramChats) {
    companion object : UUIDEntityClass<DefaultTelegramChat>(DefaultTelegramChats)
}
