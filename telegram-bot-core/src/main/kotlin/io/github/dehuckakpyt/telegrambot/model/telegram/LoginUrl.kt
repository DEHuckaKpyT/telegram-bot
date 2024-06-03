package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.Boolean
import kotlin.String

/**
 * Created on 03.06.2024.
 *
 * This object represents a parameter of the inline keyboard button used to automatically authorize
 * a user. Serves as a great replacement for the [Telegram Login
 * Widget](https://core.telegram.org/widgets/login) when the user is coming from Telegram. All the user
 * needs to do is tap/click a button and confirm that they want to log in:
 *
 * Telegram apps support these buttons as of [version
 * 5.7](https://telegram.org/blog/privacy-discussions-web-bots#meet-seamless-web-bots).
 *
 * Sample bot: [@discussbot](https://t.me/discussbot)
 *
 * @see [LoginUrl] (https://core.telegram.org/bots/api/#loginurl)
 *
 * @author KScript
 *
 * @param url An HTTPS URL to be opened with user authorization data added to the query string when
 * the button is pressed. If the user refuses to provide authorization data, the original URL without
 * information about the user will be opened. The data added is the same as described in [Receiving
 * authorization data](https://core.telegram.org/widgets/login#receiving-authorization-data).  
 *
 * **NOTE:** You **must** always check the hash of the received data to verify the authentication
 * and the integrity of the data as described in [Checking
 * authorization](https://core.telegram.org/widgets/login#checking-authorization).
 * @param forwardText *Optional*. New text of the button in forwarded messages.
 * @param botUsername *Optional*. Username of a bot, which will be used for user authorization. See
 * [Setting up a bot](https://core.telegram.org/widgets/login#setting-up-a-bot) for more details. If
 * not specified, the current bot's username will be assumed. The *url*'s domain must be the same as
 * the domain linked with the bot. See [Linking your domain to the
 * bot](https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot) for more details.
 * @param requestWriteAccess *Optional*. Pass *True* to request the permission for your bot to send
 * messages to the user.
 */
public data class LoginUrl(
    /**
     * An HTTPS URL to be opened with user authorization data added to the query string when the
     * button is pressed. If the user refuses to provide authorization data, the original URL without
     * information about the user will be opened. The data added is the same as described in [Receiving
     * authorization data](https://core.telegram.org/widgets/login#receiving-authorization-data).  
     *
     * **NOTE:** You **must** always check the hash of the received data to verify the
     * authentication and the integrity of the data as described in [Checking
     * authorization](https://core.telegram.org/widgets/login#checking-authorization).
     */
    @get:JsonProperty("url")
    @param:JsonProperty("url")
    public val url: String,
    /**
     * *Optional*. New text of the button in forwarded messages.
     */
    @get:JsonProperty("forward_text")
    @param:JsonProperty("forward_text")
    public val forwardText: String? = null,
    /**
     * *Optional*. Username of a bot, which will be used for user authorization. See [Setting up a
     * bot](https://core.telegram.org/widgets/login#setting-up-a-bot) for more details. If not
     * specified, the current bot's username will be assumed. The *url*'s domain must be the same as
     * the domain linked with the bot. See [Linking your domain to the
     * bot](https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot) for more details.
     */
    @get:JsonProperty("bot_username")
    @param:JsonProperty("bot_username")
    public val botUsername: String? = null,
    /**
     * *Optional*. Pass *True* to request the permission for your bot to send messages to the user.
     */
    @get:JsonProperty("request_write_access")
    @param:JsonProperty("request_write_access")
    public val requestWriteAccess: Boolean? = null,
)
