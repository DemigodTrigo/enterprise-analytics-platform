import { createTheme } from "@mui/material/styles";


const theme = createTheme({
  palette: {
    mode: "light",
    primary: {
      main: "#1976d2"
    },
    secondary: {
      main: "#4CAF50"
    },
    background: {
      default: "#f5f7fb"
    }
  },
  typography: {
    fontFamily: "Inter, Roboto, Arial, sans-serif",
    h5: {
      fontWeight: 600
    }
  },
  shape: {
    borderRadius: 12
  }
});

export default theme;