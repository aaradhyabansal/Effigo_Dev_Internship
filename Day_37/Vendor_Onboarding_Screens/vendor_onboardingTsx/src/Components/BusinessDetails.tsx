import { useState } from "react";
import { Box, Button, Typography, Paper, Grid, Modal } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import BusinessDetailsModal from "./BusinessDetailsModal";

function BusinessDetails() {
  const [financialDetails] = useState({
    parentOrganizationName: "N/A",
    parentCompanyType: "N/A",
    orderCurrency: "N/A",
    msmeRegistration: "N/A",
    esiNo: "N/A",
    pfNo: "N/A",
  });

  const [open, setOpen] = useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <Box sx={{ width: "100%", maxWidth: "1200px", margin: "0 auto" }}>
      <Paper
        sx={{
          p: 2,
          bgcolor: "#f5f5f5",
          borderRadius: 0,
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            mb: 0,
          }}
        >
          <Typography variant="h6" sx={{ color: "#228cab", fontWeight: 500 }}>
            Business Details <span style={{ color: "red" }}>*</span>
          </Typography>
          <Box>
            <Button
              variant="contained"
              startIcon={<AddIcon />}
              sx={{
                mr: 1,
                bgcolor: "#228cab",
                "&:hover": { bgcolor: "#1976d2" },
              }}
              onClick={handleOpen}
            >
              Add / Edit
            </Button>
            <Button variant="contained" sx={{ bgcolor: "#228cab" }}>
              Close
            </Button>
          </Box>
        </Box>
      </Paper>
      <Paper sx={{ p: 2, mb: 3, bgcolor: "#ffffff", borderRadius: 0 }}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Parent Organization Name (If applicable){" "}
              <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.parentOrganizationName}
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Parent Company Type (If applicable){" "}
              <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.parentCompanyType}
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Order Currency<span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.orderCurrency}
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              MSME Registration <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">
              {financialDetails.msmeRegistration}
            </Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              ESI No.<span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">{financialDetails.esiNo}</Typography>
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              PF No. <span style={{ color: "red" }}>*</span>
            </Typography>
            <Typography variant="body1">{financialDetails.pfNo}</Typography>
          </Grid>
        </Grid>
      </Paper>

      {/* Fixed Modal Implementation */}
      <Modal open={open} onClose={handleClose}>
        <BusinessDetailsModal handleClose={handleClose} open={open} />
      </Modal>
    </Box>
  );
}

export default BusinessDetails;
