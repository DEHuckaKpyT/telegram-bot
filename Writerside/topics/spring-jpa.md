# Spring JPA

## Save all states in database

All you have to do is add a dependency for `source-jpa`:
<tabs>
<tab title="Spring 3.x">
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

## Available properties

| PROPERTY                                                   | DEFAULT | DESCRIPTION                                                          |
|------------------------------------------------------------|---------|----------------------------------------------------------------------|
| `telegram-bot.source-jpa.enabled`                          | `true`  | Disable all default sources                                          |
| `telegram-bot.source-jpa.message-source.enabled`           | `true`  | Disable default message source                                       |
| `telegram-bot.source-jpa.chain-source.enabled`             | `true`  | Disable default chain source                                         |
| `telegram-bot.source-jpa.callback-content-source.enabled`  | `true`  | Disable default callback content source                              |
| `telegram-bot.source-jpa.callback-content-source.per-user` | `20`    | Max count of contents will be saved for every user (`-1` for ignore) |
