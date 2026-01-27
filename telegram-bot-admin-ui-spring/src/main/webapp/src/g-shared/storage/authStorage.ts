import type { UserData } from "../../f-entities/auth-session/userData.ts";


let userData: UserData | null = null;

export const authStorage = {
    get(): UserData | null {
        return userData;
    },

    set(next: UserData) {
        userData = next;
        localStorage.setItem('user_data', JSON.stringify(next));
    },

    restore() {
        const raw = localStorage.getItem('user_data');
        if (raw) {
            userData = JSON.parse(raw);
        }
    },

    clear() {
        userData = null;
        localStorage.removeItem('user_data');
    },
};
