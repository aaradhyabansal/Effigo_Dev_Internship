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

// Define props type
interface BusinessDetailsModalProps {
  open: boolean;
  handleClose: () => void;
}

// Define form data type
interface FormData {
  parentOrganizationName: string;
  parentCompanyType: string;
  orderCurrency: string;
  msmeRegistration: string;
  esiNo: string;
  pfNo: string;
}

const BusinessDetailsModal: React.FC<BusinessDetailsModalProps> = ({ open, handleClose }) => {
  // State for form fields
  const [formData, setFormData] = useState<FormData>({
    parentOrganizationName: "N/A",
    parentCompanyType: "N/A",
    orderCurrency: "N/A",
    msmeRegistration: "N/A",
    esiNo: "N/A",
    pfNo: "N/A",
  });

  // Handle input changes
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    console.log("Saving business details...");
    await saveBusinessDetails(formData);
  };

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
        Add / Edit Financial Details
        <IconButton sx={{ color: "#999" }} onClick={handleClose}>
          <CloseIcon />
        </IconButton>
      </DialogTitle>

      <DialogContent sx={{ p: 3 }}>
        <Grid container spacing={3}>
          {/* First row */}
          {[
            { label: "Parent Organization Name", name: "parentOrganizationName" },
            { label: "Parent Company Type", name: "parentCompanyType" },
            { label: "Order Currency", name: "orderCurrency" },
          ].map((field) => (
            <Grid item xs={12} sm={6} md={4} key={field.name}>
              <Typography variant="subtitle2" sx={{ mb: 1 }}>
                {field.label} <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                fullWidth
                name={field.name}
                value={formData[field.name as keyof FormData]}
                onChange={handleChange}
                variant="outlined"
                size="small"
              />
            </Grid>
          ))}

          {/* Second row */}
          {[
            { label: "MSME Registration", name: "msmeRegistration" },
            { label: "ESI No.", name: "esiNo" },
            { label: "PF No.", name: "pfNo" },
          ].map((field) => (
            <Grid item xs={12} sm={6} md={4} key={field.name}>
              <Typography variant="subtitle2" sx={{ mb: 1 }}>
                {field.label} <span style={{ color: "red" }}>*</span>
              </Typography>
              <TextField
                fullWidth
                name={field.name}
                value={formData[field.name as keyof FormData]}
                onChange={handleChange}
                variant="outlined"
                size="small"
              />
            </Grid>
          ))}
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

export default BusinessDetailsModal;
