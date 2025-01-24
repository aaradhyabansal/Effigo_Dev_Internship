import * as React from "react";
import {
  AppBar,
  Toolbar,
  Typography,
  Container,
  Button,
  Box,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

const LandingPage = ({ toggleTheme, isDarkTheme }) => {
  const navigate = useNavigate();

  const buttonStyle = {
    padding: "8px 16px",
    backgroundColor: isDarkTheme ? "#ffffff" : "#121212",
    color: isDarkTheme ? "#121212" : "#ffffff",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
    margin: "16px",
    transition: "all 0.3s ease",
    background: "linear-gradient(-45deg, #00dbde, #fc00ff)",
  };

  const handleNavigate = (path) => {
    navigate(path);
  };

  return (
    <div className="restaurant-list">
      <Container sx={{ marginTop: 8, textAlign: "center" }}>
        <Typography variant="h3" gutterBottom>
          Welcome to Zwigato
        </Typography>
        <Typography variant="h6" paragraph>
          Discover a variety of dishes from different restaurants. Get the best
          dining experience right at your fingertips!
        </Typography>

        <Typography variant="body1" sx={{ mt: 4 }}>
          Whether you're craving Italian, Chinese, Indian, or local cuisine,
          Zwigato has it all. Find your favorite dishes and explore new flavors!
        </Typography>

        <Button
          sx={{
            padding: "8px 16px",
            backgroundColor: isDarkTheme ? "#ffffff" : "#121212",
            color: isDarkTheme ? "#121212" : "#ffffff",
            border: "none",
            borderRadius: "4px",
            cursor: "pointer",
            transition: "all 0.3s ease",
            background: "linear-gradient(-45deg, #00dbde, #fc00ff)",
            mt: 3,
          }}
          onClick={() => handleNavigate("/restaurants")}
        >
          Explore More Restaurants
        </Button>
      </Container>
    </div>
  );
};

export default LandingPage;
