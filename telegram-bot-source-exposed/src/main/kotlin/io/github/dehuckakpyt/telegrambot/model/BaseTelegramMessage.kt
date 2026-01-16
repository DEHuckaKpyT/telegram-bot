package io.github.dehuckakpyt.telegrambot.model

import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import org.jetbrains.exposed.dao.UUIDEntity
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
open class BaseTelegramMessages : UUIDTable("telegram_message") {

    val chatId = long("chat_id")
    val fromId = long("from_id").nullable()
    val fromBot = bool("from_bot")
    val messageId = long("message_id")
    val type = varchar("type", 255)
    val step = varchar("step", 255).nullable()
    val stepContainerType = varchar("step_container_type", 255).nullable()
    val text = text("text").nullable()
    val fileIds = array("file_ids", TextColumnType()).nullable()
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
}

open class BaseTelegramMessage(id: EntityID<UUID>, table: BaseTelegramMessages) : UUIDEntity(id), TelegramMessage<EntityID<UUID>> {

    override var chatId by table.chatId
    override var fromId by table.fromId
    override var fromBot by table.fromBot
    override var messageId by table.messageId
    override var type by table.type
    override var step by table.step
    override var stepContainerType by table.stepContainerType
    override var text by table.text
    override var fileIds by table.fileIds
    override val createdAt by table.createdAt
}