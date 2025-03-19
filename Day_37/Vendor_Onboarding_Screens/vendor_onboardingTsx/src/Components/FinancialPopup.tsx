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
import { savefinancial } from "../Api/VendorApi";

interface FinancialDetailsModalProps {
  open: boolean;
  handleClose: () => void;
  handleUpdate: (data: any) => void;
}

const FinancialDetailsModal: React.FC<FinancialDetailsModalProps> = ({
  open,
  handleClose,
  handleUpdate,
}) => {
  interface FormData {
    accountNo: string;
    beneficiaryName: string;
    bankName: string;
    branch: string;
    ifscCode: string;
    state: string;
    currency: string;
    cancelledCheque: File | null;
  }

  const [formData, setFormData] = useState<FormData>({
    accountNo: "",
    beneficiaryName: "",
    bankName: "",
    branch: "",
    ifscCode: "",
    state: "",
    currency: "",
    cancelledCheque: null,
  });

  const handleChange = (
    e: React.ChangeEvent<{ name?: string; value: unknown }>
  ) => {
    const { name, value } = e.target;
    if (name) {
      setFormData((prev) => ({
        ...prev,
        [name]: value as string,
      }));
    }
  };

  const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0] || null;
    setFormData((prev) => ({
      ...prev,
      cancelledCheque: file,
    }));
  };

  const handleSubmit = async () => {
    console.log("save financial details");
    await savefinancial(formData);
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
              >
                <MenuItem value="">
                  <em>Select</em>
                </MenuItem>
                <MenuItem value="Karnataka">Karnataka</MenuItem>
                <MenuItem value="Maharashtra">Maharashtra</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item xs={12}>
            <Typography variant="subtitle2" sx={{ mb: 1 }}>
              Cancelled Cheque <span style={{ color: "red" }}>*</span>
            </Typography>
            <Box sx={{ display: "flex", alignItems: "center", gap: 2, mb: 2 }}>
              <Button variant="contained" component="label">
                Upload
                <input type="file" hidden onChange={handleFileUpload} />
              </Button>
            </Box>
          </Grid>
        </Grid>
      </DialogContent>

      <Divider />

      <DialogActions sx={{ p: 2, justifyContent: "flex-end" }}>
        <Button variant="contained" onClick={handleClose}>
          Close
        </Button>
        <Button
          variant="contained"
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

const FinancialPopup: React.FC = () => {
  const [open, setOpen] = useState<boolean>(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleUpdate = (data: any) => {
    console.log("Updated financial details:", data);
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
};

export default FinancialPopup;
