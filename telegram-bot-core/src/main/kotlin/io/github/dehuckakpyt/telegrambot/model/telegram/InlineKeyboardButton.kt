package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * This object represents one button of an inline keyboard. Exactly one of the fields other than
 * *text*, *icon_custom_emoji_id*, and *style* must be used to specify the type of the button.
 *
 * @see [InlineKeyboardButton] (https://core.telegram.org/bots/api/#inlinekeyboardbutton)
 *
 * @author KScript
 *
 * @param text Label text on the button
 * @param iconCustomEmojiId *Optional*. Unique identifier of the custom emoji shown before the text
 * of the button. Can only be used by bots that purchased additional usernames on
 * [Fragment](https://fragment.com) or in the messages directly sent by the bot to private, group and
 * supergroup chats if the owner of the bot has a Telegram Premium subscription.
 * @param style *Optional*. Style of the button. Must be one of “danger” (red), “success” (green) or
 * “primary” (blue). If omitted, then an app-specific style is used.
 * @param url *Optional*. HTTP or tg:// URL to be opened when the button is pressed. Links
 * `tg://user?id=<user_id>` can be used to mention a user by their identifier without using a username,
 * if this is allowed by their privacy settings.
 * @param callbackData *Optional*. Data to be sent in a [callback
 * query](https://core.telegram.org/bots/api/#callbackquery) to the bot when the button is pressed,
 * 1-64 bytes
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
 * for messages sent in channel direct messages chats and on behalf of a Telegram Business account.
 * @param switchInlineQueryCurrentChat *Optional*. If set, pressing the button will insert the bot's
 * username and the specified inline query in the current chat's input field. May be empty, in which
 * case only the bot's username will be inserted.  
 *
 * This offers a quick way for the user to open your bot in inline mode in the same chat - good for
 * selecting something from multiple options. Not supported in channels and for messages sent in
 * channel direct messages chats and on behalf of a Telegram Business account.
 * @param switchInlineQueryChosenChat *Optional*. If set, pressing the button will prompt the user
 * to select one of their chats of the specified type, open that chat and insert the bot's username and
 * the specified inline query in the input field. Not supported for messages sent in channel direct
 * messages chats and on behalf of a Telegram Business account.
 * @param copyText *Optional*. Description of the button that copies the specified text to the
 * clipboard.
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
     * *Optional*. Unique identifier of the custom emoji shown before the text of the button. Can
     * only be used by bots that purchased additional usernames on [Fragment](https://fragment.com) or
     * in the messages directly sent by the bot to private, group and supergroup chats if the owner of
     * the bot has a Telegram Premium subscription.
     */
    @get:JsonProperty("icon_custom_emoji_id")
    @param:JsonProperty("icon_custom_emoji_id")
    public val iconCustomEmojiId: String? = null,
    /**
     * *Optional*. Style of the button. Must be one of “danger” (red), “success” (green) or
     * “primary” (blue). If omitted, then an app-specific style is used.
     */
    @get:JsonProperty("style")
    @param:JsonProperty("style")
    public val style: String? = null,
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
     * query](https://core.telegram.org/bots/api/#callbackquery) to the bot when the button is pressed,
     * 1-64 bytes
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
     * sent in channel direct messages chats and on behalf of a Telegram Business account.
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
     * in channel direct messages chats and on behalf of a Telegram Business account.
     */
    @get:JsonProperty("switch_inline_query_current_chat")
    @param:JsonProperty("switch_inline_query_current_chat")
    public val switchInlineQueryCurrentChat: String? = null,
    /**
     * *Optional*. If set, pressing the button will prompt the user to select one of their chats of
     * the specified type, open that chat and insert the bot's username and the specified inline query
     * in the input field. Not supported for messages sent in channel direct messages chats and on
     * behalf of a Telegram Business account.
     */
    @get:JsonProperty("switch_inline_query_chosen_chat")
    @param:JsonProperty("switch_inline_query_chosen_chat")
    public val switchInlineQueryChosenChat: SwitchInlineQueryChosenChat? = null,
    /**
     * *Optional*. Description of the button that copies the specified text to the clipboard.
     */
    @get:JsonProperty("copy_text")
    @param:JsonProperty("copy_text")
    public val copyText: CopyTextButton? = null,
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
