package io.github.dehuckakpyt.telegrambot.model.telegram.input


/**
 * Created on 02.05.2024.
 *
 * @author Denis Matytsin
 */
public class NamedByteArrayContent(
    public override val name: String,
    public override val byteArray: ByteArray,
) : NamedContentInput
