import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { addtransactions } from "../Api/Paymentsapi";

function AddTransaction() {
  const [invoice_date, setInvoice_date] = useState("");
  const [invoice_amount, setInvoice_amount] = useState("");
  const [invoice_type, setInvoice_type] = useState("");
  const [invoices, setInvoices] = useState([]);
  const [paymentName, setPaymentName] = useState("");
  const [pay_id, setPay_id] = useState("");
  const [pay_type, setPay_type] = useState("");
  const [paymentReceiverName, setPaymentReceiverName] = useState("");
  const [amount, setAmount] = useState("");
  const [companyCode, setCompanyCode] = useState("");
  const [transactionCode, setTransactionCode] = useState("");
  const [plant, setPlant] = useState("");
  const [gst, setGst] = useState("");
  const [status, setStatus] = useState("");
  const [transactions, setTransactions] = useState([]);

  const navigate = useNavigate();

  function handleAddTransaction() {
    const newInvoice = { invoice_date, invoice_type, invoice_amount };
    setInvoices((prevInvoices) => [...prevInvoices, newInvoice]);

    setInvoice_amount("");
    setInvoice_date("");
    setInvoice_type("");
  }

  async function handleOnClick() {
    const newPayment = {
      paymentName,
      pay_id,
      pay_type,
      invoices,
      paymentReceiverName,
      amount,
      companyCode,
      transactionCode,
      plant,
      gst,
      status,
    };

    const updatedTransactions = newPayment;
    setTransactions(updatedTransactions);
    console.log(updatedTransactions);
    await addtransactions(updatedTransactions);
    navigate("/home");
  }

  return (
    <StyledContainer>
      <StyledBox>
        <h2>Add New Transaction</h2>
        <form>
          <StyledGrid>
            <StyledInput
              type="text"
              placeholder="Payment Name"
              value={paymentName}
              onChange={(e) => setPaymentName(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Payment ID"
              value={pay_id}
              onChange={(e) => setPay_id(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Payment Type"
              value={pay_type}
              onChange={(e) => setPay_type(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Status"
              value={status}
              onChange={(e) => setStatus(e.target.value)}
              required
            />
          </StyledGrid>
          <StyledInvoiceGrid className="pb-3">
            <StyledInput
              type="text"
              placeholder="Invoice Type"
              value={invoice_type}
              onChange={(e) => setInvoice_type(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Invoice Date"
              value={invoice_date}
              onChange={(e) => setInvoice_date(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Invoice Amount"
              value={invoice_amount}
              onChange={(e) => setInvoice_amount(e.target.value)}
              required
            />
            <StyledButton onClick={handleAddTransaction}>
              + Add Invoice
            </StyledButton>
          </StyledInvoiceGrid>

          <StyledGrid>
            <StyledInput
              type="text"
              placeholder="Payment Receiver Name"
              value={paymentReceiverName}
              onChange={(e) => setPaymentReceiverName(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Company Code"
              value={companyCode}
              onChange={(e) => setCompanyCode(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Transaction Code"
              value={transactionCode}
              onChange={(e) => setTransactionCode(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="Plant"
              value={plant}
              onChange={(e) => setPlant(e.target.value)}
              required
            />
            <StyledInput
              type="text"
              placeholder="GST"
              value={gst}
              onChange={(e) => setGst(e.target.value)}
              required
            />
          </StyledGrid>

          <StyledButton type="button" onClick={handleOnClick}>
            Submit Transaction
          </StyledButton>
        </form>
      </StyledBox>
    </StyledContainer>
  );
}

const StyledContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #ece9e6, #ffffff);
`;

const StyledBox = styled.div`
  background: #fff;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
  width: 500px;
  max-width: 90%;
  text-align: center;
`;

const StyledInvoiceGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
  align-items: center;
`;

const StyledGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 15px;
  margin-bottom: 15px;
`;

const StyledInput = styled.input`
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  outline: none;
  font-size: 14px;
  transition: 0.3s;

  &:focus {
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
  }
`;

const StyledButton = styled.button`
  width: 100%;
  padding: 12px;
  background-color: #e5989b; /* Pastel Button Background */
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out, box-shadow 0.3s ease-in-out,
    transform 0.3s ease-in-out;

  &:hover {
    background-color: #b5838d; /* Darker shade on hover */
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
    transform: translateY(-3px); /* Lift effect on hover */
  }
`;

export default AddTransaction;
