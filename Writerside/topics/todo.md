# Todo

## High priority
- Remove [SpringContext object](https://github.com/DEHuckaKpyT/telegram-bot/blob/v0.7.13b/telegram-bot-spring/src/main/kotlin/io/github/dehuckakpyt/telegrambot/context/SpringContext.kt#L15).
- Move [http client](https://github.com/DEHuckaKpyT/telegram-bot/blob/v0.7.13b/telegram-bot-core/src/main/kotlin/io/github/dehuckakpyt/telegrambot/TelegramBotImpl.kt#L44) to other class.

## Medium priority
- Add more tests.
- Add webhook.
- Complete documentation in code (classes and methods).

## Low priority
- Figure out how to send messages longer than 4096 characters.
- Figure out how to implement internationalization.
- Add ability to specify action ('typing...', etc.).
- Make cleanHtml and escapeHtml methods disabled in templates.
- Make parser from [json](https://github.com/ark0f/tg-bot-api) to classes and methods.