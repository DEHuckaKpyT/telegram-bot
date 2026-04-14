package io.github.dehuckakpyt.telegrambot.config.edit

import io.github.dehuckakpyt.telegrambot.config.edit.commands.EditCommandsConfig


/**
 * @author Denis Matytsin
 */
data class EditConfig(
    /** Edit commands */
    val commands: EditCommandsConfig = EditCommandsConfig(),
)
