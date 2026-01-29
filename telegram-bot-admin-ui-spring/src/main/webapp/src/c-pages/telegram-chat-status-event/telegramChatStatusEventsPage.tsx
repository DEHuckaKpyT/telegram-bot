import { Box, Card, Link, Typography } from '@mui/material';
import GitHubIcon from '@mui/icons-material/GitHub';
import EventNoteIcon from "@mui/icons-material/EventNote";

export function TelegramChatStatusEventsPage() {
    return (
        <Box
            sx={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                minHeight: '60vh',
                p: 2,
            }}
        >
            <Card sx={{ maxWidth: 520, textAlign: 'center', p: 3 }}>
                <EventNoteIcon sx={{ fontSize: 48, mb: 2, color: 'text.secondary' }}/>
                <Typography variant="h6" gutterBottom>
                    Chat Status Events
                </Typography>

                <Typography variant="body2" color="text.secondary" sx={{ mb: 1 }}>
                    This section has not been implemented yet.
                </Typography>

                <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
                    The project is under active development, and contributions are welcome.
                </Typography>

                <Link
                    href="https://github.com/DEHuckaKpyT/telegram-bot"
                    target="_blank"
                    rel="noopener"
                    sx={{ display: 'inline-flex', alignItems: 'center', gap: 0.5 }}
                >
                    <GitHubIcon fontSize="small"/>
                    Contribute on GitHub
                </Link>
            </Card>
        </Box>
    );
}
