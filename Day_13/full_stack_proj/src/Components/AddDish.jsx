import { useState, useEffect } from "react";
import { TextField, Button, Typography, Box, Container } from "@mui/material";
import { addNewDish, updateDish, getDishById } from "../api/FoodApi";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";
import "../css/AddNewDish.css";

const AddNewDish = () => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");
  const [restaurantId, setRestaurantId] = useState(0);

  const navigate = useNavigate();

  const { id } = useParams();
  useEffect(() => {
    const fetchDishDetails = async () => {
      if (id !== -1) {
        try {
          const response = await getDishById(id);
          const dish = response.data;
          setName(dish.name);
          setPrice(dish.price);
          setDescription(dish.description);
          setRestaurantId(dish.restaurant_id);
        } catch (error) {
          console.error("Error fetching dish details:", error);
        }
      }
    };

    fetchDishDetails();
  }, [id]);

  async function handleOnClick() {
    const dish = {
      name: name,
      description: description,
      price: price,
      restaurant_id: restaurantId,
    };
    if (id === -1) {
      await addNewDish(dish, restaurantId);
    } else {
      await updateDish(dish, id);
    }
    navigate("/dishes");
  }

  return (
    <div className="addnewdish-container">
      <GradientContainer>
        <div className="addnewdish-container">
          <StyledBox>
            <Typography variant="h5" align="center" mb={3}>
              Add New Dish
            </Typography>

            <form>
              <TextField
                fullWidth
                label="Dish Name"
                variant="outlined"
                margin="normal"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />

              <TextField
                fullWidth
                label="Price"
                variant="outlined"
                margin="normal"
                type="number"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
              />

              <TextField
                fullWidth
                label="Description"
                variant="outlined"
                margin="normal"
                multiline
                rows={4}
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                required
              />

              <TextField
                fullWidth
                label="Restaurant ID"
                variant="outlined"
                margin="normal"
                type="number"
                value={restaurantId}
                onChange={(e) => setRestaurantId(e.target.value)}
                required
              />

              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                sx={{ mt: 3 }}
                onClick={handleOnClick}
              >
                Add Dish
              </Button>
            </form>
          </StyledBox>
        </div>
      </GradientContainer>
    </div>
  );
};

const GradientContainer = styled(Container)`
  background: linear-gradient(-45deg, #00dbde, #fc00ff);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
`;

const StyledBox = styled(Box)`
  padding: 24px; /* Adjust padding for form */
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
`;

export default AddNewDish;
