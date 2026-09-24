import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import SeatPage from "./pages/SeatPage";
import SuccessPage from "./pages/SuccessPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/seat/:id" element={<SeatPage />} />
      <Route path="/success" element={<SuccessPage />} />
    </Routes>
  );
}

export default App;
