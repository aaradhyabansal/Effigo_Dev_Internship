import React, { useState } from "react";
import {
  Box,
  Typography,
  Button,
  Paper,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  IconButton,
  Grid,
  Modal,
} from "@mui/material";
import { CloudUpload, Delete } from "@mui/icons-material";
import RegistrationForm from "./RegistrationComponent";
import CertificationForm from "./CertificationComponent";

const statutoryDocs = [
  "PF",
  "ESI",
  "GST Registration",
  "License to Operate",
  "Bank Undertaking",
  "PAN",
  "CIN Document",
];

const RegistrationSection = () => {
  const [uploadedFiles, setUploadedFiles] = useState({});
  const [registrationData, setRegistrationData] = useState([]);
  const [certificationData, setCertificationData] = useState([]);
  const [open, setOpen] = useState(false);
  const [open1, setOpen1] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const handleOpen1 = () => setOpen1(true);
  const handleClose1 = () => setOpen1(false);
  const handleUpload = (docType) => {
    setUploadedFiles((prev) => ({
      ...prev,
      [docType]: `Uploaded_${docType}.pdf`,
    }));
  };

  const handleDelete = (index, type) => {
    if (type === "registration") {
      setRegistrationData((prev) => prev.filter((_, i) => i !== index));
    } else {
      setCertificationData((prev) => prev.filter((_, i) => i !== index));
    }
  };

  return (
    <Box sx={{ width: "100%", maxWidth: "1100px", margin: "0 auto" }}>
      {/* Statutory Registration Section */}
      <Paper sx={{ p: 4, mb: 3 }}>
        <Typography
          variant="h6"
          sx={{ color: "#0077b6", fontWeight: 600, mb: 3 }}
        >
          Statutory Registration <span style={{ color: "red" }}>*</span>
        </Typography>

        <Grid container spacing={3}>
          {statutoryDocs.map((doc) => (
            <Grid item xs={12} sm={6} key={doc}>
              <Box
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  gap: 1.5,
                }}
              >
                <Typography variant="body1" sx={{ fontWeight: 500 }}>
                  {doc}
                </Typography>
                <Box sx={{ display: "flex", gap: 2 }}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => handleUpload(doc)}
                    sx={{ minWidth: "150px" }}
                  >
                    {uploadedFiles[doc] ? "Uploaded" : "Upload"}
                  </Button>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => handleUpload(doc)}
                    sx={{ minWidth: "150px" }}
                  >
                    Start Upload
                  </Button>
                </Box>
              </Box>
            </Grid>
          ))}
        </Grid>

        <Button
          variant="contained"
          sx={{ bgcolor: "#0288d1", mt: 3, width: "100%" }}
        >
          + Add Other Documents
        </Button>
      </Paper>

      <Typography variant="caption" sx={{ display: "block", mt: -2, mb: 2 }}>
        <b>Note:</b> Please upload .pdf, .PDF, .jpg, .JPG file formats only, max
        size 50MB.
      </Typography>

      {/* Registration Table */}
      <Paper sx={{ p: 4, mb: 3 }}>
        <Typography
          variant="h6"
          sx={{ color: "#0077b6", fontWeight: 600, mb: 3 }}
        >
          Registration
        </Typography>
        <Button
          variant="contained"
          sx={{ bgcolor: "#0288d1", mb: 2 }}
          onClick={handleOpen}
        >
          + Add
        </Button>
        <Table>
          <TableHead>
            <TableRow>
              {[
                "Registration Name",
                "Registered With",
                "Class",
                "Place",
                "Services",
                "Effective Date",
                "Expiry Date",
                "Document",
                "Actions",
              ].map((header) => (
                <TableCell key={header} sx={{ fontWeight: "bold" }}>
                  {header}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {registrationData.length === 0 ? (
              <TableRow>
                <TableCell colSpan={9} align="center">
                  No records found
                </TableCell>
              </TableRow>
            ) : (
              registrationData.map((row, index) => (
                <TableRow key={index}>
                  {Object.values(row).map((val, i) => (
                    <TableCell key={i}>{val}</TableCell>
                  ))}
                  <TableCell>
                    <IconButton
                      color="error"
                      onClick={() => handleDelete(index, "registration")}
                    >
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </Paper>

      <Paper sx={{ p: 4 }}>
        <Typography
          variant="h6"
          sx={{ color: "#0077b6", fontWeight: 600, mb: 3 }}
        >
          Certification Documents (ISO etc)
        </Typography>
        <Button
          variant="contained"
          sx={{ bgcolor: "#0288d1", mb: 2 }}
          onClick={handleOpen1}
        >
          + Add
        </Button>
        <Table>
          <TableHead>
            <TableRow>
              {[
                "Certification Name",
                "Certified By",
                "Certificate No.",
                "Issuer",
                "Place",
                "Effective Date",
                "Expiry Date",
                "Document",
                "Actions",
              ].map((header) => (
                <TableCell key={header} sx={{ fontWeight: "bold" }}>
                  {header}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {certificationData.length === 0 ? (
              <TableRow>
                <TableCell colSpan={9} align="center">
                  No records found
                </TableCell>
              </TableRow>
            ) : (
              certificationData.map((row, index) => (
                <TableRow key={index}>
                  {Object.values(row).map((val, i) => (
                    <TableCell key={i}>{val}</TableCell>
                  ))}
                  <TableCell>
                    <IconButton
                      color="error"
                      onClick={() => handleDelete(index, "certification")}
                    >
                      <Delete />
                    </IconButton>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
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
          <RegistrationForm open={open} handleClose={handleClose} />{" "}
        </Box>
      </Modal>
      <Modal open={open1} onClose={handleClose1}>
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
          <CertificationForm open={open1} handleClose={handleClose1} />{" "}
        </Box>
      </Modal>
    </Box>
  );
};

export default RegistrationSection;
