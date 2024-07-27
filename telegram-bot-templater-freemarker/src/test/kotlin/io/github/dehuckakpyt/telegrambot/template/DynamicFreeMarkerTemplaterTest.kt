package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.ext.dynamicFreeMarker
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DynamicFreeMarkerTemplaterTest : FreeSpec({

    val templater = Templater.dynamicFreeMarker

    data class TestModel(
        val name: String,
        val value: Int,
    )

    "simple object" {
        // Arrange
        val template = "model with name '\${name}' and value '\${value}'"
        val model = TestModel("something", 11)

        // Act
        val actual = with(templater) {
            template with model
        }

        // Assert
        assertSoftly {
            actual shouldBe "model with name 'something' and value '11'"
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
            actual shouldBe "model with name 'something' and value '11'"
        }
    }
})
