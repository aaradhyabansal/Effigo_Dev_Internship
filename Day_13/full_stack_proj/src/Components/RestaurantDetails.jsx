import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRestaurantById } from "../api/FoodApi";
import DishCard from "./DishCard";

const RestaurantDetails = () => {
  const { id } = useParams();
  const [restaurant, setRestaurant] = useState([]);

  useEffect(() => {
    async function fetchRestaurant() {
      try {
        const response = await getRestaurantById(id);
        // console.log(response.data);
        setRestaurant(response.data);
        console.log(restaurant);
      } catch (error) {
        console.error("Error fetching restaurant details", error);
      }
    }

    fetchRestaurant();
  }, [id]);

  if (!restaurant) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <div className="restaurant-list">
        {restaurant && restaurant.length > 0 ? (
          restaurant.map((dish) => (
            <DishCard
              key={dish.id}
              name={dish.name}
              description={dish.description}
              price={dish.price}
            />
          ))
        ) : (
          <p>No dishes available</p>
        )}
      </div>
    </div>
  );
};

export default RestaurantDetails;
