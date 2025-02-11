import { useState, useEffect } from "react";
import {
  TextField,
  Button,
  Typography,
  Box,
  Container,
  CircularProgress,
  FormControl,
  FormLabel,
  RadioGroup,
  FormControlLabel,
  Radio,
} from "@mui/material";
import {
  addNewDish,
  updateDish,
  updatePatchDish,
  getDishById,
} from "../api/FoodApi";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";

const AddNewDish = () => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");
  const [restaurantId, setRestaurantId] = useState(0);
  const [originalDish, setOriginalDish] = useState({});
  const [usePut, setUsePut] = useState(false);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const fetchDishDetails = async () => {
      if (id !== "-1") {
        setLoading(true);
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
          alert("Failed to load dish details. Please try again.");
        } finally {
          setLoading(false);
        }
      }
    };

    fetchDishDetails();
  }, [id]);

  async function handleOnClick() {
    try {
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
        usePut
          ? await updateDish(dish, id)
          : await updatePatchDish(updatedFields, id);
      }

      navigate("/dishes");
    } catch (error) {
      console.error("Error saving dish:", error);
      alert("Failed to save dish. Please try again.");
    }
  }

  return (
    <PastelContainer>
      <StyledBox>
        <Typography variant="h5" align="center" mb={3}>
          {id === "-1" ? "Add New Dish" : "Edit Dish"}
        </Typography>

        {loading ? (
          <Box
            display="flex"
            justifyContent="center"
            alignItems="center"
            height="200px"
          >
            <CircularProgress />
          </Box>
        ) : (
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
              onChange={(e) =>
                setRestaurantId(Math.max(0, Number(e.target.value)))
              }
              required
            />

            <Button
              type="button"
              fullWidth
              variant="contained"
              sx={{ mt: 3, bgcolor: "#a8dadc", color: "#1d3557" }}
              onClick={handleOnClick}
            >
              Submit
            </Button>

            {/* Toggle for PUT vs PATCH */}
            {id !== "-1" && (
              <FormControl component="fieldset" sx={{ mt: 3 }}>
                <FormLabel component="legend">Update Type</FormLabel>
                <RadioGroup
                  value={usePut}
                  onChange={(e) => setUsePut(e.target.value === "true")}
                >
                  <FormControlLabel
                    value={true}
                    control={<Radio />}
                    label="Full Update (PUT)"
                  />
                  <FormControlLabel
                    value={false}
                    control={<Radio />}
                    label="Partial Update (PATCH)"
                  />
                </RadioGroup>
              </FormControl>
            )}
          </form>
        )}
      </StyledBox>
    </PastelContainer>
  );
};

// 🎨 Styled Components for Pastel Theme
const PastelContainer = styled(Container)`
  background: linear-gradient(135deg, #fbc2eb 0%, #a6c1ee 100%);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #1d3557;
`;

const StyledBox = styled(Box)`
  padding: 24px;
  border-radius: 10px;
  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
  background-color: #ffffff;
  max-width: 500px;
  width: 100%;
`;

export default AddNewDish;
