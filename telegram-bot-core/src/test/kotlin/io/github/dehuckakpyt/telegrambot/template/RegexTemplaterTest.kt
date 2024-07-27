package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.ext.template.regex
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.equals.shouldBeEqual

class RegexTemplaterTest : FreeSpec({

    val templater = RegexTemplater()

    data class SimpleTestModel(
        val name: String,
        val value: Int,
    )

    "common usage" - {
        "simple object" {
            // Arrange
            val template = "model with name '\${name}' and value '\${value}'"
            val model = SimpleTestModel("something", 11)

            // Act
            val actual = with(templater) {
                template with model
            }

            // Assert
            assertSoftly {
                actual shouldBeEqual "model with name 'something' and value '11'"
            }
        }

        "map elements" {
            // Arrange
            val template = "model with name '\${name}' and value '\${value}'"
            val model = mapOf("name" to "something", "value" to 11)

            // Act
            val actual = with(templater) {
                template with model
            }

            // Assert
            assertSoftly {
                actual shouldBeEqual "model with name 'something' and value '11'"
            }
        }
    }

    "breaking cases" - {
        "without substitution" {
            // Arrange
            val template = "simplest string"
            val model = mapOf("name" to "something", "value" to 11)

            // Act
            val actual = with(templater) {
                template with model
            }

            // Assert
            assertSoftly {
                actual shouldBeEqual "simplest string"
            }
        }

        "template inside template" {
            // Arrange
            val template = "model with name '\${some text \${name}}' and value '\${value}'"
            val model = mapOf("name" to "something", "value" to 11)

            // Act
            val actual = with(templater) {
                template with model
            }

            // Assert
            assertSoftly {
                actual shouldBeEqual "model with name '\${some text something}' and value '11'"
            }
        }
    }

    "other settings" - {
        "replacement null value" {
            // Arrange
            val customTemplater = Templater.regex(replaceNullWith = "<null value>")
            val template = "model with name '\${name}' and value '\${value}'"
            val model = mapOf("name" to "something")

            // Act
            val actual = with(customTemplater) {
                template with model
            }

            // Assert
            assertSoftly {
                actual shouldBeEqual "model with name 'something' and value '<null value>'"
            }
        }

        "replacement regex" {
            // Arrange
            val customTemplater = Templater.regex(replaceRegex = Regex("(\\{([a-zA-Z]+[a-zA-Z0-9_]*)\\})"))
            val template = "model with name '{name}' and value '{value}'"
            val model = mapOf("name" to "something", "value" to 11)

            // Act
            val actual = with(customTemplater) {
                template with model
            }

            // Assert
            assertSoftly {
                actual shouldBeEqual "model with name 'something' and value '11'"
            }
        }
    }
})
