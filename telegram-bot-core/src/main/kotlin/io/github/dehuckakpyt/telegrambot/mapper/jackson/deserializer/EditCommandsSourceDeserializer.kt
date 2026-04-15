package io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
import io.github.dehuckakpyt.telegrambot.config.properties.TelegramBotProperties.EditProperties.EditCommandsProperties.EditCommandsSource


/**
 * Deserializes `telegram-bot.edit.commands.source` from case-insensitive textual values.
 *
 * @author Denis Matytsin
 */
class EditCommandsSourceDeserializer : StdScalarDeserializer<EditCommandsSource>(EditCommandsSource::class.java) {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): EditCommandsSource {
        return when (parser.valueAsString) {
            "config", "CONFIG" -> EditCommandsSource.CONFIG
            "code", "CODE"     -> EditCommandsSource.CODE
            else               -> throw IllegalArgumentException("Unknown commands source: ${parser.valueAsString}")
        }
    }
}
