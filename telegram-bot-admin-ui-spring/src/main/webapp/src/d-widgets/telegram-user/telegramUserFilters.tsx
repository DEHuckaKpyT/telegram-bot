import { Box, Button, TextField } from '@mui/material';

type TelegramUsersFiltersProps = {
    anyStringFieldContainsIgnoreCase: string;
    usernameContainsIgnoreCase: string;
    userIdsIn: string;

    onAnyStringFieldContainsIgnoreCaseChange: (v: string) => void;
    onUsernameContainsIgnoreCaseChange: (v: string) => void;
    onUserIdsInChange: (v: string) => void;
    onReset: () => void;
};

export function TelegramUsersFilters({
                                         anyStringFieldContainsIgnoreCase,
                                         usernameContainsIgnoreCase,
                                         userIdsIn,
                                         onAnyStringFieldContainsIgnoreCaseChange,
                                         onUsernameContainsIgnoreCaseChange,
                                         onUserIdsInChange,
                                         onReset,
                                     }: TelegramUsersFiltersProps) {
    return (
        <Box sx={{ display: 'flex', gap: 2, mb: 2 }}>
            <TextField
                label="Search (any text)"
                size="small"
                value={anyStringFieldContainsIgnoreCase}
                onChange={e => onAnyStringFieldContainsIgnoreCaseChange(e.target.value)}
            />

            <TextField
                label="Username"
                size="small"
                value={usernameContainsIgnoreCase}
                onChange={e => onUsernameContainsIgnoreCaseChange(e.target.value)}
            />

            <TextField
                label="User ID"
                size="small"
                value={userIdsIn}
                onChange={e => {
                    const filtered = e.target.value.replace(/[^0-9,]/g, '');
                    onUserIdsInChange(filtered);
                }}
            />

            <Button onClick={onReset}>Reset</Button>
        </Box>
    );
}