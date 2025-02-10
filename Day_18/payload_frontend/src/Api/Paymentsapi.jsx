import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/payments",
});

export function getsuccessfulPayments() {
  return apiClient.get("/successful");
}

export function getfailedPayments() {
  return apiClient.get("/failed");
}

export function addtransactions(transaction) {
  return apiClient.post("/payments/convert", transaction);
}

export function deletetransactions(id) {
  return apiClient.delete(`/delete/${id}`);
}
