package io.github.dehuckakpyt.telegrambot.holder.command

import io.github.dehuckakpyt.telegrambot.config.edit.commands.CommandsBlankDescription.FAIL
import io.github.dehuckakpyt.telegrambot.config.edit.commands.EditCommandsConfig
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommand


/**
 * Accumulates command descriptions declared in code and provides them in sorted order.
 *
 * Description may start with ordering prefix like `1. Start`.
 * The numeric prefix is used only for sorting and is removed from final description text.
 * If prefix is absent, description is kept as is and sorted after numbered entries.
 *
 * @author Denis Matytsin
 */
internal class CommandDescriptionsHolder(
    private val editCommandsConfig: EditCommandsConfig,
    /** Separator between order number and description text (default: ". "). */
    orderDescriptionDelimiter: String = ". ",
) {
    /** Regex for optional ordered format: `N<delimiter>Description`. */
    private val orderedDescriptionRegex = Regex("^(\\d+)${Regex.escape(orderDescriptionDelimiter)}(.+)$")

    /** Final command list in Telegram Bot API format. */
    internal val botCommands: List<BotCommand>
        get() = descriptionsByCommandRaw
            .sortedBy { it.order }
            .map { (_, command, description) ->
                BotCommand(command = command, description = description)
            }

    /** Internal mutable storage before projection to public sorted view. */
    private val descriptionsByCommandRaw: MutableList<CommandDescription> = mutableListOf()

    /**
     * Adds command description from code configuration.
     *
     * Blank description handling depends on [EditCommandsConfig.blankDescription].
     */
    public fun put(command: String, description: String? = null) {
        // Blank descriptions are either rejected or ignored depending on config.
        if (description.isNullOrBlank()) {
            if (editCommandsConfig.blankDescription == FAIL) throw IllegalArgumentException("The command description must not be blank when 'telegram-bot.edit.commands.source=code' is configured.")
            else /* editCommandsConfig.blankDescription == SKIP */ return
        }

        // Optional numeric prefix (`1. `) controls ordering in Telegram menu.
        val (order, cleanDescription) = parseDescription(description)
        if (cleanDescription.length !in 1..256) {
            throw IllegalArgumentException("The command description length must be between 1 and 256 characters, but was ${cleanDescription.length} (${cleanDescription}).")
        }

        // Telegram Bot API expects command name without leading slash.
        val cleanCommand = command.removePrefix("/")
        descriptionsByCommandRaw.removeAll { it.command == cleanCommand }

        descriptionsByCommandRaw += CommandDescription(
            order = order,
            command = cleanCommand,
            description = cleanDescription,
        )
    }

    /**
     * Parses optional numeric order from description.
     *
     * Returns pair of:
     * - parsed order and clean description, if prefix exists
     * - [Int.MAX_VALUE] and original description, if prefix is absent
     */
    private fun parseDescription(description: String): Pair<Int, String> {
        val match = orderedDescriptionRegex.matchEntire(description)
            ?: return Int.MAX_VALUE to description

        val order = match.groupValues[1].toInt()
        val cleanDescription = match.groupValues[2]

        return order to cleanDescription
    }

    private data class CommandDescription(
        val order: Int,
        val command: String,
        val description: String,
    )
}
