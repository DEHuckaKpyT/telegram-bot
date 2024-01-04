package io.github.dehuckakpyt.telegrambot.argument.message


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object MessageType {
    val TEXT = TextMessageArgument::class
    val PHOTO = PhotoMessageArgument::class
    val AUDIO = AudioMessageArgument::class
    val VOICE = VoiceMessageArgument::class
    val CONTACT = ContactMessageArgument::class
    val DOCUMENT = DocumentMessageArgument::class
}