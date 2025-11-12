# Spring JPA

## Save all states in database

All you have to do is add a dependency for `source-jpa`:
<code-block lang="kotlin">
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-core:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-spring:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-source-jpa:%current_version%")
}
</code-block>

## Available properties

| PROPERTY                                                   | DEFAULT | DESCRIPTION                                                          |
|------------------------------------------------------------|---------|----------------------------------------------------------------------|
| `telegram-bot.source-jpa.enabled`                          | `true`  | Enable (disable) all default `inDatabase` sources                    |
| `telegram-bot.source-jpa.message-source.enabled`           | `true`  | Enable (disable) default `inDatabase` message source                 |
| `telegram-bot.source-jpa.chain-source.enabled`             | `true`  | Enable (disable) default `inDatabase` chain source                   |
| `telegram-bot.source-jpa.callback-content-source.enabled`  | `true`  | Enable (disable) default `inDatabase` callback content source        |
| `telegram-bot.source-jpa.callback-content-source.per-user` | `20`    | Max count of contents will be saved for every user (`-1` for ignore) |
| `telegram-bot.source-jpa.user-source.enabled`              | `true`  | Enable (disable) default `inDatabase` user source                    |
| `telegram-bot.source-jpa.chat-source.enabled`              | `false` | Enable (disable) default `inDatabase` chat source                    |
| `telegram-bot.source-jpa.chat-status-event-source.enabled` | `false` | Enable (disable) default `inDatabase` chat status event source       |


## Override default models and extend services

If you're a bit unhappy with the default implementation, you can override it. For example, override `TelegramUserSource`:

<tabs id="override-model-spring-jpa" group="telegram-bot-code">
    <tab title="Spring" group-key="spring"></tab>
</tabs>

You need to disable it in properties `resources/application.properties`:
```properties
telegram-bot.source-jpa.user-source.enabled=false
```

```kotlin
package com.dehucka.example.config

@Configuration
@EnableJpaRepositories(basePackages = ["com.dehucka.example.repository"]) // your repository package
@EntityScan(basePackages = ["com.dehucka.example.model"]) // your model package
class BotConfig
```

```kotlin
package com.dehucka.example.model

// It is important to write the entity with an empty constructor.
// Otherwise, it will be necessary to override the method for creating a user.
@Entity
class TelegramUser : BaseTelegramUser() {

    // in BaseTelegramUser all default fields
    // and you can add your own fields
    var phone: String? = null
}
```

```kotlin
package com.dehucka.example.repository

import com.dehucka.example.model.TelegramUser

interface TelegramUserRepository : BaseTelegramUserRepository<TelegramUser>
```

```kotlin
package com.dehucka.example.service

import com.dehucka.example.model.TelegramUser
import com.dehucka.example.repository.TelegramUserRepository

@Service
class TelegramUserService(
    override val transactional: TransactionAction,
    override val repository: TelegramUserRepository,
) : BaseTelegramUserSource<TelegramUser>() {

    // your any methods
    suspend fun setPhone(userId: Long, phone: String): TelegramUser = transactional {
        val user = repository.findByUserId(userId)
            ?: throw IllegalStateException("User $userId does not exist.")

        user.phone = phone

        repository.save(user)
    }
}
```

If you need more control, you can extend interfaces `io.github.dehuckakpyt.telegrambot.model.source.TelegramUser` and `io.github.dehuckakpyt.telegrambot.source.user.TelegramUserSource`.

If you need to inject instances from `TelegramBotContext`, you can use creating bean of `io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression<out TelegramUserSource>` like <a href="sources.md">here</a>.
