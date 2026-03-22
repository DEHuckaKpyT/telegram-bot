package io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode.LONG_POLLING
import io.github.dehuckakpyt.telegrambot.config.receiver.ReceivingMode.WEBHOOK


/**
 * @author Denis Matytsin
 */
class TelegramBotReceivingModeDeserializer : StdScalarDeserializer<ReceivingMode>(ReceivingMode::class.java) {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): ReceivingMode {
        return when (parser.valueAsString) {
            "long-polling", "long_polling", "LONG_POLLING" -> LONG_POLLING
            "webhook", "WEBHOOK"                           -> WEBHOOK
            else                                           -> throw IllegalArgumentException("Unknown receiving mode type: ${parser.valueAsString}")
        }
    }
}