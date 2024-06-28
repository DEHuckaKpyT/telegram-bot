# Testing

Tests are an important part of the application. And testing a bot can be extremely simple:

```kotlin
    dependencies {
        ...
        testImplementation("io.github.dehuckakpyt.telegrambot:telegram-bot-test:%current_version%")
    }
```

[Example in code](https://github.com/DEHuckaKpyT/telegram-bot/blob/master/example-spring/src/test/kotlin/io/github/dehuckakpyt/telegrambotexample/handler/RegistrationHandlerIT.kt) with database storing.

Examples:

<tabs id="bot-testing" group="telegram-bot-test-code">
    <tab title="JUnit 5" group-key="junit5">
        <code>com/example/myproject/handler/RegistrationHandlerIT.kt</code>
        <code-block lang="kotlin">
            @SpringBootTest
            @EnableTelegramBotTest
            class RegistrationHandlerIT @Autowired constructor(
                private val bot: TelegramBot,
            ) {
                @BeforeEach
                fun setUp() {
                    clearAllMocks()
                }
                @Test
                @DataSet("/datasets/handler/registration/command.json")
                @ExpectedDataSet("/datasets/handler/registration/command__expected.json")
                fun `register command`() = runTest {
                    // Arrange
                    coEvery { bot.sendMessage(1_001, any(), replyMarkup = any()) } returns mockk()
                    // Act
                    sendUpdateAsync(resourceAsString("/json/handler/registration/command-update.json"))
                    // Assert
                    coVerify(timeout = 3000) {
                        bot.sendMessage(1_001, "Please, share your phone number.", replyMarkup = isNull(inverse = true))
                    }
                }
            }
        </code-block>
        <code>/json/handler/registration/command-update.json</code>
        <code-block lang="json">
            {
              "update_id": 1,
              "message": {
                "message_id": 2,
                "from": {
                  "id": 1001,
                  "is_bot": false,
                  "first_name": "DEHucka_KpyT"
                },
                "chat": {
                  "id": 1001,
                  "type": "private"
                },
                "date": 1707824482,
                "text": "/register"
              }
            }
        </code-block>
    </tab>
    <tab title="Kotest" group-key="kotest">
        <code>com/example/myproject/handler/StartHandlerIT.kt</code>
        <code-block lang="kotlin">
            @SpringBootTest
            @EnableTelegramBotTest
            class StartCommandIT(
                private val bot: TelegramBot,
            ) : FreeSpec({
                beforeEach {
                    clearAllMocks()
                }
                "start command" {
                    // Arrange
                    coEvery { bot.sendMessage(123, any()) } returns mockk()
                    // Act
                    sendUpdate(resourceAsString("/json/handler/start/update.json"))
                    // Assert
                    coVerify {
                        bot.sendMessage(123, "Start command.")
                        bot.sendMessage(123, "Hello, my name is mock_bot :-)")
                    }
                }
            })
        </code-block>
        <code>/json/handler/start/update.json</code>
        <code-block lang="json">
            {
              "update_id": 1,
              "message": {
                "message_id": 2,
                "from": {
                  "id": 123,
                  "is_bot": false,
                  "first_name": "DEHucka_KpyT"
                },
                "chat": {
                  "id": 123,
                  "type": "private"
                },
                "date": 1707824482,
                "text": "/start"
              }
            }
        </code-block>
    </tab>
</tabs>