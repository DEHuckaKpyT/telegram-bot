package io.github.dehuckakpyt.telegrambot.api.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.github.dehuckakpyt.telegrambot.model.telegram.input.StringInput


/**
 * Created on 04.06.2024.
 *
 * @author Denis Matytsin
 */
internal class StringInputSerializer @JvmOverloads constructor(clazz: Class<StringInput>? = null) : StdSerializer<StringInput>(clazz) {

    override fun serialize(value: StringInput, generator: JsonGenerator, provider: SerializerProvider) {
        generator.writeString(value.fileId)
    }
}