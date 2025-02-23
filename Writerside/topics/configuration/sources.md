# Sources

`callbackContentSource: CallbackContentSource` - is used to store callback data that are longer than 64 characters.

`chainSource: ChainSource` - is used to store the current state of chains (next step, content for next step).

`messageSource: MessageSource` - is used to store the history of all messages.

`telegramUserSource: TelegramUserSource` - is used to store all users which send `/start` and blocks bot.

`telegramChatSource: TelegramChatSource` - is used to store all chats in which bot was added and bot was kicked.

`telegramChatStatusEventSource: TelegramChatStatusEventSource` - is used to store the history of all chat adding and kicking.


By default, everything is stored in memory and will become inaccessible after the application is terminated.
It may be convenient for quick testing of bot's work.

```kotlin
val config = TelegramBotConfig().apply {
    messageSource = { MessageSource.empty }
    receiving {
        callbackContentSource = { CallbackContentSource.inMemory }
        chainSource = { ChainSource.inMemory }
        telegramUserSource = { TelegramUserSource.empty }
        telegramChatSource = { TelegramChatSource.empty }
        telegramChatStatusEventSource = { TelegramChatStatusEventSource.empty }
    }
}
```

How to **save state in database** see <a href="database-integration.md">here</a>.

<tabs id="template-factory-receiving-templates" group="telegram-bot-code">
    <tab title="Spring" group-key="spring"></tab>
</tabs>

You can create beans as `ConfigExpression<..Source>` also:

```kotlin
    @Bean
    fun telegramMessageSourceExpression(): ConfigExpression<MessageSource> = 
        ConfigExpression { CustomTelegramMessageSource() }

    @Bean
    fun chainSourceExpression(): ConfigExpression<ChainSource> = 
        ConfigExpression { CustomChainSource() }

    @Bean
    fun callbackContentSourceExpression(): ConfigExpression<CallbackContentSource> = 
        ConfigExpression { CustomCallbackContentSource() }

    @Bean
    fun telegramUserSourceExpression(): ConfigExpression<TelegramUserSource> =
        ConfigExpression { CustomTelegramUserSource() }

    @Bean
    fun telegramChatSourceExpression(): ConfigExpression<TelegramChatSource> = 
        ConfigExpression { CustomTelegramChatSource() }

    @Bean
    fun telegramChatStatusEventSourceExpression(): ConfigExpression<TelegramChatStatusEventSource> = 
        ConfigExpression { CustomTelegramChatStatusEventSource() }
```