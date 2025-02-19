import { Navbar, Nav, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles.css";

function CustomNavbar() {
  const navigate = useNavigate();

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
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default CustomNavbar;
