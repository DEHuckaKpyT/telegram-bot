import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { adminSessionStore } from "../../g-shared/store/admin-session-store.ts";
import { logout } from "../../g-shared/api/auth/admin-auth-api.ts";

export function LogoutButton() {
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        adminSessionStore.clear();
        navigate('/login', { replace: true });
    };

    return (
        <Button variant="contained" color="secondary" onClick={handleLogout}>
            Logout
        </Button>
    );
}
