package io.github.dehuckakpyt.telegrambot.model.type.supplement.content

import java.io.File


/**
 * Created on 26.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface NamedContent : Content {
    public override val name: String
    public override val byteArray: ByteArray
}

public class NamedResourceContent(
    public override val name: String,
    path: String,
) : NamedContent {
    public override val byteArray: ByteArray = javaClass.getResource(path)?.readBytes()
        ?: throw IllegalArgumentException("Resource not found for path '$path'.")
}

public class NamedFileContent(
    public override val name: String,
    file: File,
) : NamedContent {
    public constructor(file: File) : this(file.name, file)

    public override val byteArray: ByteArray = file.readBytes()
}

public class NamedByteArrayContent(
    public override val name: String,
    public override val byteArray: ByteArray,
) : NamedContent