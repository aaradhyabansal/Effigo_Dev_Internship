// import NavBar from "./Components/navbar";
// import Food from "./FoodApp/Food";
// import "./App.css";
// function App() {
//   return (
//     <>
//       <NavBar />
//       <Food></Food>
//     </>
//   );
// }

// export default Ap;
import { useState } from "react";
import NavBar from "./Components/Navbar";
import Food from "./FoodApp/Food";
import "./App.css";
import { BrowserRouter } from "react-router-dom";

function App() {
  const [isDarkTheme, setIsDarkTheme] = useState(false);

  const toggleTheme = () => {
    setIsDarkTheme((prevTheme) => !prevTheme);
  };

  const appStyle = {
    backgroundColor: isDarkTheme ? "#121212" : "#ffffff",
    color: isDarkTheme ? "#ffffff" : "#000000",
    minHeight: "100vh", // Ensure full-page coverage
    transition: "all 0.3s ease",
  };

  return (
    <div style={appStyle}>
      <BrowserRouter>
        <Food />
      </BrowserRouter>
    </div>
  );
}

export default App;
