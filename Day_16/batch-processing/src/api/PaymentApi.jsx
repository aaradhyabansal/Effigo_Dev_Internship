import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/payments",
});

export function getAllPayments() {
  return apiClient.get("/getallpayments");
}

export function addtransactions(transaction) {
  return apiClient.post("/process", transaction);
}

export function deletetransactions(id) {
  return apiClient.delete(`/delete/${id}`);
}
