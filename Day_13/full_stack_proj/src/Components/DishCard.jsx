import React from "react";
import styled from "styled-components";

const DishCard = ({ name, description, price }) => {
  return (
    <StyledCard>
      <h3>{name}</h3>
      <p className="description">{description || "No description available"}</p>
      <p className="price">Price: ${price.toFixed(2)}</p>
    </StyledCard>
  );
};

const StyledCard = styled.div`
  border-radius: 12px;
  padding: 16px;
  background: linear-gradient(135deg, #fbc2eb, #a6c1ee);
  color: #1d3557;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  margin: 12px;
  width: 250px; /* Keeps all cards uniform */
  min-height: 180px;
  max-height: 250px; /* Prevents super tall cards */
  text-align: center;
  transition: all 0.3s ease-in-out;
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* Ensures content is evenly spaced */
  overflow: hidden;

  h3 {
    font-size: 18px;
    font-weight: bold;
    color: #3a3d98;
  }

  .description {
    font-size: 14px;
    color: #444;
    display: -webkit-box;
    -webkit-line-clamp: 3; /* Limits text to 3 lines */
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-overflow: ellipsis;
    margin: 8px 0;
  }

  .price {
    font-weight: bold;
    color: #2a9d8f;
    font-size: 16px;
  }

  &:hover {
    transform: scale(1.05);
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
  }
`;

export default DishCard;
