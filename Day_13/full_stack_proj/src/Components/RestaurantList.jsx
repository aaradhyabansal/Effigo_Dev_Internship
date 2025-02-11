import { useEffect, useState } from "react";
import { getAllRestaurants } from "../api/FoodApi";
import Card from "./Card";
import styled from "styled-components";

const RestaurantList = () => {
  const [restaurants, setRestaurants] = useState([]);

  useEffect(() => {
    async function fetchRestaurants() {
      try {
        const response = await getAllRestaurants();
        setRestaurants(response.data);
      } catch (error) {
        console.error("Error fetching restaurants", error);
      }
    }

    fetchRestaurants();
  }, []);

  return (
    <StyledContainer>
      {restaurants.map((restaurant) => (
        <Card key={restaurant.id} name={restaurant.name} id={restaurant.id} />
      ))}
    </StyledContainer>
  );
};

const StyledContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 16px; /* Ensures spacing */
  padding: 20px;
  max-width: 1200px;
  margin: auto;
`;

export default RestaurantList;
