package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Long
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object represents a Telegram user or bot.
 *
 * @see [User] (https://core.telegram.org/bots/api/#user)
 *
 * @author KScript
 *
 * @param id Unique identifier for this user or bot. This number may have more than 32 significant
 * bits and some programming languages may have difficulty/silent defects in interpreting it. But it
 * has at most 52 significant bits, so a 64-bit integer or double-precision float type are safe for
 * storing this identifier.
 * @param isBot *True*, if this user is a bot
 * @param firstName User's or bot's first name
 * @param lastName *Optional*. User's or bot's last name
 * @param username *Optional*. User's or bot's username
 * @param languageCode *Optional*. [IETF language
 * tag](https://en.wikipedia.org/wiki/IETF_language_tag) of the user's language
 * @param isPremium *Optional*. *True*, if this user is a Telegram Premium user
 * @param addedToAttachmentMenu *Optional*. *True*, if this user added the bot to the attachment
 * menu
 * @param canJoinGroups *Optional*. *True*, if the bot can be invited to groups. Returned only in
 * [getMe](https://core.telegram.org/bots/api/#getme).
 * @param canReadAllGroupMessages *Optional*. *True*, if [privacy
 * mode](https://core.telegram.org/bots/features#privacy-mode) is disabled for the bot. Returned only
 * in [getMe](https://core.telegram.org/bots/api/#getme).
 * @param supportsInlineQueries *Optional*. *True*, if the bot supports inline queries. Returned
 * only in [getMe](https://core.telegram.org/bots/api/#getme).
 * @param canConnectToBusiness *Optional*. *True*, if the bot can be connected to a Telegram
 * Business account to receive its messages. Returned only in
 * [getMe](https://core.telegram.org/bots/api/#getme).
 */
public data class User(
    /**
     * Unique identifier for this user or bot. This number may have more than 32 significant bits
     * and some programming languages may have difficulty/silent defects in interpreting it. But it has
     * at most 52 significant bits, so a 64-bit integer or double-precision float type are safe for
     * storing this identifier.
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: Long,
    /**
     * *True*, if this user is a bot
     */
    @get:JsonProperty("is_bot")
    @param:JsonProperty("is_bot")
    public val isBot: Boolean,
    /**
     * User's or bot's first name
     */
    @get:JsonProperty("first_name")
    @param:JsonProperty("first_name")
    public val firstName: String,
    /**
     * *Optional*. User's or bot's last name
     */
    @get:JsonProperty("last_name")
    @param:JsonProperty("last_name")
    public val lastName: String? = null,
    /**
     * *Optional*. User's or bot's username
     */
    @get:JsonProperty("username")
    @param:JsonProperty("username")
    public val username: String? = null,
    /**
     * *Optional*. [IETF language tag](https://en.wikipedia.org/wiki/IETF_language_tag) of the
     * user's language
     */
    @get:JsonProperty("language_code")
    @param:JsonProperty("language_code")
    public val languageCode: String? = null,
    /**
     * *Optional*. *True*, if this user is a Telegram Premium user
     */
    @get:JsonProperty("is_premium")
    @param:JsonProperty("is_premium")
    public val isPremium: Boolean? = null,
    /**
     * *Optional*. *True*, if this user added the bot to the attachment menu
     */
    @get:JsonProperty("added_to_attachment_menu")
    @param:JsonProperty("added_to_attachment_menu")
    public val addedToAttachmentMenu: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can be invited to groups. Returned only in
     * [getMe](https://core.telegram.org/bots/api/#getme).
     */
    @get:JsonProperty("can_join_groups")
    @param:JsonProperty("can_join_groups")
    public val canJoinGroups: Boolean? = null,
    /**
     * *Optional*. *True*, if [privacy mode](https://core.telegram.org/bots/features#privacy-mode)
     * is disabled for the bot. Returned only in [getMe](https://core.telegram.org/bots/api/#getme).
     */
    @get:JsonProperty("can_read_all_group_messages")
    @param:JsonProperty("can_read_all_group_messages")
    public val canReadAllGroupMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot supports inline queries. Returned only in
     * [getMe](https://core.telegram.org/bots/api/#getme).
     */
    @get:JsonProperty("supports_inline_queries")
    @param:JsonProperty("supports_inline_queries")
    public val supportsInlineQueries: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can be connected to a Telegram Business account to receive its
     * messages. Returned only in [getMe](https://core.telegram.org/bots/api/#getme).
     */
    @get:JsonProperty("can_connect_to_business")
    @param:JsonProperty("can_connect_to_business")
    public val canConnectToBusiness: Boolean? = null,
)
