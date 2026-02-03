import { type ReactNode, useState } from 'react';
import { Avatar, Box, Collapse, Drawer, List, ListItemButton, ListItemText, Popover, Typography, } from '@mui/material';
import { ExpandLess, ExpandMore } from '@mui/icons-material';
import { LogoutButton } from "../../d-widgets/admin-auth/telegramLogoutButton.tsx";
import { useLocation, useNavigate } from "react-router-dom";
import PeopleIcon from "@mui/icons-material/People";
import MessageIcon from "@mui/icons-material/Message";
import ChatBubbleOutlineIcon from "@mui/icons-material/ChatBubbleOutline";
import CategoryIcon from "@mui/icons-material/Category";
import EventNoteIcon from '@mui/icons-material/EventNote';
import { $authContext } from "../../g-shared/store/authStore.ts";

type Props = {
    children: ReactNode;
};

export function AppLayout({ children }: Props) {
    const navigate = useNavigate();
    const location = useLocation();
    const userData = $authContext.get()!;

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
                            <ListItemText
                                primary={
                                    <span>
                    <CategoryIcon fontSize="small" sx={{ mr: 1 }}/>
                    Entities
                </span>
                                }
                            />
                            {openEntities ? <ExpandLess/> : <ExpandMore/>}
                        </ListItemButton>
                        <Collapse in={openEntities} timeout="auto" unmountOnExit>
                            <List component="div" disablePadding>
                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    selected={location.pathname === '/telegram-users'}
                                    onClick={() => navigate('/telegram-users')}
                                >
                                    <ListItemText
                                        primary={
                                            <span>
                            <PeopleIcon fontSize="small" sx={{ mr: 1 }}/>
                            Users
                        </span>
                                        }
                                    />
                                </ListItemButton>

                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    selected={location.pathname === '/telegram-messages'}
                                    onClick={() => navigate('/telegram-messages')}
                                >
                                    <ListItemText
                                        primary={
                                            <span>
                            <MessageIcon fontSize="small" sx={{ mr: 1 }}/>
                            Messages
                        </span>
                                        }
                                    />
                                </ListItemButton>

                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    selected={location.pathname === '/telegram-chat'}
                                    onClick={() => navigate('/telegram-chats')}
                                >
                                    <ListItemText
                                        primary={
                                            <span>
                            <ChatBubbleOutlineIcon fontSize="small" sx={{ mr: 1 }}/>
                            Chats
                        </span>
                                        }
                                    />
                                </ListItemButton>
                                <ListItemButton
                                    sx={{ pl: 4 }}
                                    selected={location.pathname === '/telegram-chat-staus-events'}
                                    onClick={() => navigate('/telegram-chat-staus-events')}
                                >
                                    <ListItemText
                                        primary={
                                            <span>
                <EventNoteIcon fontSize="small" sx={{ mr: 1 }}/>
                Chat Status Events
            </span>
                                        }
                                    />
                                </ListItemButton>
                            </List>
                        </Collapse>
                    </List>
                </Box>

                {/* User info bottom */}
                <Box sx={{ p: 2 }}>
                    <Box sx={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }} onClick={handleUserClick}>
                        <Avatar sx={{ mr: 1 }}>A</Avatar>
                        <Typography variant="body2">{userData.firstName}</Typography>
                    </Box>

                    <Popover
                        open={Boolean(openUserPopover)}
                        anchorEl={openUserPopover}
                        onClose={handleUserClose}
                        anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
                        transformOrigin={{ vertical: 'bottom', horizontal: 'right' }}
                    >
                        <Box sx={{ p: 2, display: 'flex', flexDirection: 'column', gap: 1 }}>
                            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 0 }}>
                                <Typography variant="body2">{userData.firstName} {userData.lastName}</Typography>
                                {userData.username && (
                                    <Typography
                                        variant="caption"
                                        color="text.secondary"
                                    >
                                        {userData.username}
                                    </Typography>
                                )}
                            </Box>
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
