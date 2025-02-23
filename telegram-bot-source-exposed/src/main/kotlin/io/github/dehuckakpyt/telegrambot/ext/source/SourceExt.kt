package io.github.dehuckakpyt.telegrambot.ext.source

import io.github.dehuckakpyt.telegrambot.config.constants.source.callback.CallbackContentSourceConstant.CALLBACK_CONTENT_SOURCE_MAX_CONTENTS_PER_USER
import io.github.dehuckakpyt.telegrambot.model.*
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.DatabaseCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.DatabaseChainSource
import io.github.dehuckakpyt.telegrambot.source.chat.DatabaseTelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.TelegramChatSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.DatabaseTelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.chat.event.TelegramChatStatusEventSource
import io.github.dehuckakpyt.telegrambot.source.message.DatabaseMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.source.user.DatabaseTelegramUserSource
import io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
fun CallbackContentSource.Companion.inDatabase(
    /**
     * Максимальное количество записей с содержанием callback'а для одного пользователя.
     * -1 для игнорирования ограничения.
     * ВАЖНО: эта проперть должна быть задана раньше, чем callbackContentSource
     */
    maxCallbackContentsPerUser: Long = CALLBACK_CONTENT_SOURCE_MAX_CONTENTS_PER_USER,
): CallbackContentSource {
    transaction {
        SchemaUtils.createMissingTablesAndColumns(CallbackContents)
    }

    return DatabaseCallbackContentSource(maxCallbackContentsPerUser)
}

val CallbackContentSource.Companion.inDatabase: CallbackContentSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(CallbackContents)
        }

        return DatabaseCallbackContentSource(CALLBACK_CONTENT_SOURCE_MAX_CONTENTS_PER_USER)
    }

val ChainSource.Companion.inDatabase: ChainSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(Chains)
        }

        return DatabaseChainSource()
    }

val MessageSource.Companion.inDatabase: MessageSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(TelegramMessages)
        }

        return DatabaseMessageSource()
    }

val TelegramUserSource.Companion.inDatabase: TelegramUserSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(TelegramUsers)
        }

        return DatabaseTelegramUserSource()
    }

val TelegramChatSource.Companion.inDatabase: TelegramChatSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(TelegramChats)
        }

        return DatabaseTelegramChatSource()
    }

val TelegramChatStatusEventSource.Companion.inDatabase: TelegramChatStatusEventSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(TelegramChatStatusEvents)
        }

        return DatabaseTelegramChatStatusEventSource()
    }
