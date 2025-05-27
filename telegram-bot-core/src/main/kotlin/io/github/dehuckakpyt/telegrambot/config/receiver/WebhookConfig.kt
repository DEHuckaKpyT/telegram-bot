package io.github.dehuckakpyt.telegrambot.config.receiver

import io.github.dehuckakpyt.telegrambot.model.telegram.input.ContentInput


/**
 * @author Denis Matytsin
 */
data class WebhookConfig(

    /**
     * Host for HTTPS URL to send updates to. Will be concatenated with `urlPath`.
     * URL directly to your application.
     * For Example, `https://my.domain.com/api/awesome-telegram-bot`.
     */
    var urlHost: String? = null,

    /**
     * Path for HTTPS URL to send updates to. Will be concatenated with `urlHost`.
     * API mapping in your application.
     * For Example, `/updates/receive`.
     * Defaults `/updates/receive`.
     */
    var urlPath: String? = null,

    /**
     * Upload your public key certificate so that the root certificate in use can be checked.
     * See our [self-signed guide](https://core.telegram.org/bots/self-signed) for details.
     */
    var certificate: ContentInput? = null,

    /** The fixed IP address which will be used to send webhook requests instead of the IP address resolved through DNS. */
    var ipAddress: String? = null,

    /**
     * The maximum allowed number of simultaneous HTTPS connections to the webhook for update delivery, 1-100.
     * Defaults to *40*. Use lower values to limit the load on your bot's server, and higher values to increase your bot's throughput.
     */
    var maxConnections: Int? = null,

    /** Pass *True* to drop all pending updates. */
    var dropPendingUpdates: Boolean? = null,

    /**
     * A secret token to be sent in a header “X-Telegram-Bot-Api-Secret-Token” in every webhook request, 1-256 characters.
     * Only characters `A-Z`, `a-z`, `0-9`, `_` and `-` are allowed.
     * The header is useful to ensure that the request comes from a webhook set by you.
     */
    var secretToken: String? = null,

    /**
     * Generation random secret token.
     * Defaults `RANDOM_UUID`.
     */
    var secretTokenRandomGeneration: SecretTokenRandomGeneration? = null,

    /**
     * Printing to log generated secret token.
     * Defaults `false`.
     */
    var secretTokenRandomGenerationPrintOnStartup: Boolean? = null,
) {
    public enum class SecretTokenRandomGeneration {
        NONE,
        RANDOM_UUID,
        RANDOM_256_CHARS,
    }
}
