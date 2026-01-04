# Use webhook without domain

This topic explains how to deploy your Telegram bot (use webhook) on a VPS using a self-signed certificate and IP address instead of a domain, with the help of `nginx`, `openssl`, `docker compose`, and of course, `dehuckakpyt.telegram-bot`.

## Install requirements

- `sudo apt install nginx` – installs the NGINX web server.
- `sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin` – installs Docker Engine and Docker Compose plugins.

## Set configs

We'll use `/work-directory` as the working directory.

1. `cd /work-directory` – navigate to the working directory.
2. copy your bot project to `/work-directory/webhook-example-telegram-bot`
3. `openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -sha256 -days 36500 -nodes` – generates a self-signed SSL certificate and private key.
    **Important**: Be sure to specify your IP address in the `Common Name` field when prompted.
4. copy `cert.pem` to your project under `/resources/cert.pem`

5. `nano /work-directory/docker-compose.yaml` – create the Docker Compose configuration file:

```yaml
version: "3.4"
services:

  webhook-example-telegram-bot:
    image: webhook-example-telegram-bot
    build:
      context: './webhook-example-telegram-bot'
      dockerfile: Dockerfile
    command: >
      --telegram-bot.token=<YOUR_TOKEN> 
      --telegram-bot.username=<YOUR_USERNAME> 
      --telegram-bot.spring.update-receiver=webhook
      --telegram-bot.spring.update-receiver.webhook.url.host=https://<YOUR_IP>:8443/api/webhook-example-telegram-bot
      --telegram-bot.spring.update-receiver.webhook.certificate.path=/cert.pem
      --telegram-bot.spring.update-receiver.webhook.secret-token.random-generation.print-on-startup=true
    restart: no
    ports:
      - "9001:8080"
```

`telegram-bot.spring.update-receiver.webhook.url.host` – the URL where Telegram will send webhook updates.

`telegram-bot.spring.update-receiver.webhook.certificate.path` – the path to your certificate inside the application resources.

`telegram-bot.spring.update-receiver.webhook.secret-token.random-generation.print-on-startup` – prints the auto-generated value for the `X-Telegram-Bot-Api-Secret-Token` header to the console at startup.

6. `nano /work-directory/webhook-example-telegram-bot/Dockerfile` – create the Dockerfile for building and running your bot:

```docker
## Build service
FROM gradle:8.7-jdk17 AS build

WORKDIR /build
COPY . /build/
RUN ["chmod", "+x", "gradlew"]
RUN gradle bootJar

## Run service
FROM openjdk:17-oracle

COPY --from=build /build/build/libs/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
```

7. `nano /etc/nginx/conf.d/default.conf` – configure NGINX to serve your bot over HTTPS:

```nginx
server {
    listen       8443 ssl;
    listen  [::]:8443 ssl;
    server_name  <YOUR_IP>;

    ssl_certificate /work-directory/cert.pem;
    ssl_certificate_key /work-directory/key.pem;

    location /api/webhook-example-telegram-bot/ {                                                         
        proxy_pass         http://127.0.0.1:9001/;
        proxy_redirect     off;
        proxy_set_header   Host             $host;
        proxy_set_header   X-Real-IP        $remote_addr;
        proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
    }
}
```

## Start services

- `systemctl start nginx` – starts nginx service. `systemctl enable nginx` – starts automatically on system reboot. `systemctl restart nginx` – reload service after changing config. `systemctl status nginx` – check that service starts.
- `docker compose build --no-cache && docker compose up -d` – build and start application.

## Testing

You can check if the bot is working by simply sending it a message in Telegram.
Alternatively, you can manually send a test request using the following curl command:

```curl
curl -L 'https://<YOUR_IP>:8443/api/webhook-example-telegram-bot/updates/receive' -H 'X-Telegram-Bot-Api-Secret-Token: <GENERATED_HEADER_VALUE_FROM_CONSOLE>' -H 'Content-Type: application/json' -d '{}'
```

You should receive a 200 OK response if everything is configured correctly.

<seealso>
    <category ref="external-resources">
        <a href="https://core.telegram.org/bots/webhooks">Telegram docs about webhook</a>
        <a href="https://docs.docker.com/engine/install">Docker installation</a>
        <a href="https://nginx.org/en/docs/install.html">NGINX installation</a>
    </category>
</seealso>
