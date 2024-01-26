package io.github.dehuckakpyt.telegrambot.model.type.supplement.content


/**
 * Created on 04.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface Content {
    public val name: String get() = "content"
    public abstract val byteArray: ByteArray
}

public class ResourceContent(
    path: String,
) : Content {
    public override val byteArray: ByteArray = javaClass.getResource(path)?.readBytes()
        ?: throw IllegalArgumentException("Resource not found for path '$path'.")
}

public class ByteArrayContent(
    public override val byteArray: ByteArray,
) : Content