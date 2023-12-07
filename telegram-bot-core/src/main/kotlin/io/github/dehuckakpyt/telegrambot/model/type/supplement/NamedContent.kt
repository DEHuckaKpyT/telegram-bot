package io.github.dehuckakpyt.telegrambot.model.type.supplement

import java.io.File


/**
 * Created on 04.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface NamedContent {
    public abstract val filaName: String
    public abstract val byteArray: ByteArray
}

public class NamedFileContent(
    public override val filaName: String,
    public val file: File
) : NamedContent {
    public override val byteArray: ByteArray get() = file.readBytes()
}

public class NamedByteArrayContent(
    public override val filaName: String,
    public override val byteArray: ByteArray
) : NamedContent

public class NamedStringContent(
    public override val filaName: String,
    public val string: String
) : NamedContent {
    public override val byteArray: ByteArray get() = string.toByteArray()
}