package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.java.UUIDEntityClass
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramUsers : BaseTelegramUsers()

open class DefaultTelegramUser(id: EntityID<UUID>) : BaseTelegramUser(id, DefaultTelegramUsers) {
    companion object : UUIDEntityClass<DefaultTelegramUser>(DefaultTelegramUsers)
}
