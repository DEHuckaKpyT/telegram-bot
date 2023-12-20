package io.github.dehuckakpyt.telegrambot.config.constans

import io.github.dehuckakpyt.telegrambot.context.DatabaseKoinContext
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
import org.koin.core.definition.Definition
import org.koin.core.qualifier.named


/**
 * Created on 30.11.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface DatabaseSourceFactory {
    val CallbackContentSource.Companion.inDatabase: Definition<CallbackContentSource>
        get() {
            transaction {
                SchemaUtils.createMissingTablesAndColumns(CallbackContents)
            }

            return { DatabaseCallbackContentSource(DatabaseKoinContext.koin.get(named("maxCallbackContentsPerUser"))) }
        }

    val ChainSource.Companion.inDatabase: Definition<ChainSource>
        get() {
            transaction {
                SchemaUtils.createMissingTablesAndColumns(Chains)
            }

            return { DatabaseChainSource() }
        }

    val MessageSource.Companion.inDatabase: Definition<MessageSource>
        get() {
            transaction {
                SchemaUtils.createMissingTablesAndColumns(TelegramMessages)
            }

            return { DatabaseMessageSource() }
        }

    fun TelegramBotConfig.allInDatabase() {
        callbackContentSource = { DatabaseCallbackContentSource(DatabaseKoinContext.koin.get(named("maxCallbackContentsPerUser"))) }
        chainSource = { DatabaseChainSource() }
        messageSource = { DatabaseMessageSource() }

        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                CallbackContents,
                Chains,
                TelegramMessages
            )
        }
    }
}