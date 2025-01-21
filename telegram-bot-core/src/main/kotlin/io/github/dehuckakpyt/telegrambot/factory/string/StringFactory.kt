package io.github.dehuckakpyt.telegrambot.factory.string


/**
 * @author Denis Matytsin
 */
public object StringFactory {

    public fun randomString(allowedChars: List<Char>, length: Int): String = randomString(allowedChars.toString(), length)

    public fun randomString(allowedChars: CharSequence, length: Int): String =
        (1..length).map { allowedChars.random() }.joinToString("")
}