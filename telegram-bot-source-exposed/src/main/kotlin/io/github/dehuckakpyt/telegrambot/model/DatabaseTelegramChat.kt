package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramChat
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
object TelegramChats : UUIDTable("telegram_chat") {

    val chatId = long("chat_id").uniqueIndex()
    val type = varchar("type", 255)
    val title = varchar("title", 255)
    val username = varchar("username", 255).nullable()
    val available = bool("available")
    val updateDate = datetime("update_date").defaultExpression(CurrentDateTime)
    val createDate = datetime("create_date").defaultExpression(CurrentDateTime)
}

class DatabaseTelegramChat(id: EntityID<UUID>) : UUIDEntity(id), TelegramChat {
    companion object : UUIDEntityClass<DatabaseTelegramChat>(TelegramChats)

    override var chatId by TelegramChats.chatId
    override var type by TelegramChats.type
    override var title by TelegramChats.title
    override var username by TelegramChats.username
    override var available by TelegramChats.available
    override var updateDate by TelegramChats.updateDate
    override var createDate by TelegramChats.createDate
}
