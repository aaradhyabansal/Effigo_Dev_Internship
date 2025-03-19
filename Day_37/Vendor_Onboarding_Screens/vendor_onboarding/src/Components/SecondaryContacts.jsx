import { useState } from "react";
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
  IconButton,
  Divider,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Container,
} from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";
import AddIcon from "@mui/icons-material/Add";
import { saveContactDetails } from "../Api/VendorApi";
// Escalation Details Modal component
const EscalationDetailsModal = ({
  open,
  handleClose,
  handleSave,
  initialData,
}) => {
  const [formData, setFormData] = useState(
    initialData || {
      level1: {
        name: " ",
        designation: " ",
        contactNumber: " ",
        emailId: " ",
      },
      level2: {
        name: " ",
        designation: " ",
        contactNumber: " ",
        emailId: " ",
      },
      level3: {
        name: " ",
        designation: " ",
        contactNumber: " ",
        emailId: " ",
      },
    }
  );

  const handleChange = (level, field, value) => {
    setFormData({
      ...formData,
      [level]: {
        ...formData[level],
        [field]: value,
      },
    });
  };

  async function onSave() {
    console.log(formData);
    await saveContactDetails(formData);

    handleClose();
  }

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
          {required && <span style={{ color: "red" }}>*</span>}
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
      sx={{ width: "100%" }}
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

// Main Secondary Contacts Component
const SecondaryContacts = () => {
  const [openModal, setOpenModal] = useState(false);
  const [contactsData, setContactsData] = useState({
    level1: {
      name: " ",
      designation: " ",
      contactNumber: " ",
      emailId: " ",
    },
    level2: {
      name: " ",
      designation: " ",
      contactNumber: " ",
      emailId: " ",
    },
    level3: {
      name: " ",
      designation: " ",
      contactNumber: " ",
      emailId: " ",
    },
  });

  const handleOpenModal = () => {
    setOpenModal(true);
  };

  const handleCloseModal = () => {
    setOpenModal(false);
  };

  const handleSaveContacts = (data) => {
    setContactsData(data);
    console.log("Saved contacts data:", data);
  };

  return (
    <Box sx={{ maxWidth: "1200px", margin: "0", ml: 15 }}>
      <Paper
        elevation={0}
        sx={{ border: "1px solid #e0e0e0", borderRadius: "4px", width: "100%" }}
      >
        {/* Header section with Add/Edit button */}
        <Box
          sx={{
            p: 1.5,
            bgcolor: "#f5f5f5",
            borderBottom: "1px solid #e0e0e0",
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            width: "100%",
          }}
        >
          <Typography
            sx={{ color: "#4a8db7", fontWeight: 500, fontSize: "1.1rem" }}
          >
            Secondary Contacts <span style={{ color: "red" }}>*</span>
          </Typography>
          <Button
            variant="contained"
            startIcon={<AddIcon />}
            onClick={handleOpenModal}
            sx={{
              bgcolor: "#4a8db7",
              "&:hover": { bgcolor: "#3d7a9a" },
              textTransform: "none",
              fontSize: "0.9rem",
            }}
          >
            Add / Edit
          </Button>
        </Box>

        {/* Contacts table */}
        <TableContainer component={Box}>
          <Table>
            <TableHead>
              <TableRow sx={{ bgcolor: "#ffffff", borderStyle: "none" }}>
                <TableCell
                  width="15%"
                  sx={{ fontWeight: 500, color: "#555", borderBottom: "none" }}
                >
                  &nbsp;
                </TableCell>
                <TableCell
                  width="22%"
                  sx={{ fontWeight: 700, color: "#555", borderBottom: "none" }}
                >
                  Name <span style={{ color: "red" }}>*</span>
                </TableCell>
                <TableCell
                  width="22%"
                  sx={{ fontWeight: 700, color: "#555", borderBottom: "none" }}
                >
                  Designation <span style={{ color: "red" }}>*</span>
                </TableCell>
                <TableCell
                  width="20%"
                  sx={{ fontWeight: 700, color: "#555", borderBottom: "none" }}
                >
                  Contact No. <span style={{ color: "red" }}>*</span>
                </TableCell>
                <TableCell
                  width="21%"
                  sx={{ fontWeight: 700, color: "#555", borderBottom: "none" }}
                >
                  Email Id <span style={{ color: "red" }}>*</span>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow>
                <TableCell sx={{ fontWeight: 500, borderBottom: "none" }}>
                  Level 1<span style={{ color: "red" }}>*</span>
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level1.name}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level1.designation}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level1.contactNumber}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level1.emailId}
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell sx={{ fontWeight: 500, borderBottom: "none" }}>
                  Level 2
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level2.name}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level2.designation}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level2.contactNumber}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level2.emailId}
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell sx={{ fontWeight: 500, borderBottom: "none" }}>
                  Level 3
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level3.name}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level3.designation}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level3.contactNumber}
                </TableCell>
                <TableCell sx={{ borderBottom: "none" }}>
                  {contactsData.level3.emailId}
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>
      </Paper>

      <Box mt={2.5} display={"flex"} justifyContent={"flex-end"}>
        <Button variant="contained" sx={{ bgcolor: "#228cab" }}>
          Next
        </Button>
      </Box>
      {/* Modal that opens when Add/Edit is clicked */}
      <EscalationDetailsModal
        open={openModal}
        handleClose={handleCloseModal}
        handleSave={handleSaveContacts}
        initialData={contactsData}
      />
    </Box>
  );
};

export default SecondaryContacts;
