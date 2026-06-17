package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object represents a block in a rich formatted message. Currently, it can be any of the
 * following types:
 *
 * * [RichBlockParagraph](https://core.telegram.org/bots/api/#richblockparagraph)
 * * [RichBlockSectionHeading](https://core.telegram.org/bots/api/#richblocksectionheading)
 * * [RichBlockPreformatted](https://core.telegram.org/bots/api/#richblockpreformatted)
 * * [RichBlockFooter](https://core.telegram.org/bots/api/#richblockfooter)
 * * [RichBlockDivider](https://core.telegram.org/bots/api/#richblockdivider)
 * *
 * [RichBlockMathematicalExpression](https://core.telegram.org/bots/api/#richblockmathematicalexpression)
 * * [RichBlockAnchor](https://core.telegram.org/bots/api/#richblockanchor)
 * * [RichBlockList](https://core.telegram.org/bots/api/#richblocklist)
 * * [RichBlockBlockQuotation](https://core.telegram.org/bots/api/#richblockblockquotation)
 * * [RichBlockPullQuotation](https://core.telegram.org/bots/api/#richblockpullquotation)
 * * [RichBlockCollage](https://core.telegram.org/bots/api/#richblockcollage)
 * * [RichBlockSlideshow](https://core.telegram.org/bots/api/#richblockslideshow)
 * * [RichBlockTable](https://core.telegram.org/bots/api/#richblocktable)
 * * [RichBlockDetails](https://core.telegram.org/bots/api/#richblockdetails)
 * * [RichBlockMap](https://core.telegram.org/bots/api/#richblockmap)
 * * [RichBlockAnimation](https://core.telegram.org/bots/api/#richblockanimation)
 * * [RichBlockAudio](https://core.telegram.org/bots/api/#richblockaudio)
 * * [RichBlockPhoto](https://core.telegram.org/bots/api/#richblockphoto)
 * * [RichBlockVideo](https://core.telegram.org/bots/api/#richblockvideo)
 * * [RichBlockVoiceNote](https://core.telegram.org/bots/api/#richblockvoicenote)
 * * [RichBlockThinking](https://core.telegram.org/bots/api/#richblockthinking)
 *
 * @see [RichBlock] (https://core.telegram.org/bots/api/#richblock)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = RichBlockParagraph::class, name = "paragraph"),
    JsonSubTypes.Type(value = RichBlockSectionHeading::class, name = "heading"),
    JsonSubTypes.Type(value = RichBlockPreformatted::class, name = "pre"),
    JsonSubTypes.Type(value = RichBlockFooter::class, name = "footer"),
    JsonSubTypes.Type(value = RichBlockDivider::class, name = "divider"),
    JsonSubTypes.Type(value = RichBlockMathematicalExpression::class, name =
            "mathematical_expression"),
    JsonSubTypes.Type(value = RichBlockAnchor::class, name = "anchor"),
    JsonSubTypes.Type(value = RichBlockList::class, name = "list"),
    JsonSubTypes.Type(value = RichBlockBlockQuotation::class, name = "blockquote"),
    JsonSubTypes.Type(value = RichBlockPullQuotation::class, name = "pullquote"),
    JsonSubTypes.Type(value = RichBlockCollage::class, name = "collage"),
    JsonSubTypes.Type(value = RichBlockSlideshow::class, name = "slideshow"),
    JsonSubTypes.Type(value = RichBlockTable::class, name = "table"),
    JsonSubTypes.Type(value = RichBlockDetails::class, name = "details"),
    JsonSubTypes.Type(value = RichBlockMap::class, name = "map"),
    JsonSubTypes.Type(value = RichBlockAnimation::class, name = "animation"),
    JsonSubTypes.Type(value = RichBlockAudio::class, name = "audio"),
    JsonSubTypes.Type(value = RichBlockPhoto::class, name = "photo"),
    JsonSubTypes.Type(value = RichBlockVideo::class, name = "video"),
    JsonSubTypes.Type(value = RichBlockVoiceNote::class, name = "voice_note"),
    JsonSubTypes.Type(value = RichBlockThinking::class, name = "thinking"),
)
public sealed interface RichBlock {
    public val type: String
}
