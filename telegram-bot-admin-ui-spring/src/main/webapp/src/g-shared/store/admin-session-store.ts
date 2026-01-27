import type { AdminSession } from "../../f-entities/auth-session/admin-session.ts";
import { atom } from 'nanostores';

const STORAGE_KEY = 'admin_session';

// restore from localStorage
const raw = localStorage.getItem(STORAGE_KEY);
const initial: AdminSession | null = raw ? JSON.parse(raw) : null;

export const $adminSession = atom<AdminSession | null>(initial);

$adminSession.subscribe(value => {
    if (value) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(value));
    } else {
        localStorage.removeItem(STORAGE_KEY);
    }
});

export const adminSessionStore = {
    set(session: AdminSession) {
        $adminSession.set(session);
    },

    clear() {
        $adminSession.set(null);
    },
};
