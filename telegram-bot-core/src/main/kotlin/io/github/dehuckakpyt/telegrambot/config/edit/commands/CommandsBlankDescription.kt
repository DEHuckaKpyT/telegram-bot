package io.github.dehuckakpyt.telegrambot.config.edit.commands


/**
 * Strategy for handling blank command descriptions in `CommandsSource.CODE` mode.
 *
 * @property SKIP silently ignore command without description
 * @property FAIL throw exception on startup/registration
 *
 * @author Denis Matytsin
 */
enum class CommandsBlankDescription {
    /** Ignore commands with blank description. */
    SKIP,
    /** Fail fast if any command has blank description. */
    FAIL,
}
