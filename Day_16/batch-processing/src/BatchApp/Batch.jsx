import { Routes, Route } from "react-router-dom";

import Homepage from "../Components/Homepage";

import Payments from "../Components/Payments";

import AddTransaction from "../Components/AddTransaction";
import Navbar from "../Components/NavBar";

function Batch() {
  return (
    <div>
      <Navbar />
      <Routes>
        <Route path="/home" element={<Homepage />} />
        <Route path="/getallpayments" element={<Payments />}></Route>
        <Route path="/addtransaction" element={<AddTransaction />}></Route>
      </Routes>
    </div>
  );
}

export default Batch;
