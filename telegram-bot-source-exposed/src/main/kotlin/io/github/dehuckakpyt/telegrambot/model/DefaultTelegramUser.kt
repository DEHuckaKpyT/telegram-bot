package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.*


/**
 * @author Denis Matytsin
 */
object DefaultTelegramUsers : UUIDTable("telegram_user") {

    val userId = long("user_id").uniqueIndex()
    val username = varchar("username", 255).nullable()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255).nullable()
    val languageCode = varchar("language_code", 255).nullable()
    val available = bool("available")
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

open class DefaultTelegramUser(id: EntityID<UUID>) : UUIDEntity(id), TelegramUser<EntityID<UUID>> {
    companion object : UUIDEntityClass<DefaultTelegramUser>(DefaultTelegramUsers)

    override var userId by DefaultTelegramUsers.userId
    override var username by DefaultTelegramUsers.username
    override var firstName by DefaultTelegramUsers.firstName
    override var lastName by DefaultTelegramUsers.lastName
    override var languageCode by DefaultTelegramUsers.languageCode
    override var available by DefaultTelegramUsers.available
    override var updatedAt by DefaultTelegramUsers.updatedAt
    override var createdAt by DefaultTelegramUsers.createdAt
}
