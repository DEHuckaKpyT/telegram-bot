# Admin UI

<note>
This module is <b>experimental</b>. Backward compatibility is not guaranteed — any part may change.
</note>

<note>
This module provides its own Spring Security configuration, which may conflict with your application setup.
If you encounter such issues, please report them in
<a href="https://github.com/DEHuckaKpyT/telegram-bot/issues">GitHub Issues</a>.
</note>

To enable the Admin UI, add the following dependencies:
<code-block lang="kotlin">
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-admin-api-spring:%current_version%")
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-admin-ui-spring:%current_version%")
}
</code-block>

By default, the Admin Panel is available at
<code>http://127.0.0.1/admin-ui</code>.

By default, the list of users allowed to access the Admin Panel is taken from the
<code>telegram-bot.administration.access.user-ids</code> property.
You must explicitly specify Telegram user ids that are allowed to log in.

<note>
The Telegram login widget works only on the <code>127.0.0.1</code> host and port <code>80</code>.
You must also register this domain in <a href="https://t.me/BotFather">BotFather</a>.
</note>

## Configuration properties

```yaml
telegram-bot:
  administration:
    access:
      # Telegram user ids allowed to access the admin panel
      user-ids: 
        - 123
        - 456
    
    admin-api:
      # Backend API prefix, used to separate backend and frontend routes
      prefix: "/api"

      token-store:
        # Token TTL after the last backend request
        token-ttl: "PT12H"

    admin-panel:
      # Frontend UI prefix, used to separate backend and frontend routes
      prefix: "/admin-ui"
      front-end-config:
        # Default page size for all paginated views
        default-page-size: 25
```

If everything is configured correctly, you will see the login page:

<img src="admin-ui__login-page.jpg" alt="Admin UI login page"/>
