package io.github.dehuckakpyt.telegrambot.template

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * Created on 26.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
@ConfigurationProperties("telegram-bot.template")
data class SpringMessageTemplate(
    override var whenCommandNotFound: String = "An unknown command \${command} has been entered. You can view the possible actions by invoking the /help command.",
    override var whenUnexpectedMessageType: String = "A different message type is pending.",
    override var whenStepNotFound: String = "Unexpected message. You can view possible actions by calling /help.",
    override var whenKnownException: String = "\${message}",
    override var whenUnknownException: String = "An unexpected error occurred. Please contact the developer.",
) : MessageTemplate()