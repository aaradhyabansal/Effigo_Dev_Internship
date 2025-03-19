import { useState } from "react";
import {
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Typography,
  Grid,
  Divider,
  IconButton,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import { saveBusinessDetails } from "../Api/VendorApi";
import PropTypes from "prop-types";
const BusinessDetailsModal = ({ handleClose }) => {
  // State for form fields - completely replaced with the new fields
  const [formData, setFormData] = useState({
    parentOrganizationName: "N/A",
    parentCompanyType: "N/A",
    orderCurrency: "N/A",
    msmeRegistration: "N/A",
    esiNo: "N/A",
    pfNo: "N/A",
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
    await saveBusinessDetails(formData);
  }

  return (
    <Dialog
      open={open}
      maxWidth="md"
      fullWidth
      PaperProps={{
        sx: {
          borderRadius: "4px",
          boxShadow: "0px 2px 10px rgba(0, 0, 0, 0.1)",
          border: "2px solid #dee2db",
        },
      }}
    >
      <DialogTitle
        sx={{
          bgcolor: "white",
          color: "#4ba3c7",
          fontWeight: 500,
          fontSize: "1.2rem",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          p: 2,
        }}
      >
        Add /Edit Financial Details
        <IconButton sx={{ color: "#999" }}>
          <CloseIcon />
        </IconButton>
      </DialogTitle>

      <DialogContent sx={{ p: 3 }}>
        <Grid container spacing={3}>
          {/* First row */}
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Parent Organization Name <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="parentOrganizationName"
              value={formData.parentOrganizationName}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Parent Company Type <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="parentCompanyType"
              value={formData.parentCompanyType}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Order Currency <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="orderCurrency"
              value={formData.orderCurrency}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>

          {/* Second row */}
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              MSME Registration <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="msmeRegistration"
              value={formData.msmeRegistration}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              ESI No. <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="esiNo"
              value={formData.esiNo}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              PF No. <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              name="pfNo"
              value={formData.pfNo}
              onChange={handleChange}
              variant="outlined"
              size="small"
            />
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
            handleClose();
            handleSubmit();
          }}
        >
          Save
        </Button>
      </DialogActions>
    </Dialog>
  );
};

BusinessDetailsModal.propTypes = {
  handleClose: PropTypes.func.isRequired,
};

export default BusinessDetailsModal;
