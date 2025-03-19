import React, { useState } from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Typography,
  Grid,
  Button,
  Box,
  IconButton,
  Divider,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";

const EscalationDetailsModal = ({ open, handleClose, handleSave }) => {
  const [formData, setFormData] = useState({
    level1: {
      name: "",
      designation: "",
      contactNumber: "",
      emailId: "",
    },
    level2: {
      name: "",
      designation: "",
      contactNumber: "",
      emailId: "",
    },
    level3: {
      name: "",
      designation: "",
      contactNumber: "",
      emailId: "",
    },
  });

  const handleChange = (level, field, value) => {
    setFormData({
      ...formData,
      [level]: {
        ...formData[level],
        [field]: value,
      },
    });
  };

  const onSave = () => {
    handleSave(formData);
    handleClose();
  };

  // Render a row of input fields for each level
  const renderLevelFields = (level, levelNumber, required = false) => {
    return (
      <Box sx={{ mb: 3 }}>
        <Typography
          variant="subtitle1"
          sx={{
            fontWeight: 500,
            mb: 1.5,
            color: "#4c5c68",
          }}
        >
          Level {levelNumber}
        </Typography>

        <Grid container spacing={2}>
          <Grid item xs={12} sm={3}>
            <Typography variant="body2" sx={{ mb: 0.5 }}>
              Name {required && <span style={{ color: "red" }}>*</span>}
            </Typography>
            <TextField
              fullWidth
              size="small"
              value={formData[level].name}
              onChange={(e) => handleChange(level, "name", e.target.value)}
              required={required}
              sx={{ bgcolor: "#ffffff" }}
            />
          </Grid>
          <Grid item xs={12} sm={3}>
            <Typography variant="body2" sx={{ mb: 0.5 }}>
              Designation {required && <span style={{ color: "red" }}>*</span>}
            </Typography>
            <TextField
              fullWidth
              size="small"
              value={formData[level].designation}
              onChange={(e) =>
                handleChange(level, "designation", e.target.value)
              }
              required={required}
              sx={{ bgcolor: "#ffffff" }}
            />
          </Grid>
          <Grid item xs={12} sm={3}>
            <Typography variant="body2" sx={{ mb: 0.5 }}>
              Contact Number{" "}
              {required && <span style={{ color: "red" }}>*</span>}
            </Typography>
            <TextField
              fullWidth
              size="small"
              value={formData[level].contactNumber}
              onChange={(e) =>
                handleChange(level, "contactNumber", e.target.value)
              }
              required={required}
              sx={{ bgcolor: "#ffffff" }}
            />
          </Grid>
          <Grid item xs={12} sm={3}>
            <Typography variant="body2" sx={{ mb: 0.5 }}>
              Email ID {required && <span style={{ color: "red" }}>*</span>}
            </Typography>
            <TextField
              fullWidth
              size="small"
              value={formData[level].emailId}
              onChange={(e) => handleChange(level, "emailId", e.target.value)}
              required={required}
              sx={{ bgcolor: "#ffffff" }}
            />
          </Grid>
        </Grid>
      </Box>
    );
  };

  return (
    <Dialog
      open={open}
      onClose={handleClose}
      maxWidth="lg"
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
          bgcolor: "#f7f9fa",
          color: "#4a8db7",
          fontWeight: 500,
          fontSize: "1.25rem",
          p: 2,
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        Add/Edit Escalation Details
        <IconButton
          onClick={handleClose}
          size="small"
          aria-label="close"
          sx={{ color: "#999" }}
        >
          <CloseIcon />
        </IconButton>
      </DialogTitle>

      <Divider />

      <DialogContent sx={{ p: 3, bgcolor: "#f7f9fa" }}>
        {renderLevelFields("level1", 1, true)}
        {renderLevelFields("level2", 2)}
        {renderLevelFields("level3", 3)}
      </DialogContent>

      <Divider />

      <DialogActions
        sx={{ p: 2, justifyContent: "flex-end", bgcolor: "#f7f9fa" }}
      >
        <Button
          variant="contained"
          color="inherit"
          onClick={handleClose}
          sx={{
            bgcolor: "#6c757d",
            color: "white",
            "&:hover": { bgcolor: "#5a6268" },
            textTransform: "none",
            mr: 1,
          }}
        >
          Close
        </Button>
        <Button
          variant="contained"
          onClick={onSave}
          sx={{
            bgcolor: "#4a8db7",
            color: "white",
            "&:hover": { bgcolor: "#3d7a9a" },
            textTransform: "none",
          }}
        >
          Save
        </Button>
      </DialogActions>
    </Dialog>
  );
};

// Example usage
const EscalationDetailsComponent = () => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleSave = (data) => {
    console.log("Saved escalation details:", data);
    // Process the submitted data
  };

  return (
    <div>
      <Button variant="contained" onClick={handleOpen}>
        Open Escalation Details
      </Button>
      <EscalationDetailsModal
        open={open}
        handleClose={handleClose}
        handleSave={handleSave}
      />
    </div>
  );
};

export default EscalationDetailsComponent;
