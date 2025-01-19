import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../Components/Login";
import Welcome from "../Components/Welcome";
import Home from "../Components/Home";
import ErrorPage from "../Components/ErrorPage";
import Listtodo from "../Components/Listtodo";
import Navbar from "../Components/Navbar";
function Todo() {
  return (
    <div>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/welcome/:username" element={<Welcome />}></Route>
          <Route path="/listtodos/:username" element={<Listtodo />}></Route>
          <Route path="*" element={<ErrorPage />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default Todo;
