# Callback serialization

By default, the string `<next step>|s<json content string>` is written to the callback.
But if the string is more than 64 characters, the string will contain `<next step>|i<id CallbackContent>`.

`callbackDataDelimiter: Char` - character that separates step and callback. By default - `|`.

`contentConverter: ContentConverter` - A converter that serializes the object to a string (defaults to json).

`callbackSerializer: CallbackSerializer` - serializer that stacks step and object into a string.

To serialize to a more compact string, you can write your own implementation for serialization.
Just implement the `CallbackSerializer` interface and set it in the config:

```kotlin
val config = TelegramBotConfig().apply {
    receiving {
        callbackSerializer = CustomCallbackSerializer()
    }
}
```