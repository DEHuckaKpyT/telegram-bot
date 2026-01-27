import { Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { adminSessionStore } from "../../g-shared/store/adminSessionStore.ts";
import { useApi } from "../../a-app/provider/apiProvider.tsx";

export function LogoutButton() {
    const { adminAuth } = useApi();
    const navigate = useNavigate();

    const handleLogout = () => {
        adminAuth.logout();
        adminSessionStore.clear();
        navigate('/login', { replace: true });
    };

    return (
        <Button variant="contained" color="secondary" onClick={handleLogout}>
            Logout
        </Button>
    );
}
