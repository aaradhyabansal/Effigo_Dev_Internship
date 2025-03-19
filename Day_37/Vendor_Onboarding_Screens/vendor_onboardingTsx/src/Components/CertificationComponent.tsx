import React, { useState } from "react";
import {
  Box,
  TextField,
  Button,
  Typography,
  Paper,
  Grid,
  IconButton,
  InputAdornment,
  Dialog,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import SaveIcon from "@mui/icons-material/Save";
import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import { saveCertification, saveCertificationExcel } from "../Api/VendorApi";

const CertificationForm = ({
  open,
  handleClose,
}: {
  open: boolean;
  handleClose: () => void;
}) => {
  const [formData, setFormData] = useState<{
    certifiedBy: string;
    certificateNo: number;
    issuer: string;
    placeOfCert: string;
    effectiveDate: Date | null;
    expiryDate: Date | null;
    documentName: string;
    document: File | null;
  }>({
    certifiedBy: "",
    certificateNo: 0,
    issuer: "",
    placeOfCert: "",
    effectiveDate: null,
    expiryDate: null,
    documentName: "",
    document: null,
  });

  const [file, setFile] = useState<File | null>(null);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleUploadFileChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const selectedFile = event.target.files?.[0]; // Use optional chaining to avoid errors
    if (selectedFile) {
      setFile(selectedFile);
    }
  };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile: File | null = e.target.files?.[0] || null; // Ensure it's File or null
    setFormData({
      ...formData,
      document: selectedFile, // ✅ Now TypeScript won't complain
    });
  };

  async function handleSubmit() {
    console.log("hi from submit");
    console.log(formData);
    await saveCertification(formData);
    console.log("Form submitted:", formData);
  }
  async function handleFileUpload() {
    if (!file) {
      console.error("No file selected");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      await saveCertificationExcel(formData);
      console.log("File uploaded successfully");
    } catch (error) {
      console.error("File upload failed", error);
    }
  }
  return (
    <Dialog open={open} onClose={handleClose}>
      <Paper
        elevation={3}
        sx={{ maxWidth: 900, margin: "0 auto", position: "relative" }}
      >
        <Box
          sx={{
            borderBottom: "1px solid #e0e0e0",
            padding: 2,
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Typography variant="h6" component="h2" sx={{ color: "#2c8daa" }}>
            Certifications Form
          </Typography>
          <IconButton size="small">
            <CloseIcon />
          </IconButton>
        </Box>

        <Box component="form" onSubmit={handleSubmit} sx={{ padding: 3 }}>
          <Grid container spacing={3}>
            {/* First row */}
            <Grid item xs={12} sm={3}>
              <Typography variant="subtitle1" gutterBottom>
                Certified By <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                required
                fullWidth
                name="certifiedBy"
                value={formData.certifiedBy}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
              />
            </Grid>
            <Grid item xs={12} sm={3}>
              <Typography variant="subtitle1" gutterBottom>
                Certificate No. <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                required
                fullWidth
                name="certificateNo"
                value={formData.certificateNo}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
              />
            </Grid>
            <Grid item xs={12} sm={3}>
              <Typography variant="subtitle1" gutterBottom>
                Issuer <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                required
                fullWidth
                name="issuer"
                value={formData.issuer}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
              />
            </Grid>
            <Grid item xs={12} sm={3}>
              <Typography variant="subtitle1" gutterBottom>
                Place of Certification <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                required
                fullWidth
                name="placeOfCert"
                value={formData.placeOfCert}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
              />
            </Grid>

            {/* Second row */}
            <Grid item xs={12} sm={3}>
              <Typography variant="subtitle1" gutterBottom>
                Effective Date <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                required
                fullWidth
                name="effectiveDate"
                type="date"
                value={formData.effectiveDate}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <CalendarTodayIcon
                        color="action"
                        sx={{
                          backgroundColor: "#e0e0e0",
                          padding: "5px",
                          borderRadius: "0 4px 4px 0",
                          margin: "-7px -13px",
                        }}
                      />
                    </InputAdornment>
                  ),
                }}
              />
            </Grid>
            <Grid item xs={12} sm={3}>
              <Typography variant="subtitle1" gutterBottom>
                Expiry Date <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                required
                fullWidth
                name="expiryDate"
                type="date"
                value={formData.expiryDate}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">
                      <CalendarTodayIcon
                        color="action"
                        sx={{
                          backgroundColor: "#e0e0e0",
                          padding: "5px",
                          borderRadius: "0 4px 4px 0",
                          margin: "-7px -13px",
                        }}
                      />
                    </InputAdornment>
                  ),
                }}
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <Typography variant="subtitle1" gutterBottom>
                Document Name <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                fullWidth
                name="documentName"
                value={formData.documentName}
                onChange={handleChange}
                variant="outlined"
                size="small"
                margin="dense"
              />
            </Grid>

            {/* Upload section */}
            <Grid item xs={12}>
              <Typography variant="subtitle1" gutterBottom>
                Upload Document <span style={{ color: "red" }}>*</span>
              </Typography>
              <Box sx={{ display: "flex", gap: 2, mb: 2 }}>
                <Button
                  variant="contained"
                  component="label"
                  sx={{
                    backgroundColor: "#2c8daa",
                    "&:hover": { backgroundColor: "#236e87" },
                  }}
                >
                  Upload
                  <input type="file" hidden onChange={handleUploadFileChange} />
                </Button>
                <Button
                  onClick={handleFileUpload}
                  variant="contained"
                  sx={{
                    backgroundColor: "#2c8daa",
                    "&:hover": { backgroundColor: "#236e87" },
                  }}
                >
                  Start Upload
                </Button>
              </Box>
              <Typography variant="subtitle2" sx={{ mb: 1 }}>
                Note:
              </Typography>
              <Typography variant="body2" sx={{ color: "#666" }}>
                Please upload .xlsx | .doc | .docx | .xls| .pdf| .XLSX| .DOC|
                .DOCX| .XLS| .PDF| .ZIP| .DWG| .jpg| .jpeg| .png| .JPG| .JPEG|
                .PNG file only and Maximum Upload Size is 50 MB for each
                Document.
              </Typography>
            </Grid>
          </Grid>

          {/* Form buttons */}
          <Box
            sx={{ display: "flex", justifyContent: "flex-end", gap: 1, mt: 3 }}
          >
            <Button
              onClick={handleClose}
              variant="contained"
              color="inherit"
              startIcon={<CloseIcon />}
              sx={{
                backgroundColor: "#6c757d",
                color: "white",
                "&:hover": { backgroundColor: "#5a6268" },
              }}
            >
              Close
            </Button>
            <Button
              onClick={() => {
                console.log("button pressed");
                handleSubmit();
              }}
              type="submit"
              variant="contained"
              startIcon={<SaveIcon />}
              sx={{
                backgroundColor: "#2c8daa",
                "&:hover": { backgroundColor: "#236e87" },
              }}
            >
              Save
            </Button>
          </Box>
        </Box>
      </Paper>
    </Dialog>
  );
};

export default CertificationForm;
