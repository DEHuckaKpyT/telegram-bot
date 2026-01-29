import { createTheme } from "@mui/material/styles";

export const darkTheme = createTheme({
    palette: {
        mode: "dark",
        primary: {
            main: "#90caf9",
        },
        secondary: {
            main: "#80cbc4",
        },
        background: {
            default: "#121212",
            paper: "#1e1e1e",
        },
        error: {
            main: "#ef5350"
        },
    },
});