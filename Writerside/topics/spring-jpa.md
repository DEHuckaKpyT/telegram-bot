# Spring JPA

## Save all states in database

<procedure>
    <step>Add dependency <code>telegram-bot-source-jpa</code>.</step>
    <step>Add repositories to <code>@EnableJpaRepositories</code> and entities to <code>@EntityScan</code>.</step>
    <step>Change all sources in configuration.</step>
</procedure>

<tabs>
<tab title="Spring 3.0">
<code-block lang="kotlin">
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-jpa:%current_version%")
}
</code-block>
</tab>
<tab title="Spring 2.7">
<code-block lang="kotlin">
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-spring2-jpa:%current_version%")
}
</code-block>
</tab>
</tabs>

```kotlin
@EnableTelegramBot
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository"])
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model"])
@Configuration
class BotConfig {
    @Bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        messageSource = { MessageSource.inDatabase }
        receiving {
            callbackContentSource = { CallbackContentSource.inDatabase }
            chainSource = { ChainSource.inDatabase }
        }
    }
}
```

## Use only certain sources

You can also use only selected resources.

For example, how to save in database only callbacks:

```kotlin
@EnableTelegramBot
@EnableJpaRepositories(basePackages = ["io.github.dehuckakpyt.telegrambot.repository.callback"])
@EntityScan(basePackages = ["io.github.dehuckakpyt.telegrambot.model.callback"])
@Configuration
class BotConfig {
    @Bean
    fun telegramBotConfig(): TelegramBotConfig = TelegramBotConfig().apply {
        receiving {
            callbackContentSource = { CallbackContentSource.inDatabase }
        }
    }
}
```

<chapter title="Package names by source" collapsible="true">
<table style="both">
<tr><td>Source name</td><td>Repository package</td><td>Entity package</td></tr>
<tr><td>messageSource</td><td>io.github.dehuckakpyt.telegrambot.repository.message</td><td>io.github.dehuckakpyt.telegrambot.model.message</td></tr>
<tr><td>callbackContentSource</td><td>io.github.dehuckakpyt.telegrambot.repository.callback</td><td>io.github.dehuckakpyt.telegrambot.model.callback</td></tr>
<tr><td>chainSource</td><td>io.github.dehuckakpyt.telegrambot.repository.chain</td><td>io.github.dehuckakpyt.telegrambot.model.chain</td></tr>
</table>
</chapter>
