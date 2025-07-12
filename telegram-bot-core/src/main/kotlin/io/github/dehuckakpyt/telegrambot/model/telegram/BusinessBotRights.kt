package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean

/**
 * Represents the rights of a business bot.
 *
 * @see [BusinessBotRights] (https://core.telegram.org/bots/api/#businessbotrights)
 *
 * @author KScript
 *
 * @param canReply *Optional*. *True*, if the bot can send and edit messages in the private chats
 * that had incoming messages in the last 24 hours
 * @param canReadMessages *Optional*. *True*, if the bot can mark incoming private messages as read
 * @param canDeleteSentMessages *Optional*. *True*, if the bot can delete messages sent by the bot
 * @param canDeleteAllMessages *Optional*. *True*, if the bot can delete all private messages in
 * managed chats
 * @param canEditName *Optional*. *True*, if the bot can edit the first and last name of the
 * business account
 * @param canEditBio *Optional*. *True*, if the bot can edit the bio of the business account
 * @param canEditProfilePhoto *Optional*. *True*, if the bot can edit the profile photo of the
 * business account
 * @param canEditUsername *Optional*. *True*, if the bot can edit the username of the business
 * account
 * @param canChangeGiftSettings *Optional*. *True*, if the bot can change the privacy settings
 * pertaining to gifts for the business account
 * @param canViewGiftsAndStars *Optional*. *True*, if the bot can view gifts and the amount of
 * Telegram Stars owned by the business account
 * @param canConvertGiftsToStars *Optional*. *True*, if the bot can convert regular gifts owned by
 * the business account to Telegram Stars
 * @param canTransferAndUpgradeGifts *Optional*. *True*, if the bot can transfer and upgrade gifts
 * owned by the business account
 * @param canTransferStars *Optional*. *True*, if the bot can transfer Telegram Stars received by
 * the business account to its own account, or use them to upgrade and transfer gifts
 * @param canManageStories *Optional*. *True*, if the bot can post, edit and delete stories on
 * behalf of the business account
 */
public data class BusinessBotRights(
    /**
     * *Optional*. *True*, if the bot can send and edit messages in the private chats that had
     * incoming messages in the last 24 hours
     */
    @get:JsonProperty("can_reply")
    @param:JsonProperty("can_reply")
    public val canReply: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can mark incoming private messages as read
     */
    @get:JsonProperty("can_read_messages")
    @param:JsonProperty("can_read_messages")
    public val canReadMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can delete messages sent by the bot
     */
    @get:JsonProperty("can_delete_sent_messages")
    @param:JsonProperty("can_delete_sent_messages")
    public val canDeleteSentMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can delete all private messages in managed chats
     */
    @get:JsonProperty("can_delete_all_messages")
    @param:JsonProperty("can_delete_all_messages")
    public val canDeleteAllMessages: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can edit the first and last name of the business account
     */
    @get:JsonProperty("can_edit_name")
    @param:JsonProperty("can_edit_name")
    public val canEditName: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can edit the bio of the business account
     */
    @get:JsonProperty("can_edit_bio")
    @param:JsonProperty("can_edit_bio")
    public val canEditBio: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can edit the profile photo of the business account
     */
    @get:JsonProperty("can_edit_profile_photo")
    @param:JsonProperty("can_edit_profile_photo")
    public val canEditProfilePhoto: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can edit the username of the business account
     */
    @get:JsonProperty("can_edit_username")
    @param:JsonProperty("can_edit_username")
    public val canEditUsername: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can change the privacy settings pertaining to gifts for the
     * business account
     */
    @get:JsonProperty("can_change_gift_settings")
    @param:JsonProperty("can_change_gift_settings")
    public val canChangeGiftSettings: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can view gifts and the amount of Telegram Stars owned by the
     * business account
     */
    @get:JsonProperty("can_view_gifts_and_stars")
    @param:JsonProperty("can_view_gifts_and_stars")
    public val canViewGiftsAndStars: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can convert regular gifts owned by the business account to
     * Telegram Stars
     */
    @get:JsonProperty("can_convert_gifts_to_stars")
    @param:JsonProperty("can_convert_gifts_to_stars")
    public val canConvertGiftsToStars: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can transfer and upgrade gifts owned by the business account
     */
    @get:JsonProperty("can_transfer_and_upgrade_gifts")
    @param:JsonProperty("can_transfer_and_upgrade_gifts")
    public val canTransferAndUpgradeGifts: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can transfer Telegram Stars received by the business account
     * to its own account, or use them to upgrade and transfer gifts
     */
    @get:JsonProperty("can_transfer_stars")
    @param:JsonProperty("can_transfer_stars")
    public val canTransferStars: Boolean? = null,
    /**
     * *Optional*. *True*, if the bot can post, edit and delete stories on behalf of the business
     * account
     */
    @get:JsonProperty("can_manage_stories")
    @param:JsonProperty("can_manage_stories")
    public val canManageStories: Boolean? = null,
)
