package io.github.dehuckakpyt.telegrambot.model.type.supplement.input

import java.io.File


/**
 * Created on 02.05.2024.
 *
 * @author Denis Matytsin
 */
public class NamedFileContent(
    public override val name: String,
    file: File,
) : NamedContentInput {
    public constructor(file: File) : this(file.name, file)

    public override val byteArray: ByteArray = file.readBytes()
}
