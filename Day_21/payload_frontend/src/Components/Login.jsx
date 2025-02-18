import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getToken } from "../Api/Paymentsapi";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      setError("");
      const response = await getToken({ username, password });
      const data = response.data;

      if (response.status === 200) {
        localStorage.setItem("token", data.jwtToken);
        localStorage.setItem("username", data.username);
        localStorage.setItem("roles", JSON.stringify(data.roles));

        navigate("/home");
      } else {
        setError(data.message || "Invalid credentials!");
      }
    } catch (error) {
      setError(error.response?.data?.message || "Invalid credentials!");
    }
  };

  const loginContainerStyle = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "100vh",
    backgroundColor: "#f5f5f5", // Light pastel background
  };

  const loginCardStyle = {
    backgroundColor: "#ffffff",
    padding: "2rem",
    borderRadius: "8px",
    boxShadow: "0 4px 10px rgba(0, 0, 0, 0.1)",
    width: "100%",
    maxWidth: "400px",
    textAlign: "center",
  };

  const headingStyle = {
    fontSize: "2rem",
    color: "#333",
    marginBottom: "1rem",
  };

  const inputStyle = {
    width: "100%",
    padding: "10px",
    margin: "10px 0",
    borderRadius: "5px",
    border: "1px solid #ddd",
    fontSize: "1rem",
    backgroundColor: "#fafafa", // Light pastel background
    transition: "border 0.3s ease",
  };

  const inputFocusStyle = {
    borderColor: "#ff70a6", // Pastel pink focus
    outline: "none",
  };

  const buttonStyle = {
    width: "100%",
    padding: "10px",
    backgroundColor: "#ff70a6", // Pastel pink
    color: "white",
    border: "none",
    borderRadius: "5px",
    fontSize: "1rem",
    cursor: "pointer",
    transition: "background-color 0.3s ease",
  };

  const buttonHoverStyle = {
    backgroundColor: "#ff4889", // Darker pink on hover
  };

  const errorMessageStyle = {
    color: "#ff3e4d", // Red color for errors
    marginBottom: "1rem",
    fontSize: "1rem",
    fontWeight: "bold",
  };

  return (
    <div style={loginContainerStyle}>
      <div style={loginCardStyle}>
        <h2 style={headingStyle}>Login</h2>
        {error && <p style={errorMessageStyle}>{error}</p>}
        <input
          type="text"
          placeholder="Username"
          style={inputStyle}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          style={inputStyle}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button
          style={buttonStyle}
          onMouseOver={(e) =>
            (e.target.style.backgroundColor = buttonHoverStyle.backgroundColor)
          }
          onMouseOut={(e) =>
            (e.target.style.backgroundColor = buttonStyle.backgroundColor)
          }
          onClick={handleLogin}
        >
          Login
        </button>
      </div>
    </div>
  );
}

export default Login;
