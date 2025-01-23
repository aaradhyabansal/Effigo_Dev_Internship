import React from "react";
import styled from "styled-components";

const DishCard = ({ name, description, price }) => {
  return (
    <StyledCard>
      <h3>{name}</h3>
      <p>{description || "No description available"}</p>
      <p className="price">Price: ${price}</p>
    </StyledCard>
  );
};

const StyledCard = styled.div`
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 16px;
  background: linear-gradient(-45deg, #00dbde, #fc00ff);
  color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin: 16px;
  max-width: 300px;
  height: fit-content;
  text-align: center;
  transition: transform 0.3s ease-in-out;

  h3 {
    font-size: 18px;
    margin-bottom: 8px;
  }

  p {
    font-size: 14px;
    margin: 8px 0;
  }

  .price {
    font-weight: bold;
    color: rgb(231, 238, 231);
  }

  &:hover {
    transform: scale(1.05);
  }
`;

export default DishCard;
