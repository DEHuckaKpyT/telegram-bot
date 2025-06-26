package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.TextColumnType
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
    val fromBot = bool("from_bot")
    val messageId = long("message_id")
    val type = varchar("type", 255)
    val step = varchar("step", 255).nullable()
    val stepContainerType = varchar("step_container_type", 255).nullable()
    val text = text("text").nullable()
    val fileIds = array<String>("file_ids", TextColumnType(), maximumCardinality = 10).nullable()
    val createdDate = datetime("created_date").defaultExpression(CurrentDateTime)
}

class DatabaseTelegramMessage(id: EntityID<UUID>) : UUIDEntity(id), TelegramMessage {
    companion object : UUIDEntityClass<DatabaseTelegramMessage>(TelegramMessages)

    override var chatId by TelegramMessages.chatId
    override var fromId by TelegramMessages.fromId
    override var fromBot by TelegramMessages.fromBot
    override var messageId by TelegramMessages.messageId
    override var type by TelegramMessages.type
    override var step by TelegramMessages.step
    override var stepContainerType by TelegramMessages.stepContainerType
    override var text by TelegramMessages.text
    override var fileIds by TelegramMessages.fileIds
    override val createDate by TelegramMessages.createdDate
}