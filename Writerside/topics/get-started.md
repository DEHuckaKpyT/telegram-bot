# Get started

To use the library, all you need to do is add a dependency, build a config, specify token and username and create the bot and its context.

<tabs id="bot-creating" group="telegram-bot-code">
    <tab title="Spring" group-key="spring">
        Ready-to-use solution for use with Spring.
        <br/>
        <code>build.gradle.kts</code>
        <code-block lang="kotlin">
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:%current_version%")
            }
        </code-block>
        <code>com/example/myproject/config/BotConfig.kt</code>
        <code-block lang="kotlin">
            @EnableTelegramBot
            @Configuration
            class BotConfig {
                @Bean //optional bean
                fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
                    //configure..
                }
            }
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
        Ready-to-use solution for use with Ktor+Koin.
        <br/>
        <code>build.gradle.kts</code>
        <code-block lang="kotlin">
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-ktor:%current_version%")
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
        Can be used for integrate with any frameworks manually.
        <br/>
        <code>build.gradle.kts</code>
        <code-block lang="kotlin">
            repositories {
                mavenCentral()
            }
            dependencies {
                implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
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
                        }
                    }
                }
                val context = TelegramBotFactory.createTelegramBotContext(config)
                val bot = context.telegramBot
                val updateReceiver = context.updateReceiver
                // start and stop for example only, use this methods with starting and stopping your application
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

<note>
    And it is <b>highly recommended</b> to use with <b>saving states in database</b>.
    There are ready-to-use solutions for <a href="spring-jpa.md">Spring JPA</a> and <a href="exposed.md">Exposed</a>.
    Interfaces to be implemented with saving to the database described <a href="sources.md">here</a>.
</note>


## Containing modules

### Common
```text
io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%
io.github.dehuckakpyt.telegrambot:telegram-bot-spring:%current_version%
io.github.dehuckakpyt.telegrambot:telegram-bot-ktor:%current_version%
```

### Testing
Link to <a href="testing.md">Testing</a>.
```text
io.github.dehuckakpyt.telegrambot:telegram-bot-test:%current_version%
```

### Database integration
Link to <a href="spring-jpa.md">Spring JPA</a>, to <a href="exposed.md">Exposed</a>.
```text
io.github.dehuckakpyt.telegrambot:telegram-bot-source-jpa:%current_version%
io.github.dehuckakpyt.telegrambot:telegram-bot-source-spring2-jpa:%current_version%
io.github.dehuckakpyt.telegrambot:telegram-bot-source-exposed:%current_version%
```

### FreeMarker integration
Link to <a href="templates.md">Templating</a>.
```text
io.github.dehuckakpyt.telegrambot:telegram-bot-templater-freemarker:%current_version%
```