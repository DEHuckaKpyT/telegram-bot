package io.github.dehuckakpyt.telegrambot.converter

import io.github.dehuckakpyt.telegrambot.ext.toUUID
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk

class SimpleCallbackSerializerTest : FreeSpec({

    val source = mockk<CallbackContentSource>()
    val converter = mockk<ContentConverter>()
    val serializer = SimpleCallbackSerializer(source, converter, '|')

    val chatId = 1_001L
    val fromId = 1_002L

    "serialize to callback" - {
        "empty data" {
            // Arrange
            val nextStepName = "next_step_name"

            // Act
            val actual = serializer.toCallback(chatId, fromId, nextStepName, null)

            // Assert
            actual shouldBeEqual nextStepName
        }
        "short data" {
            // Arrange
            val nextStepName = "next_step_name"
            val instance = mockk<Any>()
            every { converter.toContent(instance) } returns """{"value":"short value of exactly 36 characters"}"""

            val expected = """next_step_name|s{"value":"short value of exactly 36 characters"}"""

            // Act
            val actual = serializer.toCallback(chatId, fromId, nextStepName, instance)

            // Assert
            actual shouldBeEqual expected
        }

        "long data" {
            // Arrange
            val nextStepName = "next_step_name"
            val instance = mockk<Any>()
            val data = """{"value":"string value of exactly 37 characters"}"""
            every { converter.toContent(instance) } returns data
            coEvery { source.save(chatId, fromId, data).callbackId } returns "00000001-0000-0000-0000-000000000001".toUUID()

            val expected = """next_step_name|i00000001-0000-0000-0000-000000000001"""

            // Act
            val actual = serializer.toCallback(chatId, fromId, nextStepName, instance)

            // Assert
            actual shouldBeEqual expected
        }

        "long next step name" {
            // Arrange
            val nextStepName = "step_longer_then_26_symbols"
            val instance = mockk<Any>()
            every { converter.toContent(instance) } returns """{"value":"string value of exactly 37 characters"}"""

            // Act
            val exception = shouldThrow<IllegalArgumentException> {
                serializer.toCallback(chatId, fromId, nextStepName, instance)
            }

            // Assert
            exception.message.shouldNotBeNull()
            exception.message!! shouldBeEqual "Callback step name should be less or equal than 26 symbols to put max 64 symbols. Because callback data will contain special markers (2 symbols) + UUID id (36 symbols) + callback name."
        }
    }

    "deserialize from callback" - {
        "empty data" {
            // Arrange
            val argument = "next_step_name"
            val expected = CallbackDataInfo("next_step_name")

            // Act
            val actual = serializer.fromCallback(argument)

            // Assert
            actual shouldBeEqual expected
        }
        "short data" {
            // Arrange
            val argument = """next_step_name|s{"value":"short value of exactly 36 characters"}"""
            val expected = CallbackDataInfo("next_step_name", """{"value":"short value of exactly 36 characters"}""")

            // Act
            val actual = serializer.fromCallback(argument)

            // Assert
            actual shouldBeEqual expected
        }

        "long data" {
            // Arrange
            val argument = "next_step_name|i00000001-0000-0000-0000-000000000001"
            coEvery { source.get("00000001-0000-0000-0000-000000000001".toUUID()).content } returns """{"value":"string value of exactly 37 characters"}"""
            val expected = CallbackDataInfo("next_step_name", """{"value":"string value of exactly 37 characters"}""")

            // Act
            val actual = serializer.fromCallback(argument)

            // Assert
            actual shouldBeEqual expected
        }
    }
})