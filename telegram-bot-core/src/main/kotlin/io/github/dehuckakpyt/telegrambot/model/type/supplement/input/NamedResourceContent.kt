package io.github.dehuckakpyt.telegrambot.model.type.supplement.input


/**
 * Created on 02.05.2024.
 *
 * @author Denis Matytsin
 */
public class NamedResourceContent(
    public override val name: String,
    path: String,
) : NamedContentInput {
    public override val byteArray: ByteArray = javaClass.getResource(path)?.readBytes()
        ?: throw IllegalArgumentException("Resource not found for path '$path'.")
}
