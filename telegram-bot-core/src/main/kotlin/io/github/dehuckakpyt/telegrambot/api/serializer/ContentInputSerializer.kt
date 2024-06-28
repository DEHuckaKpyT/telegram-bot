package io.github.dehuckakpyt.telegrambot.api.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput


/**
 * Created on 04.06.2024.
 *
 * @author Denis Matytsin
 */
internal class ContentInputSerializer @JvmOverloads constructor(clazz: Class<ContentInput>? = null) : StdSerializer<ContentInput>(clazz) {

    override fun serialize(value: ContentInput, generator: JsonGenerator, provider: SerializerProvider) {
        generator.writeString("attach://${value.name}")
    }
}