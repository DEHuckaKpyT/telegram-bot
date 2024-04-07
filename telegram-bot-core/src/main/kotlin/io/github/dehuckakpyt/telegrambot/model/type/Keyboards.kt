package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
public sealed class ReplyKeyboard

public data class ReplyKeyboardMarkup(
    @get:JsonProperty("keyboard") val keyboard: List<List<KeyboardButton>>,
    @get:JsonProperty("is_persistent") val isPersistent: Boolean? = null,
    @get:JsonProperty("resize_keyboard") val resizeKeyboard: Boolean? = null,
    @get:JsonProperty("one_time_keyboard") val oneTimeKeyboard: Boolean? = null,
    @get:JsonProperty("input_field_placeholder") val inputFieldPlaceholder: String? = null,
    @get:JsonProperty("selective") val selective: Boolean? = null,
) : ReplyKeyboard()

public data class KeyboardButton(
    @get:JsonProperty("text") val text: String,
    @get:JsonProperty("request_users") val requestUsers: KeyboardButtonRequestUsers? = null,
    @get:JsonProperty("request_chat") val requestChat: KeyboardButtonRequestChat? = null,
    @get:JsonProperty("request_contact") val requestContact: Boolean? = null,
    @get:JsonProperty("request_location") val requestLocation: Boolean? = null,
    @get:JsonProperty("request_poll") val requestPoll: KeyboardButtonPollType? = null,
    @get:JsonProperty("web_app") val webApp: WebAppInfo? = null,
)

public data class KeyboardButtonRequestUsers(
    @get:JsonProperty("request_id") val requestId: Int,
    @get:JsonProperty("user_is_bot") val userIsBot: Boolean? = null,
    @get:JsonProperty("user_is_premium") val userIsPremium: Boolean? = null,
    @get:JsonProperty("max_quantity") val maxQuantity: Int? = null,
    @get:JsonProperty("request_name") val requestName: Boolean? = null,
    @get:JsonProperty("request_username") val requestUsername: Boolean? = null,
    @get:JsonProperty("request_photo") val requestPhoto: Boolean? = null,
)

public data class KeyboardButtonRequestChat(
    @get:JsonProperty("request_id") val requestId: Int,
    @get:JsonProperty("chat_is_channel") val chatIsChannel: Boolean,
    @get:JsonProperty("chat_is_forum") val chatIsForum: Boolean? = null,
    @get:JsonProperty("chat_has_username") val chatHasUsername: Boolean? = null,
    @get:JsonProperty("chat_is_created") val chatIsCreated: Boolean? = null,
    @get:JsonProperty("user_administrator_rights") val userAdministratorRights: ChatAdministratorRights? = null,
    @get:JsonProperty("bot_administrator_rights") val botAdministratorRights: ChatAdministratorRights? = null,
    @get:JsonProperty("bot_is_member") val botIsMember: Boolean? = null,
    @get:JsonProperty("request_title") val requestTitle: Boolean? = null,
    @get:JsonProperty("request_username") val requestUsername: Boolean? = null,
    @get:JsonProperty("request_photo") val requestPhoto: Boolean? = null,
)

public class KeyboardButtonPollType(
    @get:JsonProperty("type")
    public val type: Type,
) {
    public enum class Type {
        @field:JsonProperty("quiz")
        Quiz,

        @field:JsonProperty("regular")
        Regular,

        @field:JsonProperty("any")
        Any,
    }
}

public data class ReplyKeyboardRemove(
    @get:JsonProperty("remove_keyboard") val removeKeyboard: Boolean,
    @get:JsonProperty("selective") val selective: Boolean? = null,
) : ReplyKeyboard()

public data class InlineKeyboardMarkup(
    @get:JsonProperty("inline_keyboard") val inlineKeyboard: List<List<InlineKeyboardButton>>,
) : ReplyKeyboard()

public data class InlineKeyboardButton(
    @get:JsonProperty("text") val text: String,
    @get:JsonProperty("url") val url: String? = null,
    @get:JsonProperty("login_url") val loginUrl: LoginUrl? = null,
    @get:JsonProperty("callback_data") val callbackData: String? = null,
    @get:JsonProperty("web_app") val webApp: WebAppInfo? = null,
    @get:JsonProperty("switch_inline_query") val switchInlineQuery: String? = null,
    @get:JsonProperty("switch_inline_query_current_chat") val switchInlineQueryCurrentChat: String? = null,
    @get:JsonProperty("switch_inline_query_chosen_chat") val switchInlineQueryChosenChat: SwitchInlineQueryChosenChat? = null,
    @get:JsonProperty("callback_game") val callbackGame: CallbackGame? = null,
    @get:JsonProperty("pay") val pay: Boolean? = null,
)

public data class ForceReply(
    @get:JsonProperty("force_reply") val forceReply: Boolean,
    @get:JsonProperty("input_field_placeholder") val inputFieldPlaceholder: String? = null,
    @get:JsonProperty("selective") val selective: Boolean? = null,
) : ReplyKeyboard()

public data class SwitchInlineQueryChosenChat(
    @get:JsonProperty("query") val query: String? = null,
    @get:JsonProperty("allow_user_chats") val allowUserChats: Boolean? = null,
    @get:JsonProperty("allow_bot_chats") val allowBotChats: Boolean? = null,
    @get:JsonProperty("allow_group_chats") val allowGroupChats: Boolean? = null,
    @get:JsonProperty("allow_channel_chats") val allowChannelChats: Boolean? = null,
)