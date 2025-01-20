import { createContext, useState, useContext } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);
  function login(username, password) {
    if ("aaradhya" === username && "abcd1234" === password) {
      setIsAuthenticated(true);
      return true;
    }
  }
  function logout() {
    setIsAuthenticated(false);
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        setIsAuthenticated,
        login,
        logout,
        username,
        setUsername,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;
