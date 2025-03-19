import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/",
});

export function saveCertification(certificationPayload) {
  return apiClient.post(
    "/certification/savecertification",
    certificationPayload
  );
}
export function saveRegistration(registrationPayload) {
  return apiClient.post("/registration/saveregistration", registrationPayload);
}

export function savefinancial(financialPayload) {
  return apiClient.post("/financial/savefinancial", financialPayload);
}

export function saveRegistrationExcel(registrationPayload) {
  return apiClient.post("/registration/upload", registrationPayload, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

export function saveCertificationExcel(certificationPayload) {
  return apiClient.post("/certification/upload", certificationPayload, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
}

export function saveTurnover(turnoverPayload) {
  return apiClient.post("/turnover/saveturnover", turnoverPayload);
}

export function saveBusinessDetails(bPayload) {
  return apiClient.post("/businessdetails/save", bPayload);
}

export function saveContactDetails(cPayload) {
  return apiClient.post("/contact/save", cPayload);
}
