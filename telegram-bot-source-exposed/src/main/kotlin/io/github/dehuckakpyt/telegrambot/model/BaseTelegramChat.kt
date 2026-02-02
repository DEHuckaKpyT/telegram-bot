package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
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
open class BaseTelegramChats : UUIDTable("telegram_chat") {

    val chatId = long("chat_id").uniqueIndex()
    val type = varchar("type", 255)
    val title = varchar("title", 255).nullable()
    val username = varchar("username", 255).nullable()
    val available = bool("available")
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

open class BaseTelegramChat(id: EntityID<UUID>, table: BaseTelegramChats) : UUIDEntity(id), TelegramChat<EntityID<UUID>> {

    override var chatId by table.chatId
    override var type by table.type
    override var title by table.title
    override var username by table.username
    override var available by table.available
    override var updatedAt by table.updatedAt
    override var createdAt by table.createdAt
}
