package io.github.dehuckakpyt.telegrambot.ext.event.listening

import io.github.dehuckakpyt.telegrambot.event.listening.TelegramBotEventListening
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import io.github.dehuckakpyt.telegrambot.model.telegram.PhotoSize
import io.github.dehuckakpyt.telegrambot.source.message.MessageSource


/**
 * Created on 23.12.2024.
 *
 * @author Denis Matytsin
 */
public typealias after = After

public object After

public class AfterMethod(internal val methodName: String)

internal fun TelegramBotEventListening.defaults(messageSource: MessageSource) {
    after method "sendMessage" called { args ->
        val text: String by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "TEXT", text = text)
    }

    after method "sendPhoto" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "PHOTO", text = caption, fileIds = returnedValue.photo!!.map(PhotoSize::fileId))
    }

    after method "sendAudio" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "AUDIO", text = caption, fileIds = listOf(returnedValue.audio!!.fileId))
    }

    after method "sendDocument" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "DOCUMENT", text = caption, fileIds = listOf(returnedValue.document!!.fileId))
    }

    after method "sendVideo" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "VIDEO", text = caption, fileIds = listOf(returnedValue.video!!.fileId))
    }

    after method "sendAnimation" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "ANIMATION", text = caption, fileIds = listOf(returnedValue.animation!!.fileId))
    }

    after method "sendVoice" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "VOICE", text = caption, fileIds = listOf(returnedValue.voice!!.fileId))
    }

    after method "sendVideoNote" called { args ->
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "VIDEO_NOTE", fileIds = listOf(returnedValue.videoNote!!.fileId))
    }

    after method "sendPaidMedia" called { args ->
        val caption: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "PAID_MEDIA", text = caption)
    }

    after method "sendMediaGroup" called { args ->
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "MEDIA_GROUP")
    }

    after method "sendLocation" called { args ->
        val latitude: Double by args
        val longitude: Double by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "LOCATION", text = "latitude = $latitude, longitude = $longitude")
    }

    after method "sendVenue" called { args ->
        val latitude: Double by args
        val longitude: Double by args
        val title: String by args
        val address: String by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "VENUE", text = "latitude = $latitude, longitude = $longitude, title = $title, address = $address")
    }

    after method "sendContact" called { args ->
        val phoneNumber: String by args
        val firstName: String by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "CONTACT", text = "phoneNumber = $phoneNumber, firstName = $firstName")
    }

    after method "sendPoll" called { args ->
        val question: String by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "POLL", text = question)
    }

    after method "sendDice" called { args ->
        val emoji: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "DICE", text = emoji)
    }

    after method "editMessageText" called { args ->
        val returnedValue = args["returnedValue"] as? Message ?: return@called

        val text: String by args

        messageSource.save(message = returnedValue, fromBot = true, type = "EDIT_TEXT", text = text)
    }

    after method "editMessageCaption" called { args ->
        val returnedValue = args["returnedValue"] as? Message ?: return@called

        val caption: String? by args

        messageSource.save(message = returnedValue, fromBot = true, type = "EDIT_CAPTION", text = caption)
    }

    after method "editMessageLiveLocation" called { args ->
        val returnedValue = args["returnedValue"] as? Message ?: return@called

        val latitude: Double by args
        val longitude: Double by args

        messageSource.save(message = returnedValue, fromBot = true, type = "EDIT_LIVE_LOCATION", text = "latitude = $latitude, longitude = $longitude")
    }

    after method "stopMessageLiveLocation" called { args ->
        val returnedValue = args["returnedValue"] as? Message ?: return@called

        messageSource.save(message = returnedValue, fromBot = true, type = "STOP_LIVE_LOCATION")
    }

    after method "editMessageReplyMarkup" called { args ->
        val returnedValue = args["returnedValue"] as? Message ?: return@called

        messageSource.save(message = returnedValue, fromBot = true, type = "EDIT_REPLY_MARKUP")
    }

    after method "sendSticker" called { args ->
        val emoji: String? by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "STICKER", text = emoji)
    }

    after method "sendInvoice" called { args ->
        val title: String by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "INVOICE", text = title)
    }

    after method "sendGame" called { args ->
        val gameShortName: String by args
        val returnedValue: Message by args

        messageSource.save(message = returnedValue, fromBot = true, type = "GAME", text = gameShortName)
    }

    after method "setGameScore" called { args ->
        val returnedValue = args["returnedValue"] as? Message ?: return@called

        val userId: Long by args
        val score: Int by args

        messageSource.save(message = returnedValue, fromBot = true, type = "GAME_SCORE", text = "userId = $userId, score = $score")
    }
}