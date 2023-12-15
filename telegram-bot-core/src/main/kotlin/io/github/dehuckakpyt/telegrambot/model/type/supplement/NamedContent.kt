package io.github.dehuckakpyt.telegrambot.model.type.supplement

import java.io.File


/**
 * Created on 04.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface NamedContent {
    public abstract val fileName: String
    public abstract val byteArray: ByteArray
}

public class NamedResourceContent(
    public override val fileName: String,
    public val path: String
) : NamedContent {
    public override val byteArray: ByteArray get() = javaClass.getResource(path)!!.readBytes()
}

public class NamedFileContent(
    public override val fileName: String,
    public val file: File
) : NamedContent {
    public override val byteArray: ByteArray get() = file.readBytes()
}

public class NamedByteArrayContent(
    public override val fileName: String,
    public override val byteArray: ByteArray
) : NamedContent

public class NamedStringContent(
    public override val fileName: String,
    public val string: String
) : NamedContent {
    public override val byteArray: ByteArray get() = string.toByteArray()
}