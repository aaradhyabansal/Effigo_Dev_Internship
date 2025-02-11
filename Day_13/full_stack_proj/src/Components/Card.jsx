import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { deleteRestaurantById } from "../api/FoodApi";

const Card = ({ name, id }) => {
  const navigate = useNavigate();

  function handleCardClick(id) {
    navigate(`/restaurants/${id}`);
  }

  function handleCardUpdate(id) {
    navigate(`/restaurants/addrestaurant/${id}`);
  }

  async function handleCardDelete(id) {
    await deleteRestaurantById(id);
  }

  return (
    <StyledWrapper>
      <CardContainer onClick={() => handleCardClick(id)}>
        <CardTitle>{name}</CardTitle>
      </CardContainer>
      <ButtonGroup>
        <ActionButton onClick={() => handleCardClick(id)}>View</ActionButton>
        <ActionButton onClick={() => handleCardUpdate(id)}>Edit</ActionButton>
        <DeleteButton onClick={() => handleCardDelete(id)}>Delete</DeleteButton>
      </ButtonGroup>
    </StyledWrapper>
  );
};

// 🌟 Main Wrapper for Layout
const StyledWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
`;

// 🌟 Themed Card Container with Light Shade
const CardContainer = styled.div`
  position: relative;
  width: 220px;
  height: 260px;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  border-radius: 12px;
  padding: 20px;
  cursor: pointer;
  background: linear-gradient(
    135deg,
    rgba(255, 255, 255, 0.25),
    rgba(230, 230, 230, 0.7)
  );
  backdrop-filter: blur(10px);
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;

  &:hover {
    transform: scale(1.05);
    box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.2);
  }
`;

// 🌟 Text inside the card (with contrast)
const CardTitle = styled.p`
  font-size: 22px;
  font-weight: bold;
  color: #2c2c2c;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.15);
`;

// 🌟 Button Group for Actions
const ButtonGroup = styled.div`
  display: flex;
  gap: 10px;
`;

// 🌟 Themed Buttons
const ActionButton = styled.button`
  padding: 8px 14px;
  font-size: 14px;
  font-weight: bold;
  border: none;
  border-radius: 6px;
  color: white;
  background: linear-gradient(-45deg, #ff9a9e, #fad0c4);
  cursor: pointer;
  transition: transform 0.2s, background 0.3s ease-in-out;

  &:hover {
    transform: scale(1.05);
    background: linear-gradient(-45deg, #fad0c4, #ff9a9e);
  }
`;

// 🌟 Delete Button (Slightly Different for Warning)
const DeleteButton = styled(ActionButton)`
  background: linear-gradient(-45deg, #ff4b4b, #ff7979);

  &:hover {
    background: linear-gradient(-45deg, #ff7979, #ff4b4b);
  }
`;

export default Card;
