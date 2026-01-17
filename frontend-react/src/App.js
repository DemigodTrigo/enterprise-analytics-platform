import {BrowserRouter, Routes, Route , Navigate} from "react-router-dom"
import LoginPage from './pages/LoginPage';
import DashboardLayout from "./layout/DashboardLayout";
import DashboardPage from './pages/DashboardPage';
import ProtectedRoute from "./auth/ProtectedRoute";
import MasterDataPage from "./pages/MasterDataPage";
import AdminPage from "./pages/AdminPage";

function App() {
  return (
  <BrowserRouter>
  <Routes>
    <Route path="/login" element={<LoginPage/>} />
    
    <Route
          path="/"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <Navigate to="/dashboard" />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />

   <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <DashboardPage />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
    <Route path="/master-data" element={
      <ProtectedRoute><MasterDataPage/></ProtectedRoute>
    }/>
    <Route path ="/admin" element={<ProtectedRoute><AdminPage /></ProtectedRoute>} />
  </Routes>
  </BrowserRouter>
  );
}

export default App;
