package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.List

/**
 * This object contains full information about a chat.
 *
 * @see [ChatFullInfo] (https://core.telegram.org/bots/api/#chatfullinfo)
 *
 * @author KScript
 *
 * @param id Unique identifier for this chat. This number may have more than 32 significant bits and
 * some programming languages may have difficulty/silent defects in interpreting it. But it has at most
 * 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing
 * this identifier.
 * @param type Type of the chat, can be either “private”, “group”, “supergroup” or “channel”
 * @param title *Optional*. Title, for supergroups, channels and group chats
 * @param username *Optional*. Username, for private chats, supergroups and channels if available
 * @param firstName *Optional*. First name of the other party in a private chat
 * @param lastName *Optional*. Last name of the other party in a private chat
 * @param isForum *Optional*. *True*, if the supergroup chat is a forum (has
 * [topics](https://telegram.org/blog/topics-in-groups-collectible-usernames#topics-in-groups) enabled)
 * @param accentColorId Identifier of the accent color for the chat name and backgrounds of the chat
 * photo, reply header, and link preview. See [accent
 * colors](https://core.telegram.org/bots/api/#accent-colors) for more details.
 * @param maxReactionCount The maximum number of reactions that can be set on a message in the chat
 * @param photo *Optional*. Chat photo
 * @param activeUsernames *Optional*. If non-empty, the list of all [active chat
 * usernames](https://telegram.org/blog/topics-in-groups-collectible-usernames#collectible-usernames);
 * for private chats, supergroups and channels
 * @param birthdate *Optional*. For private chats, the date of birth of the user
 * @param businessIntro *Optional*. For private chats with business accounts, the intro of the
 * business
 * @param businessLocation *Optional*. For private chats with business accounts, the location of the
 * business
 * @param businessOpeningHours *Optional*. For private chats with business accounts, the opening
 * hours of the business
 * @param personalChat *Optional*. For private chats, the personal channel of the user
 * @param availableReactions *Optional*. List of available reactions allowed in the chat. If
 * omitted, then all [emoji reactions](https://core.telegram.org/bots/api/#reactiontypeemoji) are
 * allowed.
 * @param backgroundCustomEmojiId *Optional*. Custom emoji identifier of the emoji chosen by the
 * chat for the reply header and link preview background
 * @param profileAccentColorId *Optional*. Identifier of the accent color for the chat's profile
 * background. See [profile accent colors](https://core.telegram.org/bots/api/#profile-accent-colors)
 * for more details.
 * @param profileBackgroundCustomEmojiId *Optional*. Custom emoji identifier of the emoji chosen by
 * the chat for its profile background
 * @param emojiStatusCustomEmojiId *Optional*. Custom emoji identifier of the emoji status of the
 * chat or the other party in a private chat
 * @param emojiStatusExpirationDate *Optional*. Expiration date of the emoji status of the chat or
 * the other party in a private chat, in Unix time, if any
 * @param bio *Optional*. Bio of the other party in a private chat
 * @param hasPrivateForwards *Optional*. *True*, if privacy settings of the other party in the
 * private chat allows to use `tg://user?id=<user_id>` links only in chats with the user
 * @param hasRestrictedVoiceAndVideoMessages *Optional*. *True*, if the privacy settings of the
 * other party restrict sending voice and video note messages in the private chat
 * @param joinToSendMessages *Optional*. *True*, if users need to join the supergroup before they
 * can send messages
 * @param joinByRequest *Optional*. *True*, if all users directly joining the supergroup without
 * using an invite link need to be approved by supergroup administrators
 * @param description *Optional*. Description, for groups, supergroups and channel chats
 * @param inviteLink *Optional*. Primary invite link, for groups, supergroups and channel chats
 * @param pinnedMessage *Optional*. The most recent pinned message (by sending date)
 * @param permissions *Optional*. Default chat member permissions, for groups and supergroups
 * @param acceptedGiftTypes Information about types of gifts that are accepted by the chat or by the
 * corresponding user for private chats
 * @param canSendPaidMedia *Optional*. *True*, if paid media messages can be sent or forwarded to
 * the channel chat. The field is available only for channel chats.
 * @param slowModeDelay *Optional*. For supergroups, the minimum allowed delay between consecutive
 * messages sent by each unprivileged user; in seconds
 * @param unrestrictBoostCount *Optional*. For supergroups, the minimum number of boosts that a
 * non-administrator user needs to add in order to ignore slow mode and chat permissions
 * @param messageAutoDeleteTime *Optional*. The time after which all messages sent to the chat will
 * be automatically deleted; in seconds
 * @param hasAggressiveAntiSpamEnabled *Optional*. *True*, if aggressive anti-spam checks are
 * enabled in the supergroup. The field is only available to chat administrators.
 * @param hasHiddenMembers *Optional*. *True*, if non-administrators can only get the list of bots
 * and administrators in the chat
 * @param hasProtectedContent *Optional*. *True*, if messages from the chat can't be forwarded to
 * other chats
 * @param hasVisibleHistory *Optional*. *True*, if new chat members will have access to old
 * messages; available only to chat administrators
 * @param stickerSetName *Optional*. For supergroups, name of the group sticker set
 * @param canSetStickerSet *Optional*. *True*, if the bot can change the group sticker set
 * @param customEmojiStickerSetName *Optional*. For supergroups, the name of the group's custom
 * emoji sticker set. Custom emoji from this set can be used by all users and bots in the group.
 * @param linkedChatId *Optional*. Unique identifier for the linked chat, i.e. the discussion group
 * identifier for a channel and vice versa; for supergroups and channel chats. This identifier may be
 * greater than 32 bits and some programming languages may have difficulty/silent defects in
 * interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision
 * float type are safe for storing this identifier.
 * @param location *Optional*. For supergroups, the location to which the supergroup is connected
 */
public data class ChatFullInfo(
    /**
     * Unique identifier for this chat. This number may have more than 32 significant bits and some
     * programming languages may have difficulty/silent defects in interpreting it. But it has at most
     * 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for
     * storing this identifier.
     */
    @get:JsonProperty("id")
    @param:JsonProperty("id")
    public val id: Long,
    /**
     * Type of the chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    public val type: String,
    /**
     * *Optional*. Title, for supergroups, channels and group chats
     */
    @get:JsonProperty("title")
    @param:JsonProperty("title")
    public val title: String? = null,
    /**
     * *Optional*. Username, for private chats, supergroups and channels if available
     */
    @get:JsonProperty("username")
    @param:JsonProperty("username")
    public val username: String? = null,
    /**
     * *Optional*. First name of the other party in a private chat
     */
    @get:JsonProperty("first_name")
    @param:JsonProperty("first_name")
    public val firstName: String? = null,
    /**
     * *Optional*. Last name of the other party in a private chat
     */
    @get:JsonProperty("last_name")
    @param:JsonProperty("last_name")
    public val lastName: String? = null,
    /**
     * *Optional*. *True*, if the supergroup chat is a forum (has
     * [topics](https://telegram.org/blog/topics-in-groups-collectible-usernames#topics-in-groups)
     * enabled)
     */
    @get:JsonProperty("is_forum")
    @param:JsonProperty("is_forum")
    public val isForum: Boolean? = null,
    /**
     * Identifier of the accent color for the chat name and backgrounds of the chat photo, reply
     * header, and link preview. See [accent colors](https://core.telegram.org/bots/api/#accent-colors)
     * for more details.
     */
    @get:JsonProperty("accent_color_id")
    @param:JsonProperty("accent_color_id")
    public val accentColorId: Long,
    /**
     * The maximum number of reactions that can be set on a message in the chat
     */
    @get:JsonProperty("max_reaction_count")
    @param:JsonProperty("max_reaction_count")
    public val maxReactionCount: Int,
    /**
     * *Optional*. Chat photo
     */
    @get:JsonProperty("photo")
    @param:JsonProperty("photo")
    public val photo: ChatPhoto? = null,
    /**
     * *Optional*. If non-empty, the list of all [active chat
     * usernames](https://telegram.org/blog/topics-in-groups-collectible-usernames#collectible-usernames);
     * for private chats, supergroups and channels
     */
    @get:JsonProperty("active_usernames")
    @param:JsonProperty("active_usernames")
    public val activeUsernames: List<String>? = null,
    /**
     * *Optional*. For private chats, the date of birth of the user
     */
    @get:JsonProperty("birthdate")
    @param:JsonProperty("birthdate")
    public val birthdate: Birthdate? = null,
    /**
     * *Optional*. For private chats with business accounts, the intro of the business
     */
    @get:JsonProperty("business_intro")
    @param:JsonProperty("business_intro")
    public val businessIntro: BusinessIntro? = null,
    /**
     * *Optional*. For private chats with business accounts, the location of the business
     */
    @get:JsonProperty("business_location")
    @param:JsonProperty("business_location")
    public val businessLocation: BusinessLocation? = null,
    /**
     * *Optional*. For private chats with business accounts, the opening hours of the business
     */
    @get:JsonProperty("business_opening_hours")
    @param:JsonProperty("business_opening_hours")
    public val businessOpeningHours: BusinessOpeningHours? = null,
    /**
     * *Optional*. For private chats, the personal channel of the user
     */
    @get:JsonProperty("personal_chat")
    @param:JsonProperty("personal_chat")
    public val personalChat: Chat? = null,
    /**
     * *Optional*. List of available reactions allowed in the chat. If omitted, then all [emoji
     * reactions](https://core.telegram.org/bots/api/#reactiontypeemoji) are allowed.
     */
    @get:JsonProperty("available_reactions")
    @param:JsonProperty("available_reactions")
    public val availableReactions: List<ReactionType>? = null,
    /**
     * *Optional*. Custom emoji identifier of the emoji chosen by the chat for the reply header and
     * link preview background
     */
    @get:JsonProperty("background_custom_emoji_id")
    @param:JsonProperty("background_custom_emoji_id")
    public val backgroundCustomEmojiId: String? = null,
    /**
     * *Optional*. Identifier of the accent color for the chat's profile background. See [profile
     * accent colors](https://core.telegram.org/bots/api/#profile-accent-colors) for more details.
     */
    @get:JsonProperty("profile_accent_color_id")
    @param:JsonProperty("profile_accent_color_id")
    public val profileAccentColorId: Long? = null,
    /**
     * *Optional*. Custom emoji identifier of the emoji chosen by the chat for its profile
     * background
     */
    @get:JsonProperty("profile_background_custom_emoji_id")
    @param:JsonProperty("profile_background_custom_emoji_id")
    public val profileBackgroundCustomEmojiId: String? = null,
    /**
     * *Optional*. Custom emoji identifier of the emoji status of the chat or the other party in a
     * private chat
     */
    @get:JsonProperty("emoji_status_custom_emoji_id")
    @param:JsonProperty("emoji_status_custom_emoji_id")
    public val emojiStatusCustomEmojiId: String? = null,
    /**
     * *Optional*. Expiration date of the emoji status of the chat or the other party in a private
     * chat, in Unix time, if any
     */
    @get:JsonProperty("emoji_status_expiration_date")
    @param:JsonProperty("emoji_status_expiration_date")
    public val emojiStatusExpirationDate: Long? = null,
    /**
     * *Optional*. Bio of the other party in a private chat
     */
    @get:JsonProperty("bio")
    @param:JsonProperty("bio")
    public val bio: String? = null,
    /**
     * *Optional*. *True*, if privacy settings of the other party in the private chat allows to use
     * `tg://user?id=<user_id>` links only in chats with the user
     */
    @get:JsonProperty("has_private_forwards")
    @param:JsonProperty("has_private_forwards")
    public val hasPrivateForwards: Boolean? = null,
    /**
     * *Optional*. *True*, if the privacy settings of the other party restrict sending voice and
     * video note messages in the private chat
     */
    @get:JsonProperty("has_restricted_voice_and_video_messages")
    @param:JsonProperty("has_restricted_voice_and_video_messages")
    public val hasRestrictedVoiceAndVideoMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if users need to join the supergroup before they can send messages
     */
    @get:JsonProperty("join_to_send_messages")
    @param:JsonProperty("join_to_send_messages")
    public val joinToSendMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if all users directly joining the supergroup without using an invite link
     * need to be approved by supergroup administrators
     */
    @get:JsonProperty("join_by_request")
    @param:JsonProperty("join_by_request")
    public val joinByRequest: Boolean? = null,
    /**
     * *Optional*. Description, for groups, supergroups and channel chats
     */
    @get:JsonProperty("description")
    @param:JsonProperty("description")
    public val description: String? = null,
    /**
     * *Optional*. Primary invite link, for groups, supergroups and channel chats
     */
    @get:JsonProperty("invite_link")
    @param:JsonProperty("invite_link")
    public val inviteLink: String? = null,
    /**
     * *Optional*. The most recent pinned message (by sending date)
     */
    @get:JsonProperty("pinned_message")
    @param:JsonProperty("pinned_message")
    public val pinnedMessage: Message? = null,
    /**
     * *Optional*. Default chat member permissions, for groups and supergroups
     */
    @get:JsonProperty("permissions")
    @param:JsonProperty("permissions")
    public val permissions: ChatPermissions? = null,
    /**
     * Information about types of gifts that are accepted by the chat or by the corresponding user
     * for private chats
     */
    @get:JsonProperty("accepted_gift_types")
    @param:JsonProperty("accepted_gift_types")
    public val acceptedGiftTypes: AcceptedGiftTypes,
    /**
     * *Optional*. *True*, if paid media messages can be sent or forwarded to the channel chat. The
     * field is available only for channel chats.
     */
    @get:JsonProperty("can_send_paid_media")
    @param:JsonProperty("can_send_paid_media")
    public val canSendPaidMedia: Boolean? = null,
    /**
     * *Optional*. For supergroups, the minimum allowed delay between consecutive messages sent by
     * each unprivileged user; in seconds
     */
    @get:JsonProperty("slow_mode_delay")
    @param:JsonProperty("slow_mode_delay")
    public val slowModeDelay: Int? = null,
    /**
     * *Optional*. For supergroups, the minimum number of boosts that a non-administrator user needs
     * to add in order to ignore slow mode and chat permissions
     */
    @get:JsonProperty("unrestrict_boost_count")
    @param:JsonProperty("unrestrict_boost_count")
    public val unrestrictBoostCount: Int? = null,
    /**
     * *Optional*. The time after which all messages sent to the chat will be automatically deleted;
     * in seconds
     */
    @get:JsonProperty("message_auto_delete_time")
    @param:JsonProperty("message_auto_delete_time")
    public val messageAutoDeleteTime: Int? = null,
    /**
     * *Optional*. *True*, if aggressive anti-spam checks are enabled in the supergroup. The field
     * is only available to chat administrators.
     */
    @get:JsonProperty("has_aggressive_anti_spam_enabled")
    @param:JsonProperty("has_aggressive_anti_spam_enabled")
    public val hasAggressiveAntiSpamEnabled: Boolean? = null,
    /**
     * *Optional*. *True*, if non-administrators can only get the list of bots and administrators in
     * the chat
     */
    @get:JsonProperty("has_hidden_members")
    @param:JsonProperty("has_hidden_members")
    public val hasHiddenMembers: Boolean? = null,
    /**
     * *Optional*. *True*, if messages from the chat can't be forwarded to other chats
     */
    @get:JsonProperty("has_protected_content")
    @param:JsonProperty("has_protected_content")
    public val hasProtectedContent: Boolean? = null,
    /**
     * *Optional*. *True*, if new chat members will have access to old messages; available only to
     * chat administrators
     */
    @get:JsonProperty("has_visible_history")
    @param:JsonProperty("has_visible_history")
    public val hasVisibleHistory: Boolean? = null,
    /**
     * *Optional*. For supergroups, name of the group sticker set
     */
    @get:JsonProperty("sticker_set_name")
    @param:JsonProperty("sticker_set_name")
    public val stickerSetName: String? = null,
    /**
     * *Optional*. *True*, if the bot can change the group sticker set
     */
    @get:JsonProperty("can_set_sticker_set")
    @param:JsonProperty("can_set_sticker_set")
    public val canSetStickerSet: Boolean? = null,
    /**
     * *Optional*. For supergroups, the name of the group's custom emoji sticker set. Custom emoji
     * from this set can be used by all users and bots in the group.
     */
    @get:JsonProperty("custom_emoji_sticker_set_name")
    @param:JsonProperty("custom_emoji_sticker_set_name")
    public val customEmojiStickerSetName: String? = null,
    /**
     * *Optional*. Unique identifier for the linked chat, i.e. the discussion group identifier for a
     * channel and vice versa; for supergroups and channel chats. This identifier may be greater than
     * 32 bits and some programming languages may have difficulty/silent defects in interpreting it.
     * But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are
     * safe for storing this identifier.
     */
    @get:JsonProperty("linked_chat_id")
    @param:JsonProperty("linked_chat_id")
    public val linkedChatId: Long? = null,
    /**
     * *Optional*. For supergroups, the location to which the supergroup is connected
     */
    @get:JsonProperty("location")
    @param:JsonProperty("location")
    public val location: ChatLocation? = null,
)
