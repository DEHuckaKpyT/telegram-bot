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
 * Main lifecycle manager for telegram bot runtime.
 *
 * It is responsible for:
 * - startup/shutdown of updates receiver
 * - one-time startup edits (for example, command menu registration)
 *
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

    /** Starts bot lifecycle: applies startup edits and then starts updates receiving. */
    public fun start() {
        onStart()
        updateReceiver.start()
    }

    /** Stops updates receiving. */
    public fun stop() {
        updateReceiver.stop()
    }

    private fun onStart() {
        // Resolve command definitions depending on selected source.
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

            // Editing is disabled.
            null   -> return
        }
        // Command registration API is suspend; startup contract here is synchronous.
        runBlocking {
            bot.setMyCommands(botCommands, BotCommandScopeDefault())
        }
        logger.debug("Commands registered: {}", botCommands)
    }
}
