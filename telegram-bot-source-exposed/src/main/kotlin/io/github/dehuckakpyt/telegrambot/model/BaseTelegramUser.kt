package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramUser
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.dao.java.UUIDEntity
import org.jetbrains.exposed.v1.javatime.CurrentDateTime
import org.jetbrains.exposed.v1.javatime.datetime
import java.util.*


/**
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
open class BaseTelegramUsers(name: String = "telegram_user") : UUIDTable(name) {

    val userId = long("user_id").uniqueIndex()
    val username = varchar("username", 255).nullable()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255).nullable()
    val languageCode = varchar("language_code", 255).nullable()
    val available = bool("available")
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

open class BaseTelegramUser(id: EntityID<UUID>, table: BaseTelegramUsers) : UUIDEntity(id), TelegramUser<EntityID<UUID>> {

    override var userId by table.userId
    override var username by table.username
    override var firstName by table.firstName
    override var lastName by table.lastName
    override var languageCode by table.languageCode
    override var available by table.available
    override var updatedAt by table.updatedAt
    override var createdAt by table.createdAt

    companion object
}
