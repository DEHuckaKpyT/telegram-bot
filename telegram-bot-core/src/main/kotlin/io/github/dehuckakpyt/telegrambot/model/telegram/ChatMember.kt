package io.github.dehuckakpyt.telegrambot.model.telegram

import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import kotlin.String

/**
 * Created on 02.06.2024.
 *
 * This object contains information about one member of a chat. Currently, the following 6 types of
 * chat members are supported:
 *
 * * [ChatMemberOwner](https://core.telegram.org/bots/api/#chatmemberowner)
 * * [ChatMemberAdministrator](https://core.telegram.org/bots/api/#chatmemberadministrator)
 * * [ChatMemberMember](https://core.telegram.org/bots/api/#chatmembermember)
 * * [ChatMemberRestricted](https://core.telegram.org/bots/api/#chatmemberrestricted)
 * * [ChatMemberLeft](https://core.telegram.org/bots/api/#chatmemberleft)
 * * [ChatMemberBanned](https://core.telegram.org/bots/api/#chatmemberbanned)
 *
 * @see [ChatMember] (https://core.telegram.org/bots/api/#chatmember)
 *
 * @author KScript
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "status",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ChatMemberOwner::class, name = "creator"),
    JsonSubTypes.Type(value = ChatMemberAdministrator::class, name = "administrator"),
    JsonSubTypes.Type(value = ChatMemberMember::class, name = "member"),
    JsonSubTypes.Type(value = ChatMemberRestricted::class, name = "restricted"),
    JsonSubTypes.Type(value = ChatMemberLeft::class, name = "left"),
    JsonSubTypes.Type(value = ChatMemberBanned::class, name = "kicked"),
)
public sealed interface ChatMember {
    public val status: String

    public val user: User
}
