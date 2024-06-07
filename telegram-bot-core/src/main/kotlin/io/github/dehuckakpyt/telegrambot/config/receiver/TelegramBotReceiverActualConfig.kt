package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.strategy.invocation.HandlerInvocationStrategy
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramBotReceiverActualConfig {
    val callbackContentSource: CallbackContentSource
    val chainSource: ChainSource
    val contentConverter: ContentConverter
    val callbackSerializer: CallbackSerializer
    val messageTemplate: MessageTemplate
    val invocationStrategy: HandlerInvocationStrategy
    val exceptionHandler: ExceptionHandler
    val chainExceptionHandler: ChainExceptionHandler
}