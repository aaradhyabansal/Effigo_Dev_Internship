import { useEffect, useState } from "react";
import { getAllRestaurants } from "../api/FoodApi";
import Card from "./Card";
import "../css/RestaurantList.css";

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
    <div className="restaurant-list">
      {restaurants.map((restaurant) => (
        <Card key={restaurant.id} name={restaurant.name} id={restaurant.id} />
      ))}
    </div>
  );
};

export default RestaurantList;
