import React, { useState } from "react";
import {
  TextField,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Grid,
} from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { saveRegistration, saveRegistrationExcel } from "../Api/VendorApi";

const RegistrationForm = ({ open, handleClose }) => {
  const [formData, setFormData] = useState({
    registeredWith: "",
    classOfRegistration: "",
    placeOfRegistration: "",
    serviceRegTo: "",
    effectiveDate: null,
    expiryDate: null,
    documentName: "",
    document: null,
  });
  const [file, setFile] = useState(null);
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleDateChange = (name, value) => {
    setFormData({ ...formData, [name]: value });
  };

  const handleFileChange = (event) => {
    setFormData({ ...formData, document: event.target.files[0] });
  };
  const handleUploadFileChange = (event) => {
    const selectedFile = event.target.files[0];
    setFile(selectedFile);
  };

  async function handleSubmit() {
    await saveRegistration(formData);
    console.log(formData);
    handleClose();
  }
  async function handleFileUpload() {
    if (!file) {
      console.error("No file selected");
      return;
    }

    const formData = new FormData();
    formData.append("file", file); // "file" should match backend's @RequestParam

    try {
      await saveRegistrationExcel(formData); // Ensure this method handles FormData properly
      console.log("File uploaded successfully");
    } catch (error) {
      console.error("File upload failed", error);
    }
  }

  return (
    <Dialog open={open} onClose={handleClose} fullWidth maxWidth="md">
      <DialogTitle>Registration Form</DialogTitle>
      <DialogContent>
        <Grid container spacing={2}>
          {[
            { label: "Registered With", name: "registeredWith" },
            { label: "Class of Registration", name: "classOfRegistration" },
            { label: "Place of Registration", name: "placeOfRegistration" },
            { label: "Services Registered To", name: "servicesRegisteredTo" },
          ].map((field) => (
            <Grid item xs={6} key={field.name}>
              <TextField
                fullWidth
                required
                label={field.label}
                name={field.name}
                value={formData[field.name]}
                onChange={handleChange}
              />
            </Grid>
          ))}

          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <Grid item xs={6}>
              <DatePicker
                label="Effective Date"
                value={formData.effectiveDate}
                onChange={(newValue) =>
                  handleDateChange("effectiveDate", newValue)
                }
                renderInput={(params) => (
                  <TextField {...params} fullWidth required />
                )}
              />
            </Grid>
            <Grid item xs={6}>
              <DatePicker
                label="Expiry Date"
                value={formData.expiryDate}
                onChange={(newValue) =>
                  handleDateChange("expiryDate", newValue)
                }
                renderInput={(params) => (
                  <TextField {...params} fullWidth required />
                )}
              />
            </Grid>
          </LocalizationProvider>

          <Grid item xs={6}>
            <TextField
              fullWidth
              label="Document Name"
              name="documentName"
              value={formData.documentName}
              onChange={handleChange}
            />
          </Grid>
          <Grid item xs={6}>
            <Button
              fullWidth
              variant="contained"
              component="label"
              startIcon={<CloudUploadIcon />}
            >
              Upload Document
              <input type="file" hidden onChange={handleUploadFileChange} />
            </Button>
            <Button
              onClick={handleFileUpload}
              fullWidth
              type="submit"
              variant="contained"
              component="label"
              startIcon={<CloudUploadIcon />}
            >
              Save Document
            </Button>
            {formData.document && <p>{formData.document.name}</p>}
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose} variant="outlined">
          Close
        </Button>
        <Button onClick={handleSubmit} variant="contained" color="primary">
          Save
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default RegistrationForm;
