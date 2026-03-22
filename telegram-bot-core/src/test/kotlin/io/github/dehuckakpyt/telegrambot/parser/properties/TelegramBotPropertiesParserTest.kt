package io.github.dehuckakpyt.telegrambot.parser.properties

import io.github.dehuckakpyt.telegrambot.config.receiver.WebhookConfig
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.time.Duration

class TelegramBotPropertiesParserTest : FreeSpec({
    "parse telegram-bot root with kebab-case and env placeholders" {
        val yaml = $$"""
            telegram-bot:
              token: ${TELEGRAM_BOT_TOKEN}
              username: yaml-bot
              receiving:
                long-polling:
                  limit: 11
                  timeout: 22
                  retry-delay: 333
                  graceful-shutdown-timeout: PT1M10S
                webhook:
                  url-host: https://my.domain.com
                  url-path: /updates/receive
                  certificate-path: cert.pem
                  ip-address: 127.0.0.1
                  max-connections: 40
                  drop-pending-updates: true
                  secret-token: my-secret
                  secret-token-random-generation: RANDOM_UUID
                  secret-token-random-generation-print-on-startup: true
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(
            yaml = yaml,
            env = mapOf("TELEGRAM_BOT_TOKEN" to "env-token"),
        )

        properties.token shouldBe "env-token"
        properties.username shouldBe "yaml-bot"
        properties.receiving?.longPolling?.limit shouldBe 11
        properties.receiving?.longPolling?.timeout shouldBe 22
        properties.receiving?.longPolling?.retryDelay shouldBe 333L
        properties.receiving?.longPolling?.gracefulShutdownTimeout shouldBe Duration.parse("PT1M10S")
        properties.receiving?.webhook?.urlHost shouldBe "https://my.domain.com"
        properties.receiving?.webhook?.urlPath shouldBe "/updates/receive"
        properties.receiving?.webhook?.certificatePath shouldBe "cert.pem"
        properties.receiving?.webhook?.ipAddress shouldBe "127.0.0.1"
        properties.receiving?.webhook?.maxConnections shouldBe 40
        properties.receiving?.webhook?.dropPendingUpdates shouldBe true
        properties.receiving?.webhook?.secretToken shouldBe "my-secret"
        properties.receiving?.webhook?.secretTokenRandomGeneration shouldBe WebhookConfig.SecretTokenRandomGeneration.RANDOM_UUID
        properties.receiving?.webhook?.secretTokenRandomGenerationPrintOnStartup shouldBe true
    }

    "parse dotted keys" {
        val yaml = """
            telegram-bot:
              token: my-token
              receiving.long-polling.timeout: 55
              receiving.webhook.url-host: https://example.com
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(yaml)

        properties.token shouldBe "my-token"
        properties.receiving?.longPolling?.timeout shouldBe 55
        properties.receiving?.webhook?.urlHost shouldBe "https://example.com"
    }

    "parse dotted telegram-bot root keys at top level" {
        val yaml = $$"""
            telegram-bot.token: ${TELEGRAM_BOT_TOKEN}
            telegram-bot.username: my-bot
            telegram-bot.receiving.webhook.url-host: https://${telegram-bot.username}.example.com
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(
            yaml = yaml,
            env = mapOf("TELEGRAM_BOT_TOKEN" to "env-token"),
        )

        properties.token shouldBe "env-token"
        properties.username shouldBe "my-bot"
        properties.receiving?.webhook?.urlHost shouldBe "https://my-bot.example.com"
    }

    "parse spring-style default placeholders and nullable empty default" {
        val yaml = $$"""
            telegram-bot:
              token: ${TELEGRAM_BOT_TOKEN:default-token}
              username: ${TELEGRAM_BOT_USERNAME:}
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(
            yaml = yaml,
            env = emptyMap(),
        )

        properties.token shouldBe "default-token"
        properties.username shouldBe null
    }

    "map explicit blank value to null" {
        val yaml = """
            telegram-bot:
              token: my-token
              username: ""
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(yaml)

        properties.token shouldBe "my-token"
        properties.username shouldBe null
    }

    "resolve placeholders from sibling properties" {
        val yaml = $$"""
            telegram-bot:
              token: my-token
              username: my-bot
              receiving.webhook.url-host: https://${telegram-bot.username}.example.com
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(yaml)

        properties.token shouldBe "my-token"
        properties.username shouldBe "my-bot"
        properties.receiving?.webhook?.urlHost shouldBe "https://my-bot.example.com"
    }

    "rooted config does not resolve short placeholders from yaml properties" {
        val yaml = $$"""
            telegram-bot:
              token: my-token
              username: my-bot
              receiving.webhook.url-host: https://${username}.example.com
        """.trimIndent()

        shouldThrow<IllegalArgumentException> {
            TelegramBotPropertiesParser.parseYamlText(yaml, env = emptyMap())
        }
    }

    "rootless config resolves short placeholders from yaml properties" {
        val yaml = $$"""
            token: my-token
            username: my-bot
            receiving.webhook.url-host: https://${username}.example.com
        """.trimIndent()

        val properties = TelegramBotPropertiesParser.parseYamlText(yaml, env = emptyMap())

        properties.token shouldBe "my-token"
        properties.username shouldBe "my-bot"
        properties.receiving?.webhook?.urlHost shouldBe "https://my-bot.example.com"
    }

    "fail on cyclic placeholder references in rooted config" {
        val yaml = $$"""
            telegram-bot:
              username: ${telegram-bot.token}
              token: ${telegram-bot.username}
        """.trimIndent()

        val exception = shouldThrow<IllegalArgumentException> {
            TelegramBotPropertiesParser.parseYamlText(yaml, env = emptyMap())
        }

        exception.message?.contains("Cyclic placeholder reference detected") shouldBe true
    }

    "fail on cyclic placeholder references in rootless config" {
        val yaml = $$"""
            username: ${token}
            token: ${username}
        """.trimIndent()

        val exception = shouldThrow<IllegalArgumentException> {
            TelegramBotPropertiesParser.parseYamlText(yaml, env = emptyMap())
        }

        exception.message?.contains("Cyclic placeholder reference detected") shouldBe true
    }
})
