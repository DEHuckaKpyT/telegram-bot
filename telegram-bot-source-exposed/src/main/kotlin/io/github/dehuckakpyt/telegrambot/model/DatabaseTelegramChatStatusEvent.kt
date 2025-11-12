package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
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
object TelegramChatStatusEvents : UUIDTable("telegram_chat_status_event") {

    val chatId = long("chat_id")
    val title = varchar("title", 255).nullable()
    val username = varchar("username", 255).nullable()
    val firstName = varchar("first_name", 255).nullable()
    val lastName = varchar("last_name", 255).nullable()
    val status = varchar("status", 255)
    val createDate = datetime("create_date").defaultExpression(CurrentDateTime)
}

class DatabaseTelegramChatStatusEvent(id: EntityID<UUID>) : UUIDEntity(id), TelegramChatStatusEvent {
    companion object : UUIDEntityClass<DatabaseTelegramChatStatusEvent>(TelegramChatStatusEvents)

    override var chatId by TelegramChatStatusEvents.chatId
    override var title by TelegramChatStatusEvents.title
    override var username by TelegramChatStatusEvents.username
    override var firstName by TelegramChatStatusEvents.firstName
    override var lastName by TelegramChatStatusEvents.lastName
    override var status by TelegramChatStatusEvents.status
    override val createdAt by TelegramChatStatusEvents.createDate
}