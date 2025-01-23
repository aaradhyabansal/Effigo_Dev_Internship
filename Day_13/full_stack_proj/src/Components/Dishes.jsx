import React, { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  Container,
  Button,
  Box,
} from "@mui/material";
import { getAllDishes, deleteDishById } from "../api/FoodApi"; // Assuming deleteDishById is defined in FoodApi
import { useNavigate } from "react-router-dom";
import styled from "styled-components"; // Import styled-components
import "../css/Dishes.css"; // Import the custom CSS file

const Dishes = () => {
  const [dishes, setDishes] = useState([]);
  const navigate = useNavigate();

  // Fetch all dishes
  useEffect(() => {
    const fetchDishes = async () => {
      try {
        const response = await getAllDishes(); // Using the correct API function
        if (response.data) {
          setDishes(response.data);
        }
      } catch (error) {
        console.error("Error fetching dishes:", error);
      }
    };

    fetchDishes();
  }, []);

  // Handle delete action
  const handleDelete = async (id) => {
    try {
      await deleteDishById(id); // Assuming deleteDishById is implemented in FoodApi
      setDishes(dishes.filter((dish) => dish.id !== id)); // Update the state after deletion
    } catch (error) {
      console.error("Error deleting dish:", error);
    }
  };

  // Handle update action
  const handleUpdate = (id) => {
    navigate(`/dishes/addnewdish/${id}`); // Navigate to the update dish page
  };

  return (
    <div className="dishes-container">
      <StyledContainer>
        <Typography variant="h4" align="center" gutterBottom>
          All Dishes
        </Typography>
        <TableContainer
          component={Paper}
          style={{ background: "rgba(255, 255, 255, 0.8)" }}
        >
          <Table aria-label="dishes table">
            <TableHead>
              <TableRow>
                <TableCell>ID</TableCell>
                <TableCell>Description</TableCell>
                <TableCell>Name</TableCell>
                <TableCell>Price</TableCell>
                <TableCell>Restaurant ID</TableCell>
                <TableCell>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {dishes.length > 0 ? (
                dishes.map((dish) => (
                  <TableRow key={dish.id}>
                    <TableCell>{dish.id}</TableCell>
                    <TableCell>{dish.description}</TableCell>
                    <TableCell>{dish.name}</TableCell>
                    <TableCell>{dish.price}</TableCell>
                    <TableCell>{dish.restaurant_id}</TableCell>
                    <TableCell>
                      <Box display="flex" gap={1}>
                        <Button
                          variant="contained"
                          color="primary"
                          size="small"
                          onClick={() => handleUpdate(dish.id)}
                        >
                          Update
                        </Button>
                        <Button
                          variant="contained"
                          color="secondary"
                          size="small"
                          onClick={() => handleDelete(dish.id)}
                        >
                          Delete
                        </Button>
                      </Box>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={6} align="center">
                    Loading or no data available
                  </TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </TableContainer>
      </StyledContainer>
    </div>
  );
};

const StyledContainer = styled(Container)`
  background: linear-gradient(-45deg, #00dbde, #fc00ff);
  color: white;
  padding: 16px;
`;

export default Dishes;
