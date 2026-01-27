import { type ReactNode, useState } from 'react';
import { Avatar, Box, Collapse, Drawer, List, ListItemButton, ListItemText, Popover, Typography, } from '@mui/material';
import { ExpandLess, ExpandMore } from '@mui/icons-material';
import { LogoutButton } from "../../d-widgets/admin-auth/telegram-logout-button.tsx";
import { useLocation, useNavigate } from "react-router-dom";

type Props = {
    children: ReactNode;
};

export function AppLayout({ children }: Props) {
    const navigate = useNavigate();
    const location = useLocation();
    const drawerWidth = 240;
    const [ openUserPopover, setOpenUserPopover ] = useState<HTMLElement | null>(null);
    const [ openEntities, setOpenEntities ] = useState(true);

    const handleUserClick = (event: React.MouseEvent<HTMLElement>) => {
        setOpenUserPopover(event.currentTarget);
    };

    const handleUserClose = () => {
        setOpenUserPopover(null);
    };

    const toggleEntities = () => setOpenEntities(prev => !prev);

    return (
        <Box sx={{ display: 'flex', minHeight: '100vh' }}>
            {/* Sidebar */}
            <Drawer
                variant="permanent"
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'space-between'
                    },
                }}
            >
                <Box>
                    <List>
                        <ListItemButton onClick={toggleEntities}>
                            <ListItemText primary="Сущности"/>
                            {openEntities ? <ExpandLess/> : <ExpandMore/>}
                        </ListItemButton>
                        <Collapse in={openEntities} timeout="auto" unmountOnExit>
                            <List component="div" disablePadding>
                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    selected={location.pathname === '/telegram-users'}
                                    onClick={() => navigate('/telegram-users')}
                                >
                                    <ListItemText primary="Пользователи" />
                                </ListItemButton>
                                <ListItemButton sx={{ pl: 4 }}>
                                    <ListItemText primary="Сообщения"/>
                                </ListItemButton>
                                <ListItemButton sx={{ pl: 4 }}>
                                    <ListItemText primary="Чаты"/>
                                </ListItemButton>
                            </List>
                        </Collapse>
                    </List>
                </Box>

                {/* User info bottom */}
                <Box sx={{ p: 2 }}>
                    <Box sx={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }} onClick={handleUserClick}>
                        <Avatar sx={{ mr: 1 }}>A</Avatar>
                        <Typography variant="body2">Admin</Typography>
                    </Box>

                    <Popover
                        open={Boolean(openUserPopover)}
                        anchorEl={openUserPopover}
                        onClose={handleUserClose}
                        anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
                        transformOrigin={{ vertical: 'bottom', horizontal: 'right' }}
                    >
                        <Box sx={{ p: 2, display: 'flex', flexDirection: 'column', gap: 1 }}>
                            <Typography variant="body2">Admin User</Typography>
                            <LogoutButton/>
                        </Box>
                    </Popover>
                </Box>
            </Drawer>

            {/* Main content */}
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                {children}
            </Box>
        </Box>
    );
}
