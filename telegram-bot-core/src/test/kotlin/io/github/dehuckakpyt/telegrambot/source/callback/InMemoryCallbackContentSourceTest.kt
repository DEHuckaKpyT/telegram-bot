package io.github.dehuckakpyt.telegrambot.source.callback

import io.github.dehuckakpyt.telegrambot.ext.inMemory
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.delay

class InMemoryCallbackContentSourceTest : FreeSpec({

    "one callback" {
        // Arrange
        val source = CallbackContentSource.inMemory

        // Act
        val savedContent = source.save(1_001, 1_001, "content")
        val actualContent = source.get(savedContent.callbackId)

        // Assert
        assertSoftly {
            actualContent.callbackId shouldBeEqual savedContent.callbackId
            actualContent.chatId shouldBeEqual savedContent.chatId
            actualContent.fromId shouldBeEqual savedContent.fromId
            actualContent.content shouldBeEqual savedContent.content
        }
    }

    "callbacks for different users" {
        // Arrange
        val source = CallbackContentSource.inMemory

        // Act
        val savedContent1 = source.save(1_001, 1_001, "content")
        delay(10)
        val actualContent1 = source.get(savedContent1.callbackId)
        val savedContent2 = source.save(1_002, 1_002, "content")
        delay(10)
        val actualContent2 = source.get(savedContent2.callbackId)

        // Assert
        assertSoftly {
            actualContent1.callbackId shouldBeEqual savedContent1.callbackId
            actualContent1.chatId shouldBeEqual savedContent1.chatId
            actualContent1.fromId shouldBeEqual savedContent1.fromId
            actualContent1.content shouldBeEqual savedContent1.content

            actualContent2.callbackId shouldBeEqual savedContent2.callbackId
            actualContent2.chatId shouldBeEqual savedContent2.chatId
            actualContent2.fromId shouldBeEqual savedContent2.fromId
            actualContent2.content shouldBeEqual savedContent2.content
        }
    }

    "more then max contents count for one user" {
        // Arrange
        val source = CallbackContentSource.inMemory(maxCallbackContentsPerUser = 2)

        // Act
        val savedContent1 = source.save(1_001, 1_001, "content")
        source.save(1_001, 1_001, "content")
        delay(10)
        val actualContent1 = source.get(savedContent1.callbackId) // after saving 2 times it is possible to get first callback content

        source.save(1_001, 1_001, "content")

        delay(10)
        val exception = shouldThrow<RuntimeException> {
            source.get(savedContent1.callbackId) // but after saving 3 times it is not possible to get first callback content because maxCallbackContentsPerUser=2
        }

        // Assert
        assertSoftly {
            actualContent1.callbackId shouldBeEqual savedContent1.callbackId
            actualContent1.chatId shouldBeEqual savedContent1.chatId
            actualContent1.fromId shouldBeEqual savedContent1.fromId
            actualContent1.content shouldBeEqual savedContent1.content

            exception.message.shouldNotBeNull()
            exception.message!! shouldBeEqual "No content was found for the callback :( You might have forgotten that restarting the program erases all callbacks. Recommended to use the implementation with the database."
        }
    }

    "more then max contents count for several users" {
        // Arrange
        val source = CallbackContentSource.inMemory(maxCallbackContentsPerUser = 2)
        val firstTestedUserId = 1_001L
        val secondTestedUserId = 1_002L

        // Act
        val savedContent1 = source.save(1_001, firstTestedUserId, "content")
        source.save(1_001, 1_003, "content")
        val savedContent2 = source.save(1_001, secondTestedUserId, "content")
        source.save(1_001, firstTestedUserId, "content")
        source.save(1_001, secondTestedUserId, "content")
        source.save(1_001, 1_004, "content")
        source.save(1_001, 1_003, "content")
        source.save(1_001, 1_004, "content")
        source.save(1_001, 1_003, "content")
        delay(10)
        source.get(savedContent1.callbackId)
        source.get(savedContent2.callbackId)

        source.save(1_001, firstTestedUserId, "content")
        source.save(1_001, secondTestedUserId, "content")

        delay(10)
        val exception1 = shouldThrow<RuntimeException> {
            source.get(savedContent1.callbackId)
        }
        val exception2 = shouldThrow<RuntimeException> {
            source.get(savedContent2.callbackId)
        }

        // Assert
        assertSoftly {
            exception1.message.shouldNotBeNull()
            exception1.message!! shouldBeEqual "No content was found for the callback :( You might have forgotten that restarting the program erases all callbacks. Recommended to use the implementation with the database."

            exception2.message.shouldNotBeNull()
            exception2.message!! shouldBeEqual "No content was found for the callback :( You might have forgotten that restarting the program erases all callbacks. Recommended to use the implementation with the database."
        }
    }
})
