# Sources

`callbackContentSource: CallbackContentSource` - is used to store callback data that are longer than 64 characters.

`chainSource: ChainSource` - is used to store the current state of chains (next step, content for next step).

`messageSource: MessageSource` - is used to store the history of all messages.


By default, everything is stored in memory and will become inaccessible after the application is terminated.
It may be convenient for quick testing of bot's work.

```kotlin
val config = TelegramBotConfig().apply {
    messageSource = { MessageSource.empty }
    receiving {
        callbackContentSource = { CallbackContentSource.inMemory }
        chainSource = { ChainSource.inMemory }
    }
}
```

How to **save state in database** see <a href="database-integration.md">here</a>.

## Spring only

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
```