export interface WebStorage {
    save: (key: string, value: any) => void;

    delete: (key: string) => void;

    get: <T>(key: string) => T | null;
}

export const webStorage: WebStorage = Object.freeze({
    save(key: string, value: any) {
        localStorage.setItem(key, JSON.stringify(value));
    },
    delete(key: string) {
        localStorage.removeItem(key);
    },
    get<T>(key: string) {
        const value = localStorage.getItem(key);
        if (!value) return null;
        return JSON.parse(value) as T;
    },
});
