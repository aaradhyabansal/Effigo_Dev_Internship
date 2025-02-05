import { Container, Row, Col, Card, Button, Alert } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { useNavigate } from "react-router-dom";
import "./styles.css";

export default function Homepage() {
  const navigate = useNavigate();
  return (
    <Container
      fluid
      className="min-vh-100 bg-pastel d-flex flex-column align-items-center justify-content-start py-5"
    >
      <h1 className="mb-4 fw-bold text-dark highlight-title">Dashboard</h1>
      <p className="text-center text-muted fs-5">
        Welcome to your dashboard. Manage your activities efficiently.
      </p>
      <Row className="g-4 w-100 px-4 d-flex justify-content-center">
        <Col md={5} className="d-flex">
          <Card className="shadow-lg border-0 w-100 hover-effect pastel-card highlight-card">
            <Card.Body className="d-flex flex-column justify-content-between p-4">
              <div>
                <Card.Title className="fs-4 fw-semibold highlight-text">
                  Payment Info
                </Card.Title>
                <Card.Text className="text-muted fs-6">
                  View and manage your payment transactions seamlessly.
                </Card.Text>
              </div>
              <Button
                variant="pastel-button highlight-button"
                className="mt-3"
                onClick={() => navigate(`/getallpayments`)}
              >
                View Transactions
              </Button>
            </Card.Body>
          </Card>
        </Col>
        <Col md={5} className="d-flex">
          <Card className="shadow-lg border-0 w-100 hover-effect pastel-card highlight-card">
            <Card.Body className="d-flex flex-column justify-content-between p-4">
              <div>
                <Card.Title className="fs-4 fw-semibold highlight-text">
                  Batch Processing
                </Card.Title>
                <Card.Text className="text-muted fs-6">
                  Add new transactions and start batch processing effortlessly.
                </Card.Text>
              </div>
              <Button
                variant="pastel-button highlight-button"
                className="mt-3"
                onClick={() => navigate(`/addtransaction`)}
              >
                Add Transaction
              </Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
      <Row className="mt-5 w-100 px-4 d-flex justify-content-center">
        <Col md={5} className="d-flex">
          <Card className="shadow-lg border-0 w-100 hover-effect pastel-card highlight-card">
            <Card.Body className="d-flex flex-column justify-content-between p-4">
              <div>
                <Card.Title className="fs-4 fw-semibold highlight-text">
                  Notifications
                </Card.Title>
                <Alert
                  variant="info"
                  className="mt-2 pastel-alert highlight-alert"
                >
                  You have new notifications.
                </Alert>
              </div>
              <Button
                variant="outline-pastel highlight-button"
                className="mt-3"
              >
                View Notifications
              </Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </Container>
  );
}
