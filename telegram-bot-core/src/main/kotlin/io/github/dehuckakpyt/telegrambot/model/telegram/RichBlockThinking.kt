package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonProperty
import kotlin.String

/**
 * A block with a “Thinking…” placeholder, corresponding to the custom HTML tag `<tg-thinking>`. The
 * block may be used only in
 * [sendRichMessageDraft](https://core.telegram.org/bots/api/#sendrichmessagedraft), therefore it can't
 * be received in messages. See [https://t.me/addemoji/AIActions](https://t.me/addemoji/AIActions) for
 * examples of custom emoji, which are recommended for usage in the block.
 *
 * @see [RichBlockThinking] (https://core.telegram.org/bots/api/#richblockthinking)
 *
 * @author KScript
 *
 * @param type Type of the block, always “thinking”
 * @param text Text of the block. See
 * [https://t.me/addemoji/AIActions](https://t.me/addemoji/AIActions) for examples of custom emoji,
 * which are recommended for usage in the block.
 */
public data class RichBlockThinking(
    /**
     * Type of the block, always “thinking”
     */
    @get:JsonProperty("type")
    @param:JsonProperty("type")
    override val type: String,
    /**
     * Text of the block. See [https://t.me/addemoji/AIActions](https://t.me/addemoji/AIActions) for
     * examples of custom emoji, which are recommended for usage in the block.
     */
    @get:JsonProperty("text")
    @param:JsonProperty("text")
    public val text: RichText,
) : RichBlock
