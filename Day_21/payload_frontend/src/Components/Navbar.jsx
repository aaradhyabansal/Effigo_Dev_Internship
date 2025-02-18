import { Navbar, Nav, Container, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles.css";

function CustomNavbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("roles");
    navigate("/login");
  };

  const isLoggedIn = localStorage.getItem("token") !== null;

  return (
    <Navbar expand="lg" className="pastel-navbar shadow-sm">
      <Container>
        <Navbar.Brand
          className="fw-bold highlight-title"
          onClick={() => navigate("/home")}
        >
          My Batch-Payload-Processing Transaction App
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            <Nav.Link
              className="highlight-text nav-link-custom"
              onClick={() => navigate("/getallpayments")}
            >
              Transactions
            </Nav.Link>
            <Nav.Link
              className="highlight-text nav-link-custom"
              onClick={() => navigate("/addtransaction")}
            >
              Add Transaction
            </Nav.Link>
            {isLoggedIn ? (
              <Button variant="danger" className="ms-3" onClick={handleLogout}>
                Logout
              </Button>
            ) : (
              <Button
                variant="primary"
                className="ms-3"
                onClick={() => navigate("/login")}
              >
                Login
              </Button>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default CustomNavbar;
