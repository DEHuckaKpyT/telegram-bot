package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long

/**
 * Created on 03.06.2024.
 *
 * This object defines the criteria used to request a suitable chat. Information about the selected
 * chat will be shared with the bot when the corresponding button is pressed. The bot will be granted
 * requested rights in the chat if appropriate. [More about requesting chats
 * ](https://core.telegram.org/bots/features#chat-and-user-selection).
 *
 * @see [KeyboardButtonRequestChat] (https://core.telegram.org/bots/api/#keyboardbuttonrequestchat)
 *
 * @author KScript
 *
 * @param requestId Signed 32-bit identifier of the request, which will be received back in the
 * [ChatShared](https://core.telegram.org/bots/api/#chatshared) object. Must be unique within the
 * message
 * @param chatIsChannel Pass *True* to request a channel chat, pass *False* to request a group or a
 * supergroup chat.
 * @param chatIsForum *Optional*. Pass *True* to request a forum supergroup, pass *False* to request
 * a non-forum chat. If not specified, no additional restrictions are applied.
 * @param chatHasUsername *Optional*. Pass *True* to request a supergroup or a channel with a
 * username, pass *False* to request a chat without a username. If not specified, no additional
 * restrictions are applied.
 * @param chatIsCreated *Optional*. Pass *True* to request a chat owned by the user. Otherwise, no
 * additional restrictions are applied.
 * @param userAdministratorRights *Optional*. A JSON-serialized object listing the required
 * administrator rights of the user in the chat. The rights must be a superset of
 * *bot_administrator_rights*. If not specified, no additional restrictions are applied.
 * @param botAdministratorRights *Optional*. A JSON-serialized object listing the required
 * administrator rights of the bot in the chat. The rights must be a subset of
 * *user_administrator_rights*. If not specified, no additional restrictions are applied.
 * @param botIsMember *Optional*. Pass *True* to request a chat with the bot as a member. Otherwise,
 * no additional restrictions are applied.
 * @param requestTitle *Optional*. Pass *True* to request the chat's title
 * @param requestUsername *Optional*. Pass *True* to request the chat's username
 * @param requestPhoto *Optional*. Pass *True* to request the chat's photo
 */
public data class KeyboardButtonRequestChat(
    /**
     * Signed 32-bit identifier of the request, which will be received back in the
     * [ChatShared](https://core.telegram.org/bots/api/#chatshared) object. Must be unique within the
     * message
     */
    @get:JsonProperty("request_id")
    @param:JsonProperty("request_id")
    public val requestId: Long,
    /**
     * Pass *True* to request a channel chat, pass *False* to request a group or a supergroup chat.
     */
    @get:JsonProperty("chat_is_channel")
    @param:JsonProperty("chat_is_channel")
    public val chatIsChannel: Boolean,
    /**
     * *Optional*. Pass *True* to request a forum supergroup, pass *False* to request a non-forum
     * chat. If not specified, no additional restrictions are applied.
     */
    @get:JsonProperty("chat_is_forum")
    @param:JsonProperty("chat_is_forum")
    public val chatIsForum: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request a supergroup or a channel with a username, pass *False* to
     * request a chat without a username. If not specified, no additional restrictions are applied.
     */
    @get:JsonProperty("chat_has_username")
    @param:JsonProperty("chat_has_username")
    public val chatHasUsername: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request a chat owned by the user. Otherwise, no additional
     * restrictions are applied.
     */
    @get:JsonProperty("chat_is_created")
    @param:JsonProperty("chat_is_created")
    public val chatIsCreated: Boolean? = null,
    /**
     * *Optional*. A JSON-serialized object listing the required administrator rights of the user in
     * the chat. The rights must be a superset of *bot_administrator_rights*. If not specified, no
     * additional restrictions are applied.
     */
    @get:JsonProperty("user_administrator_rights")
    @param:JsonProperty("user_administrator_rights")
    public val userAdministratorRights: ChatAdministratorRights? = null,
    /**
     * *Optional*. A JSON-serialized object listing the required administrator rights of the bot in
     * the chat. The rights must be a subset of *user_administrator_rights*. If not specified, no
     * additional restrictions are applied.
     */
    @get:JsonProperty("bot_administrator_rights")
    @param:JsonProperty("bot_administrator_rights")
    public val botAdministratorRights: ChatAdministratorRights? = null,
    /**
     * *Optional*. Pass *True* to request a chat with the bot as a member. Otherwise, no additional
     * restrictions are applied.
     */
    @get:JsonProperty("bot_is_member")
    @param:JsonProperty("bot_is_member")
    public val botIsMember: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request the chat's title
     */
    @get:JsonProperty("request_title")
    @param:JsonProperty("request_title")
    public val requestTitle: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request the chat's username
     */
    @get:JsonProperty("request_username")
    @param:JsonProperty("request_username")
    public val requestUsername: Boolean? = null,
    /**
     * *Optional*. Pass *True* to request the chat's photo
     */
    @get:JsonProperty("request_photo")
    @param:JsonProperty("request_photo")
    public val requestPhoto: Boolean? = null,
)
