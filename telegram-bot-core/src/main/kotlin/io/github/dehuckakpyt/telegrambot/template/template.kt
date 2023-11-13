package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.advise.ChainExceptionAdvice
import io.github.dehuckakpyt.telegrambot.resolver.UpdateResolver


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal val ChainExceptionAdvice.whenCommandNotFoundTemplate by template(
    "when-command-not-found",
    "Введена неизвестная команда `\${command}`\\. Посмотреть возможные действия можно, вызвав команду /help\\."
)
internal val UpdateResolver.whenKnownErrorTemplate by template(
    "when-known-error",
    "\${message}"
)
internal val UpdateResolver.whenUnknownErrorTemplate by template(
    "when-unknown-error",
    "Произошла непредвиденная ошибка. Обратитесь к разработчику."
)
internal val ChainExceptionAdvice.whenUnexpectedMessageTypeTemplate by template(
    "when-unexpected-message-type",
    "Неподходящий тип сообщения.\nОжидаемые типы: \${expectedMessageNames}"
)
internal val ChainExceptionAdvice.whenStepNotFoundTemplate by template(
    "when-step-not-found",
    "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help."
)