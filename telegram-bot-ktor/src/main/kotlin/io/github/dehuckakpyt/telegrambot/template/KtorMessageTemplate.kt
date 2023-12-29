package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.factory.template.TemplateFactory.property


/**
 * Created on 26.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class KtorMessageTemplate : MessageTemplate() {
    override val whenCommandNotFound: String by property("when-command-not-found", "Введена неизвестная команда \${command}. Посмотреть возможные действия можно, вызвав команду /help.")
    override val whenUnexpectedMessageType: String by property("when-unexpected-message-type", "Ожидается сообщение другого типа.")
    override val whenStepNotFound: String by property("when-step-not-found", "Неожидаемое сообщение. Посмотреть возможные действия можно, вызвав команду /help.")
    override val whenKnownException: String by property("when-known-error", "\${message}")
    override val whenUnknownException: String by property("when-unknown-error", "Произошла непредвиденная ошибка. Обратитесь к разработчику.")
}