# Startup bot edits

`TelegramBotManager.start()` can apply bot edits before update receiving starts.

At the moment this includes startup command menu registration via Telegram `setMyCommands`.

## Configuration

You can configure startup edits in `telegram-bot.edit.*`.

```yaml
telegram-bot:
  edit:
    commands:
      source: code # null | code | config
      blank-description: fail # skip | fail
```

`source` values:

- `null` - disable startup command menu registration
- `code` - collect commands from `BotHandling.command(..., description = "...")`
- `config` - read commands from `telegram-bot.edit.commands.definition`

`blank-description` values:

- `skip` - command without description is ignored
- `fail` - throw an exception if any command has blank description

## `source: code`

Add descriptions in handlers:

```kotlin
fun BotHandling.baseCommands() {
    command("/start", description = "1. Start") {
        sendMessage("Hello!")
    }
    command("/help", description = "2. Help") {
        sendMessage("Help message")
    }
}
```

Notes:

- command names are validated (`/` + lowercase letters/digits/underscores, max length 32)
- description length must be from 1 to 256
- optional numeric prefix like `1. ` is used for sorting and removed from final text

## `source: config`

Define command menu statically:

```yaml
telegram-bot:
  edit:
    commands:
      source: config
      definition:
        start: Start command description
        help: Help command description
```

Keys are command names **without** leading slash, values are descriptions.

## Startup flow

When you call `TelegramBotManager.start()`:

1. Startup edits are applied (if configured).
2. Update receiver is started.

If configuration is invalid (for example, `source: config` without `definition`), startup fails with an explicit exception.
