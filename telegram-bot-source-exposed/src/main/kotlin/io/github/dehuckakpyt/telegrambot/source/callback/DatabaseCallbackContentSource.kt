package io.github.dehuckakpyt.telegrambot.source.callback

import com.dehucka.exposed.ext.executeQuery
import com.dehucka.exposed.ext.readQuery
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.model.CallbackContents
import io.github.dehuckakpyt.telegrambot.model.DatabaseCallbackContent
import io.github.dehuckakpyt.telegrambot.model.source.CallbackContent
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import java.time.LocalDateTime
import java.util.*

class DatabaseCallbackContentSource(
    private val maxCallbackContentsPerUser: Long,
) : CallbackContentSource {

    override suspend fun save(chatId: Long, fromId: Long, content: String): CallbackContent = executeQuery {
        findLast(chatId, fromId).createOrUpdate {
            this.chatId = chatId
            this.fromId = fromId
            this.callbackId = UUID.randomUUID()
            this.content = content
            this.updateDate = LocalDateTime.now()
        }
    }

    override suspend fun get(callbackId: UUID): CallbackContent = readQuery {
        DatabaseCallbackContent.find(CallbackContents.callbackId eq callbackId)
            .firstOrNull()
            ?: throw ChatException("Содержание для callback'а не найдено :(")
    }

    private fun findLast(chatId: Long, fromId: Long): DatabaseCallbackContent? {
        if (maxCallbackContentsPerUser < 1) return null

        return DatabaseCallbackContent.find {
            (CallbackContents.chatId eq chatId) and (CallbackContents.fromId eq fromId)
        }.orderBy(CallbackContents.updateDate to SortOrder.DESC)
            .limit(1, maxCallbackContentsPerUser - 1)
            .firstOrNull()
    }

    private fun DatabaseCallbackContent?.createOrUpdate(block: DatabaseCallbackContent.() -> Unit): DatabaseCallbackContent {
        return this?.apply(block) ?: DatabaseCallbackContent.new(block)
    }
}