import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "../Components/Login";
import Welcome from "../Components/Welcome";
import Home from "../Components/Home";
import ErrorPage from "../Components/ErrorPage";
import Listtodo from "../Components/Listtodo";
import Navbar from "../Components/Navbar";
import AuthProvider, { useAuth } from "../Components/AuthContext";
import TodoComponent from "../Components/TodoComponent";
function Todo() {
  function AuthenticatedRoute({ children }) {
    const authContext = useAuth();

    if (authContext.isAuthenticated) return children;
  }
  return (
    <div>
      <BrowserRouter>
        <AuthProvider>
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />}></Route>
            <Route path="/login" element={<Login />}></Route>
            <Route
              path="/welcome/:username"
              element={
                <AuthenticatedRoute>
                  <Welcome />
                </AuthenticatedRoute>
              }
            ></Route>
            <Route
              path="/listtodos/:username"
              element={
                <AuthenticatedRoute>
                  <Listtodo />
                </AuthenticatedRoute>
              }
            ></Route>
            <Route
              path="/listtodo/:id"
              element={
                <AuthenticatedRoute>
                  <TodoComponent />
                </AuthenticatedRoute>
              }
            ></Route>
            <Route path="*" element={<ErrorPage />}></Route>
          </Routes>
        </AuthProvider>
      </BrowserRouter>
    </div>
  );
}

export default Todo;
