package io.github.dehuckakpyt.telegrambot.config.edit.commands


/**
 * @author Denis Matytsin
 */
data class EditCommandsConfig(
    /** Command descriptions source. */
    var source: CommandsSource? = null,

    /** Blank description handling mode. */
    var blankDescription: CommandsBlankDescription? = null,

    /** Command descriptions map where key is command and value is description. */
    var definition: Map<String, String>? = null,
)
