import { useState, useEffect } from "react";
import { TextField, Button, Typography, Box, Container } from "@mui/material";
import {
  addNewDish,
  updateDish,
  updatePatchDish,
  getDishById,
} from "../api/FoodApi";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";
import "../css/AddNewDish.css";

const AddNewDish = () => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");
  const [restaurantId, setRestaurantId] = useState(0);
  const [originalDish, setOriginalDish] = useState({});
  const [usePut, setUsePut] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const fetchDishDetails = async () => {
      if (id !== "-1") {
        try {
          const response = await getDishById(id);
          const dish = response.data;
          setName(dish.name);
          setPrice(dish.price);
          setDescription(dish.description);
          setRestaurantId(dish.restaurant_id);
          setOriginalDish(dish);
        } catch (error) {
          console.error("Error fetching dish details:", error);
        }
      }
    };

    fetchDishDetails();
  }, [id]);

  async function handleOnClick() {
    const updatedFields = {};

    if (name !== originalDish.name) updatedFields.name = name;
    if (price !== originalDish.price) updatedFields.price = price;
    if (description !== originalDish.description)
      updatedFields.description = description;
    if (restaurantId !== originalDish.restaurant_id)
      updatedFields.restaurant_id = restaurantId;

    const dish = { name, description, price, restaurant_id: restaurantId };

    if (id === "-1") {
      await addNewDish(dish, restaurantId);
    } else {
      if (usePut) {
        await updateDish(dish, id);
      } else {
        await updatePatchDish(updatedFields, id);
      }
    }

    navigate("/dishes");
  }

  return (
    <div className="addnewdish-container">
      <GradientContainer>
        <div className="addnewdish-container">
          <StyledBox>
            <Typography variant="h5" align="center" mb={3}>
              {id === "-1" ? "Add New Dish" : "Edit Dish"}
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
                onChange={(e) => setPrice(Number(e.target.value))}
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
                onChange={(e) => setRestaurantId(Number(e.target.value))}
                required
              />

              <Button
                type="button"
                fullWidth
                variant="contained"
                color="primary"
                sx={{ mt: 3 }}
                onClick={handleOnClick}
              >
                Submit
              </Button>

              {/* Toggle for PUT vs PATCH */}
              {id !== "-1" && (
                <div>
                  <label>
                    <input
                      type="radio"
                      name="updateType"
                      checked={usePut}
                      onChange={() => setUsePut(true)}
                    />
                    Full Update (PUT)
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="updateType"
                      checked={!usePut}
                      onChange={() => setUsePut(false)}
                    />
                    Partial Update (PATCH)
                  </label>
                </div>
              )}
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
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
`;

export default AddNewDish;
