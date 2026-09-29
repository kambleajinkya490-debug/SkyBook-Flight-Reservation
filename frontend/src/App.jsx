import { BrowserRouter, Routes, Route } from "react-router-dom";

import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";
import AdminPage from "./pages/AdminPage";
import SeatPage from "./pages/SeatPage";
import SuccessPage from "./pages/SuccessPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
 
        <Route path="/register" element={<RegisterPage />} />          

        <Route path="/login" element={<LoginPage />} />

        {/* Customer Flight Search */}
        <Route path="/" element={<HomePage />} />

        {/* Admin Dashboard */}
        <Route path="/admin" element={<AdminPage />} />

        {/* Seat Selection */}
        <Route path="/seat/:flightId" element={<SeatPage />} />

        {/* Booking Success / Ticket */}
        <Route path="/success" element={<SuccessPage />} />

      </Routes>
    </BrowserRouter>
  );
}

export default App;
