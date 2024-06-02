package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object represents a service message about a user allowing a bot to write messages after
 * adding it to the attachment menu, launching a Web App from a link, or accepting an explicit request
 * from a Web App sent by the method
 * [requestWriteAccess](https://core.telegram.org/bots/webapps#initializing-mini-apps).
 *
 * @see [WriteAccessAllowed] (https://core.telegram.org/bots/api/#writeaccessallowed)
 *
 * @author KScript
 *
 * @param fromRequest *Optional*. True, if the access was granted after the user accepted an
 * explicit request from a Web App sent by the method
 * [requestWriteAccess](https://core.telegram.org/bots/webapps#initializing-mini-apps)
 * @param webAppName *Optional*. Name of the Web App, if the access was granted when the Web App was
 * launched from a link
 * @param fromAttachmentMenu *Optional*. True, if the access was granted when the bot was added to
 * the attachment or side menu
 */
public data class WriteAccessAllowed(
    /**
     * *Optional*. True, if the access was granted after the user accepted an explicit request from
     * a Web App sent by the method
     * [requestWriteAccess](https://core.telegram.org/bots/webapps#initializing-mini-apps)
     */
    @get:JsonProperty("from_request")
    @param:JsonProperty("from_request")
    public val fromRequest: Boolean? = null,
    /**
     * *Optional*. Name of the Web App, if the access was granted when the Web App was launched from
     * a link
     */
    @get:JsonProperty("web_app_name")
    @param:JsonProperty("web_app_name")
    public val webAppName: String? = null,
    /**
     * *Optional*. True, if the access was granted when the bot was added to the attachment or side
     * menu
     */
    @get:JsonProperty("from_attachment_menu")
    @param:JsonProperty("from_attachment_menu")
    public val fromAttachmentMenu: Boolean? = null,
)
