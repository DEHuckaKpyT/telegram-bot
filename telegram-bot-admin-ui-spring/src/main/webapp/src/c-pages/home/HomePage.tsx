import { Box, Typography } from '@mui/material';
import { AppLayout } from "../../a-app/layout/AppLayout.tsx";

export function HomePage() {
    return (
        <AppLayout>
            <Box sx={{ mt: 4 }}>
                <Typography variant="h4" gutterBottom>
                    Добро пожаловать в админку Telegram бота
                </Typography>
                <Typography variant="body1" paragraph>
                    Здесь вы сможете управлять пользователями, чатами и сообщениями.
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Пока что это просто заглушка главной страницы. В будущем сюда можно добавить статистику, графики и
                    другую информацию.
                </Typography>
            </Box>
        </AppLayout>
    );
}
