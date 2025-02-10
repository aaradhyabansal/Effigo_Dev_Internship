import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
// import { addtransactions } from "../api/PaymentApi";

function AddTransaction() {
  const [transactionId, setTransactionId] = useState("");
  const [paymentStatus, setPaymentStatus] = useState("");
  const [amount, setAmount] = useState(0.0);
  const [userId, setUserId] = useState("");
  const [transactions, setTransactions] = useState([]);
  const navigate = useNavigate();

  function handleAddTransaction() {
    const newTransaction = {
      userId,
      transactionId,
      amount,
      payment_status: paymentStatus,
    };

    setTransactions((prevTransactions) => [
      ...prevTransactions,
      newTransaction,
    ]);

    setUserId("");
    setTransactionId("");
    setAmount("");
    setPaymentStatus("");
  }

  async function handleOnClick() {
    await addtransactions(transactions);
    navigate("/getallpayments");
  }

  return (
    <StyledContainer>
      <StyledBox>
        <h2>Add New Transaction</h2>
        <form>
          <StyledInput
            type="text"
            placeholder="User ID"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
          />
          <StyledInput
            type="text"
            placeholder="Transaction ID"
            value={transactionId}
            onChange={(e) => setTransactionId(e.target.value)}
            required
          />
          <StyledInput
            type="number"
            placeholder="Amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
          <StyledInput
            type="text"
            placeholder="Status"
            value={paymentStatus}
            onChange={(e) => setPaymentStatus(e.target.value)}
            required
          />
          <StyledButton type="button" onClick={handleAddTransaction}>
            Add Another Transaction
          </StyledButton>
          <StyledButton type="button" onClick={handleOnClick}>
            Submit
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
  background: linear-gradient(to bottom right, #f7e1d7, #d3e0ea);
`;

const StyledBox = styled.div`
  background: white;
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 350px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
`;

const StyledInput = styled.input`
  width: 100%;
  padding: 10px;
  margin: 8px 0;
  border: 1px solid #b5838d;
  border-radius: 6px;
  outline: none;
`;

const StyledButton = styled.button`
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  background-color: #e5989b;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.3s;
  &:hover {
    background-color: #b5838d;
  }
`;

export default AddTransaction;
