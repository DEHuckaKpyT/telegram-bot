import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
    plugins: [ react() ],

    build: {
        // Clean directory before every build
        emptyOutDir: true,
    },
    server: {
        host: '127.0.0.1',
        port: 80,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
            },
            '/admin-panel/config': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
            },
        }
    }
})
