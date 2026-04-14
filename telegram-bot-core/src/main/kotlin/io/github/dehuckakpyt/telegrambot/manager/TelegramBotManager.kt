package io.github.dehuckakpyt.telegrambot.manager

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.config.edit.commands.CommandsSource.CODE
import io.github.dehuckakpyt.telegrambot.config.edit.commands.CommandsSource.CONFIG
import io.github.dehuckakpyt.telegrambot.config.edit.commands.EditCommandsConfig
import io.github.dehuckakpyt.telegrambot.holder.command.CommandDescriptionsHolder
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommand
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommandScopeDefault
import io.github.dehuckakpyt.telegrambot.receiver.UpdateReceiver
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

/**
 * @author Denis Matytsin
 */
public class TelegramBotManager internal constructor(
    private val bot: TelegramBot,
    private val updateReceiver: UpdateReceiver,
    private val editCommandsConfig: EditCommandsConfig,
    // It creates only if telegram-bot.edit.commands.source=code'
    private val commandDescriptionsHolder: CommandDescriptionsHolder?,
) {
    private val logger = LoggerFactory.getLogger(TelegramBotManager::class.java)

    public fun start() {
        onStart()
        updateReceiver.start()
    }

    public fun stop() {
        updateReceiver.stop()
    }

    private fun onStart() {
        val botCommands = when (editCommandsConfig.source) {
            CODE   -> {
                val commands = commandDescriptionsHolder?.botCommands ?: return // must be not null
                if (commands.isEmpty()) throw IllegalStateException("Property 'telegram-bot.edit.commands.source' is 'code', but no commands with description registered.")
                commands
            }

            CONFIG -> {
                val descriptionsByCommand = editCommandsConfig.definition ?: throw IllegalStateException("Property 'telegram-bot.edit.commands.source' is 'config', but no commands in 'telegram-bot.edit.commands.definition'.")
                descriptionsByCommand.map { (command, description) -> BotCommand(command, description) }
            }

            null   -> return
        }
        runBlocking {
            bot.setMyCommands(botCommands, BotCommandScopeDefault())
        }
        logger.debug("Commands registered: {}", botCommands)
    }
}
