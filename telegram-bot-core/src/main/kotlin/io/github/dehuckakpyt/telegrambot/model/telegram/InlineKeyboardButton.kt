package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents one button of an inline keyboard. Exactly one of the optional fields must
 * be used to specify type of the button.
 *
 * @see [InlineKeyboardButton] (https://core.telegram.org/bots/api/#inlinekeyboardbutton)
 *
 * @author KScript
 *
 * @param text Label text on the button
 * @param url *Optional*. HTTP or tg:// URL to be opened when the button is pressed. Links
 * `tg://user?id=<user_id>` can be used to mention a user by their identifier without using a username,
 * if this is allowed by their privacy settings.
 * @param callbackData *Optional*. Data to be sent in a [callback
 * query](https://core.telegram.org/bots/api/#callbackquery) to the bot when button is pressed, 1-64
 * bytes. Not supported for messages sent on behalf of a Telegram Business account.
 * @param webApp *Optional*. Description of the [Web App](https://core.telegram.org/bots/webapps)
 * that will be launched when the user presses the button. The Web App will be able to send an
 * arbitrary message on behalf of the user using the method
 * [answerWebAppQuery](https://core.telegram.org/bots/api/#answerwebappquery). Available only in
 * private chats between a user and the bot. Not supported for messages sent on behalf of a Telegram
 * Business account.
 * @param loginUrl *Optional*. An HTTPS URL used to automatically authorize the user. Can be used as
 * a replacement for the [Telegram Login Widget](https://core.telegram.org/widgets/login).
 * @param switchInlineQuery *Optional*. If set, pressing the button will prompt the user to select
 * one of their chats, open that chat and insert the bot's username and the specified inline query in
 * the input field. May be empty, in which case just the bot's username will be inserted. Not supported
 * for messages sent on behalf of a Telegram Business account.
 * @param switchInlineQueryCurrentChat *Optional*. If set, pressing the button will insert the bot's
 * username and the specified inline query in the current chat's input field. May be empty, in which
 * case only the bot's username will be inserted.  
 *
 * This offers a quick way for the user to open your bot in inline mode in the same chat - good for
 * selecting something from multiple options. Not supported in channels and for messages sent on behalf
 * of a Telegram Business account.
 * @param switchInlineQueryChosenChat *Optional*. If set, pressing the button will prompt the user
 * to select one of their chats of the specified type, open that chat and insert the bot's username and
 * the specified inline query in the input field. Not supported for messages sent on behalf of a
 * Telegram Business account.
 * @param callbackGame *Optional*. Description of the game that will be launched when the user
 * presses the button.  
 *
 * **NOTE:** This type of button **must** always be the first button in the first row.
 * @param pay *Optional*. Specify *True*, to send a [Pay
 * button](https://core.telegram.org/bots/api/#payments). Substrings “⭐” and “XTR” in the buttons's
 * text will be replaced with a Telegram Star icon.  
 *
 * **NOTE:** This type of button **must** always be the first button in the first row and can only
 * be used in invoice messages.
 */
public data class InlineKeyboardButton(
    /**
     * Label text on the button
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: String,
    /**
     * *Optional*. HTTP or tg:// URL to be opened when the button is pressed. Links
     * `tg://user?id=<user_id>` can be used to mention a user by their identifier without using a
     * username, if this is allowed by their privacy settings.
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String? = null,
    /**
     * *Optional*. Data to be sent in a [callback
     * query](https://core.telegram.org/bots/api/#callbackquery) to the bot when button is pressed,
     * 1-64 bytes. Not supported for messages sent on behalf of a Telegram Business account.
     */
    @get:JsonProperty("callback_data")
    @param:JsonProperty("callback_data")
    public val callbackData: String? = null,
    /**
     * *Optional*. Description of the [Web App](https://core.telegram.org/bots/webapps) that will be
     * launched when the user presses the button. The Web App will be able to send an arbitrary message
     * on behalf of the user using the method
     * [answerWebAppQuery](https://core.telegram.org/bots/api/#answerwebappquery). Available only in
     * private chats between a user and the bot. Not supported for messages sent on behalf of a
     * Telegram Business account.
     */
    @get:JsonProperty("web_app")
    @param:JsonProperty("web_app")
    public val webApp: WebAppInfo? = null,
    /**
     * *Optional*. An HTTPS URL used to automatically authorize the user. Can be used as a
     * replacement for the [Telegram Login Widget](https://core.telegram.org/widgets/login).
     */
    @get:JsonProperty("login_url")
    @param:JsonProperty("login_url")
    public val loginUrl: LoginUrl? = null,
    /**
     * *Optional*. If set, pressing the button will prompt the user to select one of their chats,
     * open that chat and insert the bot's username and the specified inline query in the input field.
     * May be empty, in which case just the bot's username will be inserted. Not supported for messages
     * sent on behalf of a Telegram Business account.
     */
    @get:JsonProperty("switch_inline_query")
    @param:JsonProperty("switch_inline_query")
    public val switchInlineQuery: String? = null,
    /**
     * *Optional*. If set, pressing the button will insert the bot's username and the specified
     * inline query in the current chat's input field. May be empty, in which case only the bot's
     * username will be inserted.  
     *
     * This offers a quick way for the user to open your bot in inline mode in the same chat - good
     * for selecting something from multiple options. Not supported in channels and for messages sent
     * on behalf of a Telegram Business account.
     */
    @get:JsonProperty("switch_inline_query_current_chat")
    @param:JsonProperty("switch_inline_query_current_chat")
    public val switchInlineQueryCurrentChat: String? = null,
    /**
     * *Optional*. If set, pressing the button will prompt the user to select one of their chats of
     * the specified type, open that chat and insert the bot's username and the specified inline query
     * in the input field. Not supported for messages sent on behalf of a Telegram Business account.
     */
    @get:JsonProperty("switch_inline_query_chosen_chat")
    @param:JsonProperty("switch_inline_query_chosen_chat")
    public val switchInlineQueryChosenChat: SwitchInlineQueryChosenChat? = null,
    /**
     * *Optional*. Description of the game that will be launched when the user presses the button.  
     *
     * **NOTE:** This type of button **must** always be the first button in the first row.
     */
    @get:JsonProperty("callback_game")
    @param:JsonProperty("callback_game")
    public val callbackGame: CallbackGame? = null,
    /**
     * *Optional*. Specify *True*, to send a [Pay
     * button](https://core.telegram.org/bots/api/#payments). Substrings “⭐” and “XTR” in the buttons's
     * text will be replaced with a Telegram Star icon.  
     *
     * **NOTE:** This type of button **must** always be the first button in the first row and can
     * only be used in invoice messages.
     */
    @get:JsonProperty("pay")
    @param:JsonProperty("pay")
    public val pay: Boolean? = null,
)
