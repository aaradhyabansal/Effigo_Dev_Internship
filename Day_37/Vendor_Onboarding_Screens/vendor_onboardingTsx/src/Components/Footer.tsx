import React from "react";
import { Box, Typography } from "@mui/material";

const Footer = () => {
  return (
    <Box
      sx={{
        height: 40,
        backgroundColor: "#fff",
        position: "fixed",
        bottom: 0,
        left: 0,
        width: "100%",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        padding: "0 16px",
        boxShadow: "0px -2px 4px rgba(0,0,0,0.1)",
      }}
    >
      <Typography variant="body2" sx={{ margin: "auto", fontWeight: 500 }}>
        Copyright @ Bob eProcure
      </Typography>

      <Box
        component="img"
        src="src/assets/images/bob-procure-logo.png"
        alt="Company Logo"
        sx={{ height: 24, marginRight: 10 }}
      />
    </Box>
  );
};

export default Footer;
