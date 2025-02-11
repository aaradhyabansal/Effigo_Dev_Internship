import * as React from "react";
import { Container, Typography, Button, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const LandingPage = ({ toggleTheme, isDarkTheme }) => {
  const navigate = useNavigate();

  return (
    <GradientContainer>
      <ContentBox>
        <Title variant="h3">
          Welcome to <span>Zwigato</span>
        </Title>
        <SubText variant="h6">
          Discover a variety of dishes from different restaurants. Get the best
          dining experience right at your fingertips!
        </SubText>

        <BodyText variant="body1">
          Whether you're craving Italian, Chinese, Indian, or local cuisine,
          Zwigato has it all. Find your favorite dishes and explore new flavors!
        </BodyText>

        <StyledButton onClick={() => navigate("/restaurants")}>
          Explore More Restaurants
        </StyledButton>
      </ContentBox>
    </GradientContainer>
  );
};

// 🌟 Background with Balanced Gradient (Less Bright)
const GradientContainer = styled.div`
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(-45deg, #fbc2eb, #a6c1ee);
`;

// 🌟 Glassmorphism Effect
const ContentBox = styled(Box)`
  padding: 40px;
  border-radius: 12px;
  backdrop-filter: blur(10px);
  background: rgba(255, 255, 255, 0.2);
  box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
  text-align: center;
  max-width: 600px;
`;

// 🌟 Title with Better Contrast
const Title = styled(Typography)`
  color: #333;
  font-weight: bold;
  span {
    color: #ff0080;
  }
`;

// 🌟 Subtext with Shadow for Readability
const SubText = styled(Typography)`
  color: #444;
  text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.2);
`;

// 🌟 Body Text (Darker for Readability)
const BodyText = styled(Typography)`
  color: #222;
  margin-top: 16px;
`;

// 🌟 Themed Button
const StyledButton = styled(Button)`
  padding: 10px 20px;
  background: linear-gradient(-45deg, #ff9a9e, #fad0c4);
  color: white;
  border-radius: 8px;
  font-weight: bold;
  margin-top: 20px;
  transition: all 0.3s ease;

  &:hover {
    background: linear-gradient(-45deg, #fad0c4, #ff9a9e);
    transform: scale(1.05);
  }
`;

export default LandingPage;
