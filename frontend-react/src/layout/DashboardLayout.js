import {Box} from "@mui/material";
import TopBar from "./TopBar";
import SideBar from "./SideBar";

function DashboardLayout({ children }){
return (
    <Box sx={{ display: "flex", minHeight: "100vh", backgroundColor: "#f5f7fb" }}>
      <SideBar />

      <Box sx={{ flexGrow: 1 }}>
        <TopBar />
        <Box sx={{ p: 3 }}>
          {children}
        </Box>
      </Box>
    </Box>
  );
}

export default DashboardLayout;