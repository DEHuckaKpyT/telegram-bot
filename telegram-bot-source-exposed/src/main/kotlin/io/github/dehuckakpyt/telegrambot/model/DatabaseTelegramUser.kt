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
 * Created on 20.07.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object TelegramUsers : UUIDTable("telegram_user") {

    val userId = long("user_id").uniqueIndex()
    val username = varchar("username", 255).nullable()
    val firstName = varchar("first_name", 255)
    val lastName = varchar("last_name", 255).nullable()
    val languageCode = varchar("language_code", 255).nullable()
    val available = bool("available")
    val updateDate = datetime("update_date").defaultExpression(CurrentDateTime)
    val createDate = datetime("create_date").defaultExpression(CurrentDateTime)
}

class DatabaseTelegramUser(id: EntityID<UUID>) : UUIDEntity(id), TelegramUser {
    companion object : UUIDEntityClass<DatabaseTelegramUser>(TelegramUsers)

    override var userId by TelegramUsers.userId
    override var username by TelegramUsers.username
    override var firstName by TelegramUsers.firstName
    override var lastName by TelegramUsers.lastName
    override var languageCode by TelegramUsers.languageCode
    override var available by TelegramUsers.available
    override var updateDate by TelegramUsers.updateDate
    override var createDate by TelegramUsers.createDate
}
