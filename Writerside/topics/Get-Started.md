# Get Started

To use the library, all you need to do is add a dependency, build a config, specify token and username and create the bot and its context.

<tabs id="bot-creating" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        <code>build.gradle.kts</code>
        <code-block lang="kotlin">
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.6.0")
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:0.6.0")
            }
        </code-block>
        <code>com/example/myproject/config/BotConfig.kt</code>
        <code-block lang="kotlin">
            @EnableTelegramBot
            @Configuration
            class BotConfig
        </code-block>
        <code>resources/application.properties</code>
        <code-block>
            telegram-bot.token=${TELEGRAM_BOT_TOKEN}
            telegram-bot.username=${TELEGRAM_BOT_USERNAME}
        </code-block>
        <code>com/example/myproject/handler/StartHandler.kt</code>
        <code-block lang="kotlin">
            @HandlerComponent
            class StartHandler : BotHandler({
                command("/start") {
                    sendMessage("Hello, my name is ${bot.username} :-)")
                }
            })
        </code-block>
    </tab>
    <tab title="Ktor + Koin" group-key="ktor">
        <code>build.gradle.kts</code>
        <code-block lang="kotlin">
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.6.0")
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-ktor:0.6.0")
            }
        </code-block>
        <code>com/example/myproject/KtorApp.kt</code>
        <code-block lang="kotlin">
            fun Application.module() {
                install(Koin) {
                    modules(defaultModule)
                }
                install(TelegramBot)
            }
        </code-block>
        <code>resources/application.conf</code>
        <code-block>
            telegram-bot {
                token = ${TELEGRAM_BOT_TOKEN}
                username = ${TELEGRAM_BOT_USERNAME}
            }
        </code-block>
        <code>com/example/myproject/handler/StartHandler.kt</code>
        <code-block lang="kotlin">
            @Factory
            class StartHandler : BotHandler({
                command("/start") {
                    sendMessage("Hello, my name is ${bot.username} :-)")
                }
            })
        </code-block>
    </tab>
    <tab title="Core" group-key="core">
        <code>build.gradle.kts</code>
        <code-block lang="kotlin">
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:0.6.0")
            }
        </code-block>
        <code>com/example/myproject/KtorApp.kt</code>
        <code-block lang="kotlin">
            fun main(args: Array&lt;String&gt;): Unit {
                val config = TelegramBotConfig().apply {
                    token = "&lt;bot token required&gt;"
                    username = "&lt;bot username required&gt;"
                    receiving {
                        handling {
                            startCommand()
                            exceptionCommand()
                        }
                    }
                }
                val context = TelegramBotFactory.createTelegramBotContext(config)
                val bot = context.telegramBot
                val updateReceiver = context.updateReceiver
                updateReceiver.start()
                readlnOrNull()
                updateReceiver.stop()
            }
        </code-block>
        <code>com/example/myproject/handler/StartHandler.kt</code>
        <code-block lang="kotlin">
            fun BotHandling.startCommand() {
                command("/start") {
                    sendMessage("Hello, my name is ${bot.username} :-)")
                }
            }
        </code-block>
    </tab>
</tabs>
