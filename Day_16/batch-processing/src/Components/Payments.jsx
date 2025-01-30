import { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  Container,
  Button,
} from "@mui/material";
import styled from "styled-components";
import { deletetransactions, getAllPayments } from "../api/PaymentApi";

const Payments = () => {
  const [payments, setPayments] = useState([]);

  useEffect(() => {
    const fetchPayments = async () => {
      try {
        const response = await getAllPayments();
        if (response.data) {
          setPayments(response.data);
        }
      } catch (error) {
        console.error("Error fetching payments:", error);
      }
    };
    fetchPayments();
  }, [payments]);

  async function handleDelete(id) {
    try {
      await deletetransactions(id);
    } catch (error) {
      console.error("Error deleting transaction", error);
    }
  }

  return (
    <StyledContainer>
      <Typography
        variant="h4"
        align="center"
        gutterBottom
        className="highlight-title"
      >
        All Transactions
      </Typography>
      <TableContainer component={Paper} className="pastel-card highlight-card">
        <Table aria-label="transactions table">
          <TableHead>
            <StyledTableRow>
              <StyledTableCell>ID</StyledTableCell>
              <StyledTableCell>Transaction Id</StyledTableCell>
              <StyledTableCell>User Id</StyledTableCell>
              <StyledTableCell>Amount</StyledTableCell>
              <StyledTableCell>Status</StyledTableCell>
              <StyledTableCell>Action</StyledTableCell>
            </StyledTableRow>
          </TableHead>
          <TableBody>
            {payments.length > 0 ? (
              payments.map((payment) => (
                <StyledTableRow key={payment.id}>
                  <StyledTableCell>{payment.id}</StyledTableCell>
                  <StyledTableCell>{payment.transactionId}</StyledTableCell>
                  <StyledTableCell>{payment.userId}</StyledTableCell>
                  <StyledTableCell>{payment.amount}</StyledTableCell>
                  <StyledTableCell>{payment.payment_status}</StyledTableCell>
                  <StyledTableCell>
                    <StyledButton onClick={() => handleDelete(payment.id)}>
                      Delete
                    </StyledButton>
                  </StyledTableCell>
                </StyledTableRow>
              ))
            ) : (
              <StyledTableRow>
                <StyledTableCell colSpan={6} align="center">
                  Loading or no data available
                </StyledTableCell>
              </StyledTableRow>
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </StyledContainer>
  );
};

const StyledContainer = styled(Container)`
  background: linear-gradient(to bottom right, #f7e1d7, #d3e0ea);
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  max-width: 100% !important;
  width: 100% !important;
  margin: 0 !important;
  padding-left: 10px;
  padding-right: 10px;
`;

const StyledTableRow = styled(TableRow)`
  transition: transform 0.3s ease-in-out;
  &:hover {
    transform: translateY(-3px);
  }
`;

const StyledTableCell = styled(TableCell)`
  font-weight: bold;
  color: #5a4a4a;
  border-bottom: 1px solid #ddd;
`;

const StyledButton = styled(Button)`
  background-color: #e5989b !important;
  color: white !important;
  &:hover {
    background-color: #b5838d !important;
  }
`;

export default Payments;
