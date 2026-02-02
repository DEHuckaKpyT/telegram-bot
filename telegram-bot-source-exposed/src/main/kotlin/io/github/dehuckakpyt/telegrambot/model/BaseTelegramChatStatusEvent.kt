package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChatStatusEvent
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.dao.java.UUIDEntity
import org.jetbrains.exposed.v1.javatime.CurrentDateTime
import org.jetbrains.exposed.v1.javatime.datetime
import java.util.*


/**
 * @author Denis Matytsin
 */
open class BaseTelegramChatStatusEvents : UUIDTable("telegram_chat_status_event") {

    val chatId = long("chat_id")
    val title = varchar("title", 255).nullable()
    val username = varchar("username", 255).nullable()
    val firstName = varchar("first_name", 255).nullable()
    val lastName = varchar("last_name", 255).nullable()
    val status = varchar("status", 255)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

open class BaseTelegramChatStatusEvent(id: EntityID<UUID>, table: BaseTelegramChatStatusEvents) : UUIDEntity(id), TelegramChatStatusEvent<EntityID<UUID>> {

    override var chatId by table.chatId
    override var title by table.title
    override var username by table.username
    override var firstName by table.firstName
    override var lastName by table.lastName
    override var status by table.status
    override val createdAt by table.createdAt
}