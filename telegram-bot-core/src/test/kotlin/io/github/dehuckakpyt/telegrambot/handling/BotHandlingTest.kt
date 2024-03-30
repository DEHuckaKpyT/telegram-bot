package io.github.dehuckakpyt.telegrambot.handling

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.argument.CallbackArgument
import io.github.dehuckakpyt.telegrambot.argument.message.AudioMessageArgument
import io.github.dehuckakpyt.telegrambot.argument.message.CommandArgument
import io.github.dehuckakpyt.telegrambot.argument.message.TextMessageArgument
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.factory.button.ButtonFactory
import io.github.dehuckakpyt.telegrambot.resolver.ChainResolver
import io.github.dehuckakpyt.telegrambot.template.Templater
import io.kotest.core.spec.style.FreeSpec
import io.mockk.*

class BotHandlingTest : FreeSpec({
    val bot = mockk<TelegramBot>()
    val chainResolver = mockk<ChainResolver>()
    val contentConverter = mockk<ContentConverter>()
    val templater = mockk<Templater>()
    val buttonFactory = mockk<ButtonFactory>()

    val handling = BotHandling(bot, chainResolver, contentConverter, templater, buttonFactory)

    afterAny {
        clearAllMocks()
    }

    "add command handler" {
        // Arrange
        val command = "/command_name"
        val next = "next_step_name"
        val action = mockk<suspend CommandArgument.() -> Unit>()

        every { chainResolver.addCommand(command, next, action) } just runs

        // Act
        handling.command(command, next, action)

        // Assert
        verify { chainResolver.addCommand(command, next, action) }
    }

    "add step handler" - {
        "with default type (text message)" {
            // Arrange
            val step = "step_name"
            val next = "next_step_name"
            val action = mockk<suspend TextMessageArgument.() -> Unit>()

            every { chainResolver.addStep(step, TextMessageArgument::class, next, action) } just runs

            // Act
            handling.step(step, next, action)

            // Assert
            verify { chainResolver.addStep(step, TextMessageArgument::class, next, action) }
        }

        "with other type" {
            // Arrange
            val step = "step_name"
            val type = AudioMessageArgument::class
            val next = "next_step_name"
            val action = mockk<suspend AudioMessageArgument.() -> Unit>()

            every { chainResolver.addStep(step, type, next, action) } just runs

            // Act
            handling.step(step, type, next, action)

            // Assert
            verify { chainResolver.addStep(step, type, next, action) }
        }
    }

    "add callback handler" {
        // Arrange
        val callback = "callback_name"
        val next = "next_step_name"
        val action = mockk<suspend CallbackArgument.() -> Unit>()

        every { chainResolver.addCallback(callback, next, action) } just runs

        // Act
        handling.callback(callback, next, action)

        // Assert
        verify { chainResolver.addCallback(callback, next, action) }
    }
})