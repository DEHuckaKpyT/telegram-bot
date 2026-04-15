package io.github.dehuckakpyt.telegrambot.config.edit.commands


/**
 * Source of bot command descriptions that should be applied on startup.
 *
 * @property CONFIG read command descriptions from configuration (`definition` map)
 * @property CODE collect command descriptions from `BotHandling.command(...)` declarations
 *
 * @author Denis Matytsin
 */
enum class CommandsSource {
    /** Read command descriptions from configuration map. */
    CONFIG,
    /** Read command descriptions from code declarations. */
    CODE,
}
