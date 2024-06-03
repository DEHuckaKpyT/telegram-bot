package io.github.dehuckakpyt.telegrambot.model.telegram

/**
 * Created on 03.06.2024.
 *
 * This object represents the content of a message to be sent as a result of an inline query.
 * Telegram clients currently support the following 5 types:
 *
 * * [InputTextMessageContent](https://core.telegram.org/bots/api/#inputtextmessagecontent)
 * * [InputLocationMessageContent](https://core.telegram.org/bots/api/#inputlocationmessagecontent)
 * * [InputVenueMessageContent](https://core.telegram.org/bots/api/#inputvenuemessagecontent)
 * * [InputContactMessageContent](https://core.telegram.org/bots/api/#inputcontactmessagecontent)
 * * [InputInvoiceMessageContent](https://core.telegram.org/bots/api/#inputinvoicemessagecontent)
 *
 * @see [InputMessageContent] (https://core.telegram.org/bots/api/#inputmessagecontent)
 *
 * @author KScript
 */
public sealed interface InputMessageContent
