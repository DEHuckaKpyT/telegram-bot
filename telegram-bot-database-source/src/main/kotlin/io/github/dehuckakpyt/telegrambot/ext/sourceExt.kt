package io.github.dehuckakpyt.telegrambot.ext

import io.github.dehuckakpyt.telegrambot.model.CallbackContents
import io.github.dehuckakpyt.telegrambot.model.Chains
import io.github.dehuckakpyt.telegrambot.model.TelegramMessages
import io.github.dehuckakpyt.telegrambot.plugin.config.TelegramBotConfig
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.callback.DatabaseCallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.source.chain.DatabaseChainSource
import io.github.dehuckakpyt.telegrambot.source.message.DatabaseMessageSource
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
val CallbackContentSource.Companion.inDatabase: CallbackContentSource
    get() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(CallbackContents)
        }

        return DatabaseCallbackContentSource()
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

fun TelegramBotConfig.databaseSources() {
    callbackContentSource = DatabaseCallbackContentSource()
    chainSource = DatabaseChainSource()
    messageSource = DatabaseMessageSource()

    transaction {
        SchemaUtils.createMissingTablesAndColumns(
            CallbackContents,
            Chains,
            TelegramMessages
        )
    }
}
