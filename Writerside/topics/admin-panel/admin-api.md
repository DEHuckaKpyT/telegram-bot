# API

<note>
This module is <b>experimental</b>. Backward compatibility is not guaranteed — any part may change.
</note>

<note>
By default, all API endpoints are accessible only to users with the <code>ADMIN</code> role.
You can override this behavior by providing your own implementation of
<code>AdminApiAccessManager</code>.
The security configuration <u>must be provided by your application</u>.
</note>

Add the following dependency to enable the <code>admin-api</code> module:
<code-block lang="kotlin">
dependencies {
    implementation("io.github.dehuckakpyt.telegrambot:telegram-bot-admin-api-spring:%current_version%")
}
</code-block>

API contracts are located in the package
<code>io.github.dehuckakpyt.telegrambot.controller.admin</code>.
