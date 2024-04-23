package io.github.dehuckakpyt.telegrambot.container.message

import io.github.dehuckakpyt.telegrambot.model.type.Message
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class CommandContainerTest : FreeSpec({

    val message = mockk<Message>(relaxed = true)
    val container = CommandContainer(1_001, message)

    "only command" {
        // Arrange
        every { message.text } returns "/start"

        // Act
        val commandPathParam = container.commandPathParam
        val commandArgument = container.commandArgument

        // Assert
        assertSoftly {
            commandPathParam.shouldBeNull()
            commandArgument.shouldBeNull()
        }
    }

    "command with path param" {
        // Arrange
        every { message.text } returns "/some_command__arg_1"

        // Act
        val commandPathParam = container.commandPathParam
        val commandArgument = container.commandArgument

        // Assert
        assertSoftly {
            commandPathParam.shouldNotBeNull()
            commandPathParam shouldBeEqual "arg_1"
            commandArgument.shouldBeNull()
        }
    }

    "command with argument" {
        // Arrange
        every { message.text } returns "/some_command arg 2"

        // Act
        val commandPathParam = container.commandPathParam
        val commandArgument = container.commandArgument

        // Assert
        assertSoftly {
            commandPathParam.shouldBeNull()
            commandArgument.shouldNotBeNull()
            commandArgument shouldBeEqual "arg 2"
        }
    }

    "command with path param and argument" {
        // Arrange
        every { message.text } returns "/some_command__arg_1 arg 2"

        // Act
        val commandPathParam = container.commandPathParam
        val commandArgument = container.commandArgument

        // Assert
        assertSoftly {
            commandPathParam.shouldNotBeNull()
            commandPathParam shouldBeEqual "arg_1"
            commandArgument.shouldNotBeNull()
            commandArgument shouldBeEqual "arg 2"
        }
    }

    "command with path param and argument and multiple spaces" {
        // Arrange
        every { message.text } returns "/some_command__arg__1  arg  2"

        // Act
        val commandPathParam = container.commandPathParam
        val commandArgument = container.commandArgument

        // Assert
        assertSoftly {
            commandPathParam.shouldNotBeNull()
            commandPathParam shouldBeEqual "arg__1"
            commandArgument.shouldNotBeNull()
            commandArgument shouldBeEqual "arg  2"
        }
    }
})
