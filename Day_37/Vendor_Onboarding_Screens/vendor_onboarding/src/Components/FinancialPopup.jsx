import React, { useState } from "react";
import {
  Box,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Typography,
  Grid,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Divider,
  IconButton,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { savefinancial } from "../Api/VendorApi";

const FinancialDetailsModal = ({ open, handleClose, handleUpdate }) => {
  // State for form fields
  const [formData, setFormData] = useState({
    accountNo: "",
    beneficiaryName: "",
    bankName: "",
    branch: "",
    ifscCode: "",
    state: "",
    currency: "",
    cancelledCheque: null,
  });

  // Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };
  async function handleSubmit() {
    console.log("save financial details");
    await savefinancial(formData);
  }

  // Handle file uploads
  const handleFileUpload = (e) => {
    const file = e.target.files[0];
    setFormData({
      ...formData,
      cancelledCheque: file,
    });
  };

  return (
    <Dialog
      open={open}
      onClose={handleClose}
      maxWidth="md"
      fullWidth
      PaperProps={{
        sx: {
          borderRadius: "4px",
          boxShadow: "0px 2px 10px rgba(0, 0, 0, 0.1)",
        },
      }}
    >
      <DialogTitle
        sx={{
          bgcolor: "white",
          color: "#4ba3c7",
          fontWeight: 500,
          fontSize: "1.2rem",
          borderBottom: "1px solid #e0e0e0",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          p: 2,
        }}
      >
        Add /Edit Financial Details
        <IconButton onClick={handleClose} sx={{ color: "#999" }}>
          <CloseIcon />
        </IconButton>
      </DialogTitle>

      <DialogContent sx={{ p: 3 }}>
        <Grid container spacing={3}>
          {/* First row */}
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Account No. <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="accountNo"
              value={formData.accountNo}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Beneficiary Name <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="beneficiaryName"
              value={formData.beneficiaryName}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Bank Name <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="bankName"
              value={formData.bankName}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Branch <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="branch"
              value={formData.branch}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>

          {/* Second row */}
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              IFSC Code <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="ifscCode"
              value={formData.ifscCode}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              State <span style={{ color: "red" }}>*</span>
            </Typography>
            <FormControl fullWidth size="small">
              <Select
                displayEmpty
                name="state"
                value={formData.state}
                onChange={handleChange}
                renderValue={(selected) => {
                  if (!selected) {
                    return "Select";
                  }
                  return selected;
                }}
              >
                <MenuItem value="">
                  <em>Select</em>
                </MenuItem>
                <MenuItem value="Karnataka">Karnataka</MenuItem>
                <MenuItem value="Maharashtra">Maharashtra</MenuItem>
                <MenuItem value="Tamil Nadu">Tamil Nadu</MenuItem>
                <MenuItem value="Kerala">Kerala</MenuItem>
                {/* Add more states as needed */}
              </Select>
            </FormControl>
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Order Currency <span style={{ color: "red" }}>*</span>
            </Typography>
            <FormControl fullWidth size="small">
              <Select
                displayEmpty
                name="orderCurrency"
                value={formData.orderCurrency}
                onChange={handleChange}
                renderValue={(selected) => {
                  if (!selected) {
                    return "Select";
                  }
                  return selected;
                }}
              >
                <MenuItem value="">
                  <em>Select</em>
                </MenuItem>
                <MenuItem value="INR">INR</MenuItem>
                <MenuItem value="USD">USD</MenuItem>
                <MenuItem value="EUR">EUR</MenuItem>
                <MenuItem value="GBP">GBP</MenuItem>
                {/* Add more currencies as needed */}
              </Select>
            </FormControl>
          </Grid>

          {/* Cancelled Cheque section */}
          <Grid item xs={12}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Cancelled Cheque <span style={{ color: "red" }}>*</span>
            </Typography>
            <Box sx={{ display: "flex", alignItems: "center", gap: 2, mb: 2 }}>
              <Button
                variant="contained"
                component="label"
                sx={{
                  bgcolor: "#4ba3c7",
                  "&:hover": { bgcolor: "#3d8ba8" },
                  textTransform: "none",
                }}
              >
                Upload
                <input type="file" hidden onChange={handleFileUpload} />
              </Button>
              <Button
                variant="contained"
                sx={{
                  bgcolor: "#4ba3c7",
                  "&:hover": { bgcolor: "#3d8ba8" },
                  textTransform: "none",
                }}
              >
                Start Upload
              </Button>
            </Box>
            <Typography
              variant="subtitle2"
              color="text.secondary"
              sx={{ mb: 0.5 }}
            >
              Note:
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Please upload.xlsx |.doc |.docx
              |.xls|.pdf|.XLSX|.DOC|.DOCX|.XLS|.PDF|.ZIP|.DWG|.jpg|.jpeg|.png|.JPG|.JPEG|.PNG
              file only and maximum upload size is 50 MB for each Document.
            </Typography>
          </Grid>
        </Grid>
      </DialogContent>

      <Divider />

      <DialogActions sx={{ p: 2, justifyContent: "flex-end" }}>
        <Button
          variant="contained"
          sx={{
            bgcolor: "#6c757d",
            "&:hover": { bgcolor: "#5a6268" },
            textTransform: "none",
            mr: 1,
          }}
          onClick={handleClose}
        >
          Close
        </Button>
        <Button
          variant="contained"
          sx={{
            bgcolor: "#4ba3c7",
            "&:hover": { bgcolor: "#3d8ba8" },
            textTransform: "none",
          }}
          onClick={() => {
            handleSubmit();
            handleClose();
          }}
        >
          Update
        </Button>
      </DialogActions>
    </Dialog>
  );
};

// Example usage
function FinancialPopup() {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleUpdate = (data) => {
    console.log("Updated financial details:", data);
    // Process the updated data
  };

  return (
    <div>
      <Button variant="contained" onClick={handleOpen}>
        Open Financial Details Modal
      </Button>
      <FinancialDetailsModal
        open={open}
        handleClose={handleClose}
        handleUpdate={handleUpdate}
      />
    </div>
  );
}

export default FinancialPopup;
