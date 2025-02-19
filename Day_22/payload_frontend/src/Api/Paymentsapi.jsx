import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/payments",
  headers: {
    "Content-Type": "application/json",
  },
});

// 🔹 Add interceptor to attach token to every request
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      // Set Authorization header with Bearer prefix
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 🔹 Fetch successful payments
export function getsuccessfulPayments() {
  return apiClient.get("/successful");
}

// 🔹 Fetch failed payments (Explicitly logs headers)
export function getfailedPayments() {
  return apiClient
    .get("/failed")
    .then((response) => {
      console.log("Request Headers:", {
        Authorization: response.config.headers.Authorization,
        "Content-Type": response.config.headers["Content-Type"],
      });
      console.log("Response Headers:", response.headers);
      return response;
    })
    .catch((error) => {
      console.error("Request Failed:", error);
      if (error.config) {
        console.log("Failed Request Headers:", {
          Authorization: error.config.headers.Authorization,
          "Content-Type": error.config.headers["Content-Type"],
        });
      }
      throw error;
    });
}

// 🔹 Add transactions
export function addtransactions(transaction) {
  return apiClient.post("/convert", transaction);
}

// 🔹 Delete unsuccessful transactions
export function deleteUntransactions(id) {
  return apiClient.delete(`/failedpayments/${id}`);
}

// 🔹 Delete successful transactions
export function deleteSutransactions(id) {
  return apiClient.delete(`/successfulpayments/${id}`);
}

// 🔹 Get JWT Token (Login)
export function getToken(body) {
  return apiClient.post("/signin", body);
}

// 🔹 Charge a card (Stripe)
export function getCharge(body) {
  return apiClient.post("/charge", body);
}
