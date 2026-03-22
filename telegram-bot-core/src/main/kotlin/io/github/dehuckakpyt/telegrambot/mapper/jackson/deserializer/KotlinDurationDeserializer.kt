package io.github.dehuckakpyt.telegrambot.mapper.jackson.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
import kotlin.time.Duration


/**
 * Converts ISO-8601 duration string (`PT10S`, `PT5M`, etc.) to Kotlin [Duration].
 * 
 * @author Denis Matytsin
 */
public class KotlinDurationDeserializer : StdScalarDeserializer<Duration>(Duration::class.java) {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): Duration {
        return Duration.parse(parser.valueAsString)
    }
}