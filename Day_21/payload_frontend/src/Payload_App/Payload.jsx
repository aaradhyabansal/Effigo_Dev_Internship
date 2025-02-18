import { Routes, Route } from "react-router-dom";
import CustomNavbar from "../Components/Navbar";
import Homepage from "../Components/Homepage";
import SuccessPayments from "../Components/SuccessPayments";
import FailedPayments from "../Components/FailedPayments";
import AddTransaction from "../Components/AddTransaction";
import Login from "../Components/Login";
import PrivateRoute from "../Components/PrivateComponent";

function Payload() {
  return (
    <div>
      <CustomNavbar />
      <Routes>
        <Route path="/login" element={<Login />} />

        <Route element={<PrivateRoute />}>
          <Route path="/home" element={<Homepage />} />
          <Route path="/successfulpayments" element={<SuccessPayments />} />
          <Route path="/failedpayments" element={<FailedPayments />} />
          <Route path="/addtransaction" element={<AddTransaction />} />
        </Route>
      </Routes>
    </div>
  );
}

export default Payload;
