package io.github.dehuckakpyt.telegrambot.model

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
object TelegramMessages : UUIDTable("telegram_message") {

    val chatId = long("chat_id")
    val fromId = long("from_id").nullable()
    val messageId = long("message_id")
    val text = text("text").nullable()
    val createdDate = datetime("created_date").defaultExpression(CurrentDateTime)
}

class TelegramMessage(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TelegramMessage>(TelegramMessages)

    var chatId by TelegramMessages.chatId
    var fromId by TelegramMessages.fromId
    var messageId by TelegramMessages.messageId
    var text by TelegramMessages.text
    val createdDate by TelegramMessages.createdDate
}