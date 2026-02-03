import { atom } from 'nanostores';
import type { UserData } from "../../f-entities/auth-session/userData.ts";

const STORAGE_KEY = 'user_data';

// restore from localStorage
const raw = localStorage.getItem(STORAGE_KEY);
const initial: UserData | null = raw ? JSON.parse(raw) : null;

export const $authContext = atom<UserData | null>(initial);

$authContext.subscribe(value => {
    if (value) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(value));
    } else {
        localStorage.removeItem(STORAGE_KEY);
    }
});

export const authStore = {
    set(session: UserData) {
        $authContext.set(session);
    },

    clear() {
        $authContext.set(null);
    },
};
