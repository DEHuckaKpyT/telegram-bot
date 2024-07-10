package io.github.dehuckakpyt.telegrambot.template

import io.github.dehuckakpyt.telegrambot.factory.template.TemplateFactory.property


/**
 * Created on 26.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
class KtorMessageTemplate : MessageTemplate() {
    override val whenCommandNotFound: String by property("when-command-not-found", "An unknown command \${command} has been entered. You can view the possible actions by invoking the /help command.")
    override val whenUnexpectedMessageType: String by property("when-unexpected-message-type", "A different message type is pending.")
    override val whenStepNotFound: String by property("when-step-not-found", "Unexpected message. You can view possible actions by calling /help.")
    override val whenKnownException: String by property("when-known-error", "\${message}")
    override val whenUnknownException: String by property("when-unknown-error", "An unexpected error occurred. Please contact the developer.")
}