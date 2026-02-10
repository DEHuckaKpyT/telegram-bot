package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * This object represents one button of the reply keyboard. At most one of the fields other than
 * *text*, *icon_custom_emoji_id*, and *style* must be used to specify the type of the button. For
 * simple text buttons, *String* can be used instead of this object to specify the button text.
 *
 * @see [KeyboardButton] (https://core.telegram.org/bots/api/#keyboardbutton)
 *
 * @author KScript
 *
 * @param text Text of the button. If none of the fields other than *text*, *icon_custom_emoji_id*,
 * and *style* are used, it will be sent as a message when the button is pressed
 * @param iconCustomEmojiId *Optional*. Unique identifier of the custom emoji shown before the text
 * of the button. Can only be used by bots that purchased additional usernames on
 * [Fragment](https://fragment.com) or in the messages directly sent by the bot to private, group and
 * supergroup chats if the owner of the bot has a Telegram Premium subscription.
 * @param style *Optional*. Style of the button. Must be one of “danger” (red), “success” (green) or
 * “primary” (blue). If omitted, then an app-specific style is used.
 * @param requestUsers *Optional*. If specified, pressing the button will open a list of suitable
 * users. Identifiers of selected users will be sent to the bot in a “users_shared” service message.
 * Available in private chats only.
 * @param requestChat *Optional*. If specified, pressing the button will open a list of suitable
 * chats. Tapping on a chat will send its identifier to the bot in a “chat_shared” service message.
 * Available in private chats only.
 * @param requestContact *Optional*. If *True*, the user's phone number will be sent as a contact
 * when the button is pressed. Available in private chats only.
 * @param requestLocation *Optional*. If *True*, the user's current location will be sent when the
 * button is pressed. Available in private chats only.
 * @param requestPoll *Optional*. If specified, the user will be asked to create a poll and send it
 * to the bot when the button is pressed. Available in private chats only.
 * @param webApp *Optional*. If specified, the described [Web
 * App](https://core.telegram.org/bots/webapps) will be launched when the button is pressed. The Web
 * App will be able to send a “web_app_data” service message. Available in private chats only.
 */
public data class KeyboardButton(
    /**
     * Text of the button. If none of the fields other than *text*, *icon_custom_emoji_id*, and
     * *style* are used, it will be sent as a message when the button is pressed
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
     * *Optional*. If specified, pressing the button will open a list of suitable users. Identifiers
     * of selected users will be sent to the bot in a “users_shared” service message. Available in
     * private chats only.
     */
    @get:JsonProperty("request_users")
    @param:JsonProperty("request_users")
    public val requestUsers: KeyboardButtonRequestUsers? = null,
    /**
     * *Optional*. If specified, pressing the button will open a list of suitable chats. Tapping on
     * a chat will send its identifier to the bot in a “chat_shared” service message. Available in
     * private chats only.
     */
    @get:JsonProperty("request_chat")
    @param:JsonProperty("request_chat")
    public val requestChat: KeyboardButtonRequestChat? = null,
    /**
     * *Optional*. If *True*, the user's phone number will be sent as a contact when the button is
     * pressed. Available in private chats only.
     */
    @get:JsonProperty("request_contact")
    @param:JsonProperty("request_contact")
    public val requestContact: Boolean? = null,
    /**
     * *Optional*. If *True*, the user's current location will be sent when the button is pressed.
     * Available in private chats only.
     */
    @get:JsonProperty("request_location")
    @param:JsonProperty("request_location")
    public val requestLocation: Boolean? = null,
    /**
     * *Optional*. If specified, the user will be asked to create a poll and send it to the bot when
     * the button is pressed. Available in private chats only.
     */
    @get:JsonProperty("request_poll")
    @param:JsonProperty("request_poll")
    public val requestPoll: KeyboardButtonPollType? = null,
    /**
     * *Optional*. If specified, the described [Web App](https://core.telegram.org/bots/webapps)
     * will be launched when the button is pressed. The Web App will be able to send a “web_app_data”
     * service message. Available in private chats only.
     */
    @get:JsonProperty("web_app")
    @param:JsonProperty("web_app")
    public val webApp: WebAppInfo? = null,
)
