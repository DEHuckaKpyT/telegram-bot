# Migration guide

### From 0.13.5 to 0.14.0

Refactor column fields. Sql-example for PostgreSQL:

```postgresql
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
