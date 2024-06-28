package io.github.dehuckakpyt.telegrambot.model.telegram.input


/**
 * Created on 26.01.2024.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface NamedContentInput : ContentInput {
    public override val name: String
    public override val byteArray: ByteArray
}
