package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.BotChaining


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal val BotChaining.whenCommandNotFoundTemplate by template(
    "when-command-not-found",
    "Введена неизвестная команда `\${command}`\\. Посмотреть возможные действия можно, вызвав команду /help\\."
)
internal val BotChaining.whenKnownErrorTemplate by template(
    "when-known-error",
    "\${message}"
)
internal val BotChaining.whenUnknownErrorTemplate by template(
    "when-unknown-error",
    "Произошла непредвиденная ошибка. Обратитесь к разработчику."
)
internal val BotChaining.whenUnexpectedMessageTypeTemplate by template(
    "when-unexpected-message-type",
    "Неподходящий тип сообщения.\nОжидаемые типы: \${expectedMessageNames}"
)
internal val BotChaining.whenStepNotFoundTemplate by template(
    "when-step-not-found",
    "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help."
)