package io.github.dehuckakpyt.telegrambot.holder.command

import io.github.dehuckakpyt.telegrambot.config.edit.commands.CommandsBlankDescription
import io.github.dehuckakpyt.telegrambot.config.edit.commands.EditCommandsConfig
import io.github.dehuckakpyt.telegrambot.model.telegram.BotCommand
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CommandDescriptionsHolderTest : FreeSpec({

    "sort commands by numeric prefix and keep description without prefix" {
        // Arrange
        val holder = CommandDescriptionsHolder(
            EditCommandsConfig(blankDescription = CommandsBlankDescription.FAIL),
        )

        // Act
        holder.put("/help", "2. Help")
        holder.put("/start", "1. Start command")
        holder.put("/calculate", "3. Calculate")

        // Assert
        holder.botCommands shouldBe listOf(
            BotCommand("start", "Start command"),
            BotCommand("help", "Help"),
            BotCommand("calculate", "Calculate"),
        )
    }

    "accept description without order prefix" {
        // Arrange
        val holder = CommandDescriptionsHolder(
            EditCommandsConfig(blankDescription = CommandsBlankDescription.FAIL),
        )

        // Act
        holder.put("/start", "Start command")

        // Assert
        holder.botCommands shouldBe listOf(
            BotCommand("start", "Start command"),
        )
    }

    "throw exception for null description when blankDescription is FAIL" {
        // Arrange
        val holder = CommandDescriptionsHolder(
            EditCommandsConfig(blankDescription = CommandsBlankDescription.FAIL),
        )

        // Act + Assert
        shouldThrow<IllegalArgumentException> {
            holder.put("/start", null)
        }
    }

    "do not throw exception for null description when blankDescription is SKIP" {
        // Arrange
        val holder = CommandDescriptionsHolder(
            EditCommandsConfig(blankDescription = CommandsBlankDescription.SKIP),
        )

        // Act + Assert
        shouldNotThrowAny {
            holder.put("/start", null)
        }
        holder.botCommands shouldBe emptyList()
    }
})
