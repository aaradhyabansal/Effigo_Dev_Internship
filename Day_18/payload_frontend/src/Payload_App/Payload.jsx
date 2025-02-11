import { Routes, Route } from "react-router-dom";
import CustomNavbar from "../Components/Navbar";
import Homepage from "../Components/Homepage";
import SuccessPayments from "../Components/SuccessPayments";
import FailedPayments from "../Components/FailedPayments";
import AddTransaction from "../Components/AddTransaction";

function Payload() {
  return (
    <div>
      <CustomNavbar />
      <Routes>
        <Route path="/home" element={<Homepage />} />
        <Route path="/successfulpayments" element={<SuccessPayments />} />
        <Route path="/failedpayments" element={<FailedPayments />} />
        <Route path="/addtransaction" element={<AddTransaction />} />
      </Routes>
    </div>
  );
}

export default Payload;
