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
  return apiClient.post("/convert", transaction);
}

export function deleteUntransactions(id) {
  return apiClient.delete(`/failedpayments/${id}`);
}
export function deleteSutransactions(id) {
  return apiClient.delete(`/successfulpayments/${id}`);
}