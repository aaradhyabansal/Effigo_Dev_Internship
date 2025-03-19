import { Route, Routes } from "react-router-dom";
import Sidebar from "./Components/Sidebar";
import Regis from "./Components/RegisFinancial";
import RegistrationSection from "./Components/RegistrationSection";
import Footer from "./Components/Footer";
import Busi from "./Components/Busi";

function Vendor() {
  return (
    <div>
      <Sidebar />
      <Routes>
        <Route path="/businessdetails" element={<Busi />}></Route>
        <Route path="/financialinfo" element={<Regis />}></Route>
        <Route
          path="/registrationcertifications"
          element={<RegistrationSection />}
        ></Route>
      </Routes>
      <Footer />
    </div>
  );
}

export default Vendor;
