package io.github.dehuckakpyt.telegrambot.config

import io.github.dehuckakpyt.telegrambot.constant.Default.MAX_CALLBACK_CONTENTS_PER_USER
import io.github.dehuckakpyt.telegrambot.model.CallbackContents
import io.github.dehuckakpyt.telegrambot.model.Chains
import io.github.dehuckakpyt.telegrambot.model.TelegramMessages
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.DatabaseCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.DatabaseChainSource
import io.github.dehuckakpyt.telegrambot.source.message.DatabaseMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
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
    maxCallbackContentsPerUser: Long = MAX_CALLBACK_CONTENTS_PER_USER,
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

        return DatabaseCallbackContentSource(MAX_CALLBACK_CONTENTS_PER_USER)
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
