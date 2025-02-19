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
import { deleteUntransactions, getfailedPayments } from "../Api/Paymentsapi";
import Pagination from "./Pagination";

const FailedPayments = () => {
  const [payments, setPayments] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [paymentsPerPage, setPaymentsPerPage] = useState(10);
  const [selectedInvoices, setSelectedInvoices] = useState([]);
  const [showInvoiceOverlay, setShowInvoiceOverlay] = useState(false);
  const lastPage = currentPage * paymentsPerPage;
  const firstPage = lastPage - paymentsPerPage;

  useEffect(() => {
    const fetchPayments = async () => {
      try {
        const response = await getfailedPayments();
        if (response.data) {
          setPayments(response.data);
        }
      } catch (error) {
        console.error("Error fetching payments:", error);
      }
    };
    fetchPayments();
  }, []);
  const currentPages = payments.slice(firstPage, lastPage);
  const totalPayments = payments.length;
  const handleViewInvoices = (invoices) => {
    setSelectedInvoices(invoices);
    setShowInvoiceOverlay(true);
  };

  const handleHideInvoices = () => {
    setShowInvoiceOverlay(false);
  };

  async function handleOnClick(id) {
    await deleteUntransactions(id);
    console.log(id);
  }

  return (
    <div>
      <StyledContainer>
        <Typography variant="h4" align="center" gutterBottom>
          All Transactions
        </Typography>

        {/* Overlay for Invoices */}
        {showInvoiceOverlay && (
          <Overlay>
            <InvoiceContainer>
              <Typography variant="h5" align="center" gutterBottom>
                Invoices
              </Typography>
              <Table>
                <TableHead>
                  <StyledTableRow>
                    <StyledTableCell>ID</StyledTableCell>
                    <StyledTableCell>Invoice Amount</StyledTableCell>
                    <StyledTableCell>Invoice Date</StyledTableCell>
                    <StyledTableCell>Invoice Type</StyledTableCell>
                  </StyledTableRow>
                </TableHead>
                <TableBody>
                  {selectedInvoices.map((invoice) => (
                    <StyledTableRow key={invoice.id}>
                      <StyledTableCell>{invoice.id}</StyledTableCell>
                      <StyledTableCell>
                        {invoice.invoice_amount}
                      </StyledTableCell>
                      <StyledTableCell>
                        {invoice.invoice_date || "N/A"}
                      </StyledTableCell>
                      <StyledTableCell>{invoice.invoice_type}</StyledTableCell>
                    </StyledTableRow>
                  ))}
                  <StyledTableRow>
                    <StyledTableCell colSpan={4} align="center">
                      <StyledButton onClick={handleHideInvoices}>
                        Hide
                      </StyledButton>
                    </StyledTableCell>
                  </StyledTableRow>
                </TableBody>
              </Table>
            </InvoiceContainer>
          </Overlay>
        )}

        {/* Transactions Table */}
        <TableContainer
          component={Paper}
          className={`pastel-card highlight-card ${
            showInvoiceOverlay ? "blurred" : ""
          }`}
        >
          <Table aria-label="transactions table">
            <TableHead>
              <StyledTableRow>
                <StyledTableCell>ID</StyledTableCell>
                <StyledTableCell>Transaction Code</StyledTableCell>
                <StyledTableCell>Company Code</StyledTableCell>
                <StyledTableCell>GST</StyledTableCell>
                <StyledTableCell>Payment Id</StyledTableCell>
                <StyledTableCell>Payment Type</StyledTableCell>
                <StyledTableCell>Payment Name</StyledTableCell>
                <StyledTableCell>Payment Receiver Name</StyledTableCell>
                <StyledTableCell>Plant</StyledTableCell>
                <StyledTableCell>Payment Failure Reason</StyledTableCell>
                <StyledTableCell>Status Code</StyledTableCell>
                <StyledTableCell>Amount</StyledTableCell>
                <StyledTableCell>Invoices</StyledTableCell>
                <StyledTableCell>Action</StyledTableCell>
              </StyledTableRow>
            </TableHead>
            <TableBody>
              {currentPages.length > 0 ? (
                currentPages.map((payment) => (
                  <StyledTableRow key={payment.id}>
                    <StyledTableCell>{payment.id}</StyledTableCell>
                    <StyledTableCell>{payment.transactionCode}</StyledTableCell>
                    <StyledTableCell>{payment.companyCode}</StyledTableCell>
                    <StyledTableCell>{payment.gst}</StyledTableCell>
                    <StyledTableCell>{payment.pay_id}</StyledTableCell>
                    <StyledTableCell>{payment.pay_type}</StyledTableCell>
                    <StyledTableCell>{payment.paymentName}</StyledTableCell>
                    <StyledTableCell>
                      {payment.paymentReceiverName}
                    </StyledTableCell>
                    <StyledTableCell>{payment.plant}</StyledTableCell>
                    <StyledTableCell>{payment.reason_failure}</StyledTableCell>
                    <StyledTableCell>{payment.status}</StyledTableCell>
                    <StyledTableCell>{payment.amount}</StyledTableCell>
                    <StyledTableCell>
                      <StyledButton
                        onClick={() => handleViewInvoices(payment.invoices)}
                      >
                        View Invoices
                      </StyledButton>
                    </StyledTableCell>
                    <StyledTableCell>
                      <StyledButton onClick={() => handleOnClick(payment.id)}>
                        Delete
                      </StyledButton>
                    </StyledTableCell>
                  </StyledTableRow>
                ))
              ) : (
                <StyledTableRow>
                  <StyledTableCell colSpan={14} align="center">
                    Loading or no data available
                  </StyledTableCell>
                </StyledTableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      </StyledContainer>
      <Pagination
        totalPayments={totalPayments}
        paymentsPerPage={paymentsPerPage}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </div>
  );
};

/* Styled Components */
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

/* Overlay Styles */
const Overlay = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 10;
`;

const InvoiceContainer = styled(Paper)`
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 60%;
  max-height: 80vh;
  overflow-y: auto;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
`;

/* Blur background when overlay is open */
const TableContainerBlur = styled(TableContainer)`
  &.blurred {
    filter: blur(4px);
  }
`;

export default FailedPayments;
