import { Box, Card, CardContent, Divider, Link, Typography, } from "@mui/material";
import { TelegramLoginButton } from "../../../d-widgets/admin-auth/telegramLoginButton.tsx";
import { useAppConfig } from "../../../a-app/config/appConfigContext.tsx";

export function LoginPage() {
    const { telegramBotUsername } = useAppConfig();

    return (
        <Box
            sx={{
                minHeight: "100vh",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                background: theme =>
                    `linear-gradient(135deg,
                        ${theme.palette.background.default} 0%,
                        ${theme.palette.background.paper} 100%)`,
            }}
        >
            <Card elevation={8} sx={{ width: 400, borderRadius: 3 }}>
                <CardContent sx={{ p: 4 }}>
                    <Box sx={{ textAlign: "center", mb: 3 }}>
                        <Typography variant="h4" fontWeight={600}>
                            Admin Panel
                        </Typography>

                        <Typography
                            variant="body2"
                            color="text.secondary"
                            sx={{ mt: 1 }}
                        >
                            Administration interface for Telegram bot{" "}
                            <Link
                                href={`https://t.me/${telegramBotUsername}`}
                                target="_blank"
                                rel="noopener noreferrer"
                                underline="hover"
                            >
                                @{telegramBotUsername}
                            </Link>
                        </Typography>
                    </Box>

                    <Divider sx={{ mb: 3 }}/>

                    <Box sx={{ display: "flex", justifyContent: "center" }}>
                        <TelegramLoginButton/>
                    </Box>

                    <Typography
                        variant="caption"
                        color="text.secondary"
                        sx={{
                            mt: 3,
                            textAlign: "center",
                            display: "block",
                        }}
                    >
                        Authentication is performed via your Telegram account
                    </Typography>
                </CardContent>
            </Card>
        </Box>
    );
}
