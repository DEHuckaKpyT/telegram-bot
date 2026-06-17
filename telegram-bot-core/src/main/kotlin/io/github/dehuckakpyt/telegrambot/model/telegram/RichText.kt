package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * This object represents a rich formatted text. Currently, it can be either a String for plain
 * text, an Array of [RichText](https://core.telegram.org/bots/api/#richtext), or any of the following
 * types:
 *
 * * [RichTextBold](https://core.telegram.org/bots/api/#richtextbold)
 * * [RichTextItalic](https://core.telegram.org/bots/api/#richtextitalic)
 * * [RichTextUnderline](https://core.telegram.org/bots/api/#richtextunderline)
 * * [RichTextStrikethrough](https://core.telegram.org/bots/api/#richtextstrikethrough)
 * * [RichTextSpoiler](https://core.telegram.org/bots/api/#richtextspoiler)
 * * [RichTextDateTime](https://core.telegram.org/bots/api/#richtextdatetime)
 * * [RichTextTextMention](https://core.telegram.org/bots/api/#richtexttextmention)
 * * [RichTextSubscript](https://core.telegram.org/bots/api/#richtextsubscript)
 * * [RichTextSuperscript](https://core.telegram.org/bots/api/#richtextsuperscript)
 * * [RichTextMarked](https://core.telegram.org/bots/api/#richtextmarked)
 * * [RichTextCode](https://core.telegram.org/bots/api/#richtextcode)
 * * [RichTextCustomEmoji](https://core.telegram.org/bots/api/#richtextcustomemoji)
 * *
 * [RichTextMathematicalExpression](https://core.telegram.org/bots/api/#richtextmathematicalexpression)
 * * [RichTextUrl](https://core.telegram.org/bots/api/#richtexturl)
 * * [RichTextEmailAddress](https://core.telegram.org/bots/api/#richtextemailaddress)
 * * [RichTextPhoneNumber](https://core.telegram.org/bots/api/#richtextphonenumber)
 * * [RichTextBankCardNumber](https://core.telegram.org/bots/api/#richtextbankcardnumber)
 * * [RichTextMention](https://core.telegram.org/bots/api/#richtextmention)
 * * [RichTextHashtag](https://core.telegram.org/bots/api/#richtexthashtag)
 * * [RichTextCashtag](https://core.telegram.org/bots/api/#richtextcashtag)
 * * [RichTextBotCommand](https://core.telegram.org/bots/api/#richtextbotcommand)
 * * [RichTextAnchor](https://core.telegram.org/bots/api/#richtextanchor)
 * * [RichTextAnchorLink](https://core.telegram.org/bots/api/#richtextanchorlink)
 * * [RichTextReference](https://core.telegram.org/bots/api/#richtextreference)
 * * [RichTextReferenceLink](https://core.telegram.org/bots/api/#richtextreferencelink)
 *
 * @see [RichText] (https://core.telegram.org/bots/api/#richtext)
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
    JsonSubTypes.Type(value = RichTextBold::class, name = "bold"),
    JsonSubTypes.Type(value = RichTextItalic::class, name = "italic"),
    JsonSubTypes.Type(value = RichTextUnderline::class, name = "underline"),
    JsonSubTypes.Type(value = RichTextStrikethrough::class, name = "strikethrough"),
    JsonSubTypes.Type(value = RichTextSpoiler::class, name = "spoiler"),
    JsonSubTypes.Type(value = RichTextDateTime::class, name = "date_time"),
    JsonSubTypes.Type(value = RichTextTextMention::class, name = "text_mention"),
    JsonSubTypes.Type(value = RichTextSubscript::class, name = "subscript"),
    JsonSubTypes.Type(value = RichTextSuperscript::class, name = "superscript"),
    JsonSubTypes.Type(value = RichTextMarked::class, name = "marked"),
    JsonSubTypes.Type(value = RichTextCode::class, name = "code"),
    JsonSubTypes.Type(value = RichTextCustomEmoji::class, name = "custom_emoji"),
    JsonSubTypes.Type(value = RichTextMathematicalExpression::class, name =
            "mathematical_expression"),
    JsonSubTypes.Type(value = RichTextUrl::class, name = "url"),
    JsonSubTypes.Type(value = RichTextEmailAddress::class, name = "email_address"),
    JsonSubTypes.Type(value = RichTextPhoneNumber::class, name = "phone_number"),
    JsonSubTypes.Type(value = RichTextBankCardNumber::class, name = "bank_card_number"),
    JsonSubTypes.Type(value = RichTextMention::class, name = "mention"),
    JsonSubTypes.Type(value = RichTextHashtag::class, name = "hashtag"),
    JsonSubTypes.Type(value = RichTextCashtag::class, name = "cashtag"),
    JsonSubTypes.Type(value = RichTextBotCommand::class, name = "bot_command"),
    JsonSubTypes.Type(value = RichTextAnchor::class, name = "anchor"),
    JsonSubTypes.Type(value = RichTextAnchorLink::class, name = "anchor_link"),
    JsonSubTypes.Type(value = RichTextReference::class, name = "reference"),
    JsonSubTypes.Type(value = RichTextReferenceLink::class, name = "reference_link"),
)
public sealed interface RichText {
    public val type: String
}
