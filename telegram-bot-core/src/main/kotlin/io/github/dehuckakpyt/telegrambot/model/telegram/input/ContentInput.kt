package io.github.dehuckakpyt.telegrambot.model.telegram.input


/**
 * Created on 04.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
public interface ContentInput : Input {
    public val name: String get() = "content"
    public abstract val byteArray: ByteArray
}
