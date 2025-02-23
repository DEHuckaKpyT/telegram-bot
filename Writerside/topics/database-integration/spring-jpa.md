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
| `telegram-bot.source-jpa.enabled`                          | `true`  | Enable (disable) all default `inDatabase` sources                    |
| `telegram-bot.source-jpa.message-source.enabled`           | `true`  | Enable (disable) default `inDatabase` message source                 |
| `telegram-bot.source-jpa.chain-source.enabled`             | `true`  | Enable (disable) default `inDatabase` chain source                   |
| `telegram-bot.source-jpa.callback-content-source.enabled`  | `true`  | Enable (disable) default `inDatabase` callback content source        |
| `telegram-bot.source-jpa.callback-content-source.per-user` | `20`    | Max count of contents will be saved for every user (`-1` for ignore) |
| `telegram-bot.source-jpa.user-source.enabled`              | `true`  | Enable (disable) default `inDatabase` user source                    |
| `telegram-bot.source-jpa.chat-source.enabled`              | `false` | Enable (disable) default `inDatabase` chat source                    |
| `telegram-bot.source-jpa.chat-status-event-source.enabled` | `false` | Enable (disable) default `inDatabase` chat status event source       |


## Override default `inDatabase` models

If you're a bit unhappy with the default implementation, you can override it. For example, override `TelegramMessage` entity:

<tabs id="override-model-spring-jpa" group="telegram-bot-code">
    <tab title="Spring" group-key="spring"></tab>
</tabs>

You need to disable it in properties:

`resources/application.properties`
```properties
telegram-bot.source-jpa.message-source.enabled=false
```

```kotlin
package com.dehucka.example.config

import com.dehucka.example.repository.TelegramMessageRepository
import com.dehucka.example.service.TelegramMessageService
import io.github.dehuckakpyt.telegrambot.annotation.EnableTelegramBot
import io.github.dehuckakpyt.telegrambot.config.expression.ConfigExpression
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories(basePackages = ["com.dehucka.example.repository"]) // your repository package
@EntityScan(basePackages = ["com.dehucka.example.model"]) // your model package
class BotConfig {

    @Bean
    fun telegramMessageSourceExpression(
        transactionAction: TransactionAction,
        repository: TelegramMessageRepository,
    ): ConfigExpression<MessageSource> =
        ConfigExpression { TelegramMessageService(transactionAction, repository) }
}
```

```kotlin
package com.dehucka.example.model

import io.github.dehuckakpyt.telegrambot.model.UUIDTable
import io.github.dehuckakpyt.telegrambot.model.source.TelegramMessage
import io.github.dehuckakpyt.telegrambot.model.telegram.InlineKeyboardMarkup
import io.hypersistence.utils.hibernate.type.array.ListArrayType
import io.hypersistence.utils.hibernate.type.json.JsonType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Type
import java.time.LocalDateTime


@Entity
class CustomTelegramMessage(
    @Column(nullable = false)
    override val chatId: Long,

    @Column(nullable = false)
    override val fromId: Long,

    @Column(nullable = false)
    override val fromBot: Boolean,

    @Column(nullable = false)
    override val messageId: Long,

    @Column(nullable = false)
    override val type: String,

    override val step: String?,

    override val stepContainerType: String?,

    @Column(columnDefinition = "text")
    override val text: String?,

    @Type(ListArrayType::class)
    @Column(name = "file_ids", columnDefinition = "text[]")
    override val fileIds: List<String>?,

    @Type(JsonType::class)
    @Column(columnDefinition = "jsonb")
    val replyMarkup: InlineKeyboardMarkup?,

    @Column(nullable = false)
    @ColumnDefault("'now()'")
    override val createDate: LocalDateTime = LocalDateTime.now(),
) : UUIDTable(), TelegramMessage
```

```kotlin
package com.dehucka.example.repository

import com.dehucka.example.model.TelegramMessage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface TelegramMessageRepository : JpaRepository<CustomTelegramMessage, UUID>
```

```kotlin
package com.dehucka.example.service

import com.dehucka.example.model.TelegramMessage
import com.dehucka.example.repository.TelegramMessageRepository
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource
import io.github.dehuckakpyt.telegrambot.transaction.action.TransactionAction


class TelegramMessageService(
    private val transactional: TransactionAction,
    private val repository: TelegramMessageRepository,
) : MessageSource {

    override suspend fun save(message: Message, fromBot: Boolean, type: String, step: String?, stepContainerType: String?, text: String?, fileIds: List<String>?): Unit = transactional {
        repository.save(
            CustomTelegramMessage(
                chatId = message.chat.id,
                fromId = message.from!!.id,
                fromBot = fromBot,
                messageId = message.messageId,
                type = type,
                step = step,
                stepContainerType = stepContainerType,
                text = text,
                fileIds = fileIds,
                replyMarkup = message.replyMarkup
            )
        )
    }
}
```
