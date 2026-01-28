import { Box, Button, TextField } from '@mui/material';

type TelegramUsersFiltersProps = {
    chatIdsIn: string;
    fromIdsIn: string;

    onChatIdsInChange: (v: string) => void;
    onFromIdsInChange: (v: string) => void;
    onReset: () => void;
};

export function TelegramMessagesFilters({
                                            chatIdsIn,
                                            fromIdsIn,
                                            onChatIdsInChange,
                                            onFromIdsInChange,
                                            onReset,
                                        }: TelegramUsersFiltersProps) {
    return (
        <Box sx={{ display: 'flex', gap: 2, mb: 2 }}>
            <TextField
                label="Chat ID"
                size="small"
                value={chatIdsIn}
                onChange={e => {
                    const filtered = e.target.value.replace(/[^0-9,]/g, '');
                    onChatIdsInChange(filtered);
                }}
            />
            <TextField
                label="Sender ID"
                size="small"
                value={fromIdsIn}
                onChange={e => {
                    const filtered = e.target.value.replace(/[^0-9,]/g, '');
                    onFromIdsInChange(filtered);
                }}
            />

            <Button onClick={onReset}>Сбросить</Button>
        </Box>
    );
}