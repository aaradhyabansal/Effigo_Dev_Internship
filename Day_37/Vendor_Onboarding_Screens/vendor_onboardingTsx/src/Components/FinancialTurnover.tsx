import React from "react";
import {
  Box,
  Typography,
  Table,
  TableBody,
  TableRow,
  TableCell,
  Button,
  Paper,
  TableContainer,
  Modal,
} from "@mui/material";
import EditFinancialTurnover from "./EditFinancialTurnover";
import { useState } from "react";

const turnoverData = [
  { year: "2020-2021", amount: "NA" },
  { year: "2021-2022", amount: "NA" },
  { year: "2022-2023", amount: "NA" },
  { year: "2023-2024", amount: "NA" },
  { year: "2025-2026", amount: "NA" },
];

const FinancialTurnover = () => {
  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  return (
    <Box sx={{ width: "100%", maxWidth: "1200px", margin: "0 auto" }}>
      <Paper sx={{ p: 2, bgcolor: "#f5f5f5", borderRadius: 0 }}>
        {/* Heading */}
        <Box
          sx={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            mb: 2,
          }}
        >
          <Typography variant="h6" sx={{ color: "#2196f3", fontWeight: 500 }}>
            Annual Turnover (Past 3 years){" "}
            <span style={{ color: "red" }}>*</span>
          </Typography>
          <Button
            variant="contained"
            sx={{
              bgcolor: "#2196f3",
              "&:hover": { bgcolor: "#1976d2" },
            }}
            onClick={handleOpen}
          >
            + Add / Edit
          </Button>
        </Box>
      </Paper>
      <Paper sx={{ p: 2, mb: 3, bgcolor: "#ffffff", borderRadius: 0 }}>
        {/* Table */}
        <TableContainer component={Paper} sx={{ mb: 2 }}>
          <Table>
            <TableBody>
              <TableRow sx={{ bgcolor: "#ffffff" }}>
                {turnoverData.map((item, index) => (
                  <TableCell
                    key={index}
                    sx={{
                      fontWeight: 600,
                      color: "#000000",
                      textAlign: "center",
                    }}
                  >
                    {item.year}
                  </TableCell>
                ))}
              </TableRow>
              <TableRow>
                {turnoverData.map((item, index) => (
                  <TableCell key={index} sx={{ textAlign: "center" }}>
                    {item.amount}
                  </TableCell>
                ))}
              </TableRow>
            </TableBody>
          </Table>
        </TableContainer>

        {/* Buttons */}
        <Box sx={{ display: "flex", justifyContent: "flex-end", gap: 2 }}>
          <Button
            variant="contained"
            sx={{
              bgcolor: "#2196f3",
              "&:hover": { bgcolor: "#1976d2" },
            }}
          >
            Next
          </Button>
        </Box>
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
          <EditFinancialTurnover onClose={handleClose} />{" "}
          {/* Load Financial.jsx inside modal */}
        </Box>
      </Modal>
    </Box>
  );
};

export default FinancialTurnover;
