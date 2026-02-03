import { Box, Typography } from '@mui/material';
import { AppLayout } from "../../a-app/layout/appLayout.tsx";

export function HomePage() {
    return (
        <AppLayout>
            <Box sx={{ mt: 4 }}>
                <Typography variant="h4" gutterBottom>
                    👋 Welcome to the Telegram Bot Admin Panel
                </Typography>

                <Typography variant="body1" sx={{ mb: 2 }}>
                    This admin panel allows you to manage bot-related data such as users, chats, and messages,
                    as well as monitor how the bot interacts with Telegram.
                </Typography>

                <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                    📊 This page is currently a placeholder for the dashboard.
                </Typography>

                <Typography variant="body2" color="text.secondary">
                    In the future, it can be extended with statistics, activity charts, system status,
                    and other useful operational insights.
                </Typography>
            </Box>
        </AppLayout>
    );
}
