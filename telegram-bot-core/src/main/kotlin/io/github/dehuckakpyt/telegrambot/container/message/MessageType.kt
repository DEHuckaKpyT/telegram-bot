package io.github.dehuckakpyt.telegrambot.container.message


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
object MessageType {
    val TEXT = TextMessageContainer::class
    val PHOTO = PhotoMessageContainer::class
    val AUDIO = AudioMessageContainer::class
    val VOICE = VoiceMessageContainer::class
    val CONTACT = ContactMessageContainer::class
    val DOCUMENT = DocumentMessageContainer::class
    val LOCATION = LocationMessageContainer::class
    val WEB_APP_DATA = WebAppDataMessageContainer::class
}