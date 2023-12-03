package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler


/**
 * Created on 21.08.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal val ChainExceptionHandler.whenCommandNotFoundTemplate by template(
    "when-command-not-found",
    "Введена неизвестная команда `\${command}`\\. Посмотреть возможные действия можно, вызвав команду /help\\."
)
internal val ChainExceptionHandler.whenUnexpectedMessageTypeTemplate by template(
    "when-unexpected-message-type",
    "Ожидается сообщение другого типа."
)
internal val ChainExceptionHandler.whenStepNotFoundTemplate by template(
    "when-step-not-found",
    "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help."
)

internal val ExceptionHandler.whenKnownErrorTemplate by template(
    "when-known-error",
    "\${message}"
)
internal val ExceptionHandler.whenUnknownErrorTemplate by template(
    "when-unknown-error",
    "Произошла непредвиденная ошибка. Обратитесь к разработчику."
)