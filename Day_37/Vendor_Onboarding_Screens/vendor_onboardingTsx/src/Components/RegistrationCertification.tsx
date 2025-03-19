import { useState } from "react";
import { Box, Button, Typography, Paper, Grid, Modal } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import FinancialPopup from "./FinancialPopup";

function RegistrationCertification() {
  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [financialDetails] = useState({
    accountNo: "N/A",
    beneficiaryName: "N/A",
    bankName: "N/A",
    branch: "N/A",
    ifscCode: "N/A",
    state: "N/A",
    cancelledCheque: "N/A",
  });

  return (
    <Box sx={{ width: "100%", maxWidth: "1200px", margin: "0 auto" }}>
      {/* Financial Details Section */}
      <Paper sx={{ p: 2, mb: 3 }}>
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            mb: 2,
          }}
        >
          <Typography variant="h6" sx={{ color: "#2196f3", fontWeight: 500 }}>
            Financial Details <span style={{ color: "red" }}>*</span>
          </Typography>
          <Box>
            <Button
              variant="contained"
              startIcon={<AddIcon />}
              sx={{
                mr: 1,
                bgcolor: "#2196f3",
                "&:hover": { bgcolor: "#1976d2" },
              }}
              onClick={handleOpen} // Open the popup
            >
              Add / Edit
            </Button>
            <Button variant="contained" sx={{ bgcolor: "#2196f3" }}>
              Close
            </Button>
          </Box>
        </Box>

        <Grid container spacing={3}>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Account No. <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.accountNo}
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Beneficiary Name <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.beneficiaryName}
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Bank Name <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">{financialDetails.bankName}</Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Branch <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">{financialDetails.branch}</Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              IFSC Code<span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">{financialDetails.ifscCode}</Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              State <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">{financialDetails.state}</Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Branch <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.cancelledCheque}
            </Typography>
          </Grid>
        </Grid>
      </Paper>

      <Modal open={open} onClose={handleClose}>
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            bgcolor: "background.paper",
            boxShadow: 24,
            p: 4,
            width: "50vw",
            borderRadius: "10px",
          }}
        >
          <FinancialPopup onClose={handleClose} />{" "}
          {/* Load Financial.jsx inside modal */}
        </Box>
      </Modal>
    </Box>
  );
}

export default RegistrationCertification;
