import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import { deleteRestaurantById } from "../api/FoodApi";
import "../css/Card.css";

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
      <div className="card">
        <p className="heading">{name}</p>
      </div>
      <div className="d-flex">
        <button onClick={() => handleCardClick(id)}>Click</button>
        <button onClick={() => handleCardUpdate(id)}>Update</button>
        <button onClick={() => handleCardDelete(id)}>Delete</button>
      </div>
    </StyledWrapper>
  );
};

const StyledWrapper = styled.div`
  .card {
    position: relative;
    width: 190px;
    height: 254px;
    background-color: #000;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 12px;
    gap: 12px;
    border-radius: 8px;
    cursor: pointer;
  }

  .card::before {
    content: "";
    position: absolute;
    inset: 0;
    left: -5px;
    margin: auto;
    width: 200px;
    height: 264px;
    border-radius: 10px;
    background: linear-gradient(-45deg, #e81cff 0%, #40c9ff 100%);
    z-index: -10;
    pointer-events: none;
    transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  }

  .card::after {
    content: "";
    z-index: -1;
    position: absolute;
    inset: 0;
    background: linear-gradient(-45deg, #fc00ff 0%, #00dbde 100%);
    transform: translate3d(0, 0, 0) scale(0.95);
    filter: blur(20px);
  }

  .heading {
    font-size: 20px;
    text-transform: capitalize;
    font-weight: 700;
    color: white;
    text-align: center;
  }

  .card:hover::after {
    filter: blur(30px);
  }

  .card:hover::before {
    transform: rotate(-90deg) scaleX(1.34) scaleY(0.77);
  }

  button {
    display: inline-block;
    margin-top: 10px;
    padding: 10px 20px;
    background: linear-gradient(-45deg, #e81cff, #40c9ff);
    color: white;
    border: none;
    border-radius: 5px;
    text-align: center;
    cursor: pointer;
    transition: transform 0.2s, background 0.2s ease-in-out;
  }

  button:hover {
    transform: scale(1.05);
    background: linear-gradient(-45deg, #fc00ff, #00dbde);
  }
`;

export default Card;
