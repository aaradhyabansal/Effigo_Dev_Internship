import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isAuth, setIsAuth] = useState(false);
  const authContext = useAuth();
  const navigate = useNavigate();
  function handleUsername(e) {
    setUsername(e.target.value);
    console.log(username);
  }
  function handlePassword(e) {
    setPassword(e.target.value);
    console.log(password);
  }
  function handleAuth() {
    if (authContext.login(username, password)) {
      authContext.setUsername(username);
      setIsAuth(true);
      navigate(`/welcome/${username}`);
    }
  }
  return (
    <div>
      <div>
        <label>Username</label>
        <input
          type="text"
          name="username"
          value={username}
          onChange={handleUsername}
        />
      </div>
      <div>
        <label>Password</label>
        <input
          type="password"
          name="password"
          value={password}
          onChange={handlePassword}
        />
      </div>
      <div>
        <button type="submit" name="login" onClick={handleAuth}>
          Submit
        </button>
      </div>
      <div>
        {isAuth ? (
          <h2>Authentication Successful! Enjoy</h2>
        ) : (
          <h2>Authentication failed!!! Please try again</h2>
        )}
      </div>
    </div>
  );
}

export default Login;
