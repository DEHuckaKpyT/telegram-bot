package io.github.dehuckakpyt.telegrambot.model.telegram.input


/**
 * Created on 02.05.2024.
 *
 * @author Denis Matytsin
 */
public class ResourceContent(
    path: String,
) : ContentInput {
    public override val byteArray: ByteArray = javaClass.getResource(path)?.readBytes()
        ?: throw IllegalArgumentException("Resource not found for path '$path'.")
}
