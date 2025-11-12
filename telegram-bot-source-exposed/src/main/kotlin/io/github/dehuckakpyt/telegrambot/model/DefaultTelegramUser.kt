package io.github.dehuckakpyt.telegrambot.model

import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramUsers : AbstractTelegramUsers()

open class DefaultTelegramUser(id: EntityID<UUID>) : AbstractTelegramUser(id, DefaultTelegramUsers) {
    companion object : UUIDEntityClass<DefaultTelegramUser>(DefaultTelegramUsers)
}
