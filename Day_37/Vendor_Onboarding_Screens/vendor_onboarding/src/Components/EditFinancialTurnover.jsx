import React, { useState } from "react";
import { Box, Typography, TextField, Button, Paper } from "@mui/material";
import { saveTurnover } from "../Api/VendorApi";

const turnoverFields = {
  turnoverYear1: "2020-2021",
  turnoverYear2: "2021-2022",
  turnoverYear3: "2022-2023",
  turnoverYear4: "2023-2024",
  turnoverYear5: "2024-2025",
};

const EditFinancialTurnover = () => {
  const [turnoverValues, setTurnoverValues] = useState(
    Object.keys(turnoverFields).reduce((acc, key) => ({ ...acc, [key]: 0 }), {})
  );

  const handleChange = (key, value) => {
    setTurnoverValues((prev) => ({
      ...prev,
      [key]: value === "" ? 0 : parseInt(value, 10) || 0,
    }));
  };

  async function handleSubmit() {
    await saveTurnover(turnoverValues);
    console.log(turnoverValues); // Values will be numbers
  }

  return (
    <Box sx={{ width: "100%", maxWidth: "600px", margin: "0 auto" }}>
      <Paper sx={{ p: 3 }}>
        <Typography
          variant="h6"
          sx={{ color: "#0077b6", fontWeight: 500, mb: 2 }}
        >
          Add/Edit Annual Turnover (Past 5 years)
        </Typography>

        {Object.entries(turnoverFields).map(([key, year]) => (
          <Box key={key} sx={{ mb: 3 }}>
            <Typography variant="h6" sx={{ color: "#0077b6", fontWeight: 600 }}>
              {year}
            </Typography>
            <Typography variant="body2" sx={{ fontWeight: 500, mb: 1 }}>
              Turn Over <span style={{ color: "red" }}>*</span>
            </Typography>
            <TextField
              fullWidth
              variant="outlined"
              type="number"
              value={turnoverValues[key] || ""}
              onChange={(e) => handleChange(key, e.target.value)}
            />
          </Box>
        ))}

        <Box
          sx={{ display: "flex", justifyContent: "flex-end", gap: 2, mt: 2 }}
        >
          <Button
            onClick={handleSubmit}
            variant="contained"
            sx={{ bgcolor: "#2196f3", "&:hover": { bgcolor: "#1976d2" } }}
          >
            Save
          </Button>
        </Box>
      </Paper>
    </Box>
  );
};

export default EditFinancialTurnover;
