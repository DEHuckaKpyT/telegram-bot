# Todo

## High priority
- Remove [SpringContext object](https://github.com/DEHuckaKpyT/telegram-bot/blob/v0.7.13b/telegram-bot-spring/src/main/kotlin/io/github/dehuckakpyt/telegrambot/context/SpringContext.kt#L15).
- Add webhook.

## Medium priority
- Add post process after send messages (and after receive messages).
- Add more tests.
- Complete documentation in code (classes and methods).
- Add to documentation info about `currentContainerContext()`, `InputFactory`.
- Create custom `@ConditionalOnMissingBean` for `source-jpa` `io.github.dehuckakpyt.telegrambot.config`
- Add `ConfigExpression` for all interfaces in TelegramBotConfig (at least for Spring)
- Configure dependencies with [refreshVersions](https://github.com/Splitties/refreshVersions)

## Low priority
- Figure out how to send messages longer than 4096 characters.
- Figure out how to implement internationalization.
- Add ability to specify action ('typing...', etc.).