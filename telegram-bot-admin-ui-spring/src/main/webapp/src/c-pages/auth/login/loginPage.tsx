import { Box, Card, CardContent, Typography } from '@mui/material';
import { TelegramLoginButton } from "../../../d-widgets/admin-auth/telegramLoginButton.tsx";


export function LoginPage() {
    return (
        <Box
            sx={{
                minHeight: '100vh',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                bgcolor: 'grey.100',
            }}
        >
            <Card sx={{ width: 360 }}>
                <CardContent sx={{ textAlign: 'center', p: 4 }}>
                    <Typography variant="h5" gutterBottom>
                        Admin panel
                    </Typography>

                    <Typography variant="body2" color="text.secondary" sx={{ mb: 3 }}>
                        Enter vai Telegram
                    </Typography>

                    <TelegramLoginButton/>
                </CardContent>
            </Card>
        </Box>
    );
}
