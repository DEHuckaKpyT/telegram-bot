package io.github.dehuckakpyt.telegrambot.api

import io.github.dehuckakpyt.telegrambot.api.ex.*


/**
 * Created on 18.12.2023.
 *<p>
 *
 * @author Denis Matytsin
 */
interface TelegramApi :
    TelegramGeneralExApi,
    TelegramInlineModeApi,
    TelegramUpdatingMessagesExApi,
    TelegramStickerExApi,
    TelegramPaymentExApi,
    TelegramPassportApi,
    TelegramGameApi,
    TelegramUpdatesApi,
    TelegramChatExApi,
    TelegramReactionExApi,
    TelegramChatBoostExApi,
    TelegramBusinessApi