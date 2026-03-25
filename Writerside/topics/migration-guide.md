# Migration guide

### From 1.1.1 to 1.2.0

Receiver mode configuration was moved to unified `receiving` properties.

- Deprecated behavior: `telegram-bot.spring.update-receiver` no longer controls webhook/long-polling mode.
- New key: `telegram-bot.receiving.mode` with values `long-polling` or `webhook`.
- Long polling settings: `telegram-bot.receiving.long-polling.*`.
- Webhook settings: `telegram-bot.receiving.webhook.*`.

Example:

```yaml
telegram-bot:
  receiving:
    mode: webhook
    webhook:
      url-host: https://my.domain.com
      url-path: /updates/receive
```

### From 0.13.5 to 1.0.0

Refactor column fields. Sql-example for PostgreSQL:

```sql
ALTER TABLE callback_content RENAME COLUMN update_date TO updated_at;

ALTER TABLE telegram_chat RENAME COLUMN update_date TO updated_at;
ALTER TABLE telegram_chat RENAME COLUMN create_date TO created_at;

ALTER TABLE telegram_chat_status_event ALTER COLUMN title DROP NOT NULL;
ALTER TABLE telegram_chat_status_event RENAME COLUMN create_date TO created_at;

ALTER TABLE telegram_message ALTER COLUMN from_id DROP NOT NULL;
ALTER TABLE telegram_message RENAME COLUMN create_date TO created_at;

ALTER TABLE telegram_user RENAME COLUMN update_date TO updated_at;
ALTER TABLE telegram_user RENAME COLUMN create_date TO created_at;
```
