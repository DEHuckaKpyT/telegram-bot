package io.github.dehuckakpyt.telegrambot.model.type

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName


/**
 * Created on 03.12.2023.
 *<p>
 *
 * @author Elbek Djuraev
 * @author Denis Matytsin
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = MenuButton.Commands::class, name = "commands"),
    JsonSubTypes.Type(value = MenuButton.WebApp::class, name = "web_app"),
    JsonSubTypes.Type(value = MenuButton.Default::class, name = "default"),
)
public sealed class MenuButton {
    public abstract val type: String

    @JsonTypeName("commands")
    public data class Commands(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String = "commands",
    ) : MenuButton()

    @JsonTypeName("web_app")
    public data class WebApp(
        @get:JsonProperty("text") @param:JsonProperty("text") val text: String,
        @get:JsonProperty("web_app") @param:JsonProperty("web_app") val webApp: WebAppInfo,
    ) : MenuButton() {
        @get:JsonProperty("type")
        override val type: String = "web_app"
    }

    @JsonTypeName("default")
    public data class Default(
        @get:JsonProperty("type") @param:JsonProperty("type") override val type: String = "default",
    ) : MenuButton()
}