package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.converter.CallbackSerializer
import io.github.dehuckakpyt.telegrambot.converter.ContentConverter
import io.github.dehuckakpyt.telegrambot.exception.handler.ExceptionHandler
import io.github.dehuckakpyt.telegrambot.exception.handler.chain.ChainExceptionHandler
import io.github.dehuckakpyt.telegrambot.source.callback.CallbackContentSource
import io.github.dehuckakpyt.telegrambot.source.chain.ChainSource
import io.github.dehuckakpyt.telegrambot.template.MessageTemplate


/**
 * Created on 27.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
internal class UpdateReceiverActualConfigImpl : UpdateReceiverActualConfig {
    override lateinit var callbackContentSource: CallbackContentSource
    override lateinit var chainSource: ChainSource
    override lateinit var contentConverter: ContentConverter
    override lateinit var callbackSerializer: CallbackSerializer
    override lateinit var messageTemplate: MessageTemplate
    override lateinit var exceptionHandler: ExceptionHandler
    override lateinit var chainExceptionHandler: ChainExceptionHandler
}