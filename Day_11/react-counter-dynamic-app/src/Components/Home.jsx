import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  function handleLogin() {
    navigate("/login");
  }

  return (
    <div style={{ fontFamily: "Arial, sans-serif" }}>
      {/* Navigation Bar */}
      <nav
        style={{ backgroundColor: "#282c34", padding: "10px", color: "white" }}
      >
        <h1 style={{ margin: 0, fontSize: "24px" }}>My Website</h1>
      </nav>

      {/* Welcome Section */}
      <header style={{ textAlign: "center", marginTop: "30px" }}>
        <h1>Welcome to My Homepage</h1>
        <p>Explore the world of React with this basic homepage example.</p>
      </header>

      {/* Main Content */}
      <main style={{ padding: "20px" }}>
        <h2>About Us</h2>
        <p>
          This is a simple React homepage designed to demonstrate how to
          structure components and content.
        </p>
        {/* Corrected the typo in onClick */}
        <button type="button" onClick={handleLogin}>
          Go to Login
        </button>

        <h2>Features</h2>
        <ul>
          <li>Easy to use</li>
          <li>Responsive design</li>
          <li>Customizable components</li>
        </ul>

        <h2>Contact Us</h2>
        <p>Email: contact@mywebsite.com</p>
      </main>

      {/* Footer */}
      <footer
        style={{
          textAlign: "center",
          marginTop: "30px",
          padding: "10px",
          backgroundColor: "#f1f1f1",
        }}
      >
        <p>&copy; 2025 My Website. All Rights Reserved.</p>
      </footer>
    </div>
  );
}

export default Home;
