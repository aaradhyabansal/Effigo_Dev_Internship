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
import { getAllDishes, deleteDishById } from "../api/FoodApi";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import "../css/Dishes.css";

const Dishes = () => {
  const [dishes, setDishes] = useState([]);
  const navigate = useNavigate();

  // Fetch all dishes
  useEffect(() => {
    const fetchDishes = async () => {
      try {
        const response = await getAllDishes();
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
      await deleteDishById(id);
      setDishes(dishes.filter((dish) => dish.id !== id));
    } catch (error) {
      console.error("Error deleting dish:", error);
    }
  };

  // Handle update action
  const handleUpdate = (id) => {
    navigate(`/dishes/addnewdish/${id}`);
  };

  return (
    <StyledContainer>
      <Typography variant="h4" align="center" gutterBottom>
        🍽️ All Dishes
      </Typography>
      <TableContainer
        component={Paper}
        sx={{
          background: "rgba(255, 255, 255, 0.9)",
          borderRadius: "12px",
          boxShadow: "0px 4px 8px rgba(0, 0, 0, 0.2)",
        }}
      >
        <Table aria-label="dishes table">
          <TableHead>
            <TableRow
              sx={{ background: "linear-gradient(135deg, #FFD3B6, #D5AAFF)" }}
            >
              <StyledTableCell>ID</StyledTableCell>
              <StyledTableCell>Description</StyledTableCell>
              <StyledTableCell>Name</StyledTableCell>
              <StyledTableCell>Price</StyledTableCell>
              <StyledTableCell>Restaurant ID</StyledTableCell>
              <StyledTableCell>Actions</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {dishes.length > 0 ? (
              dishes.map((dish) => (
                <StyledTableRow key={dish.id}>
                  <TableCell>{dish.id}</TableCell>
                  <TableCell>{dish.description}</TableCell>
                  <TableCell>{dish.name}</TableCell>
                  <TableCell>{dish.price}</TableCell>
                  <TableCell>{dish.restaurant_id}</TableCell>
                  <TableCell>
                    <Box display="flex" gap={1}>
                      <StyledButton onClick={() => handleUpdate(dish.id)}>
                        Update
                      </StyledButton>
                      <StyledDeleteButton onClick={() => handleDelete(dish.id)}>
                        Delete
                      </StyledDeleteButton>
                    </Box>
                  </TableCell>
                </StyledTableRow>
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
  );
};

// ✅ Styled components for pastel look
const StyledContainer = styled(Container)`
  background: linear-gradient(135deg, #ffd3b6, #d5aaff, #85e3ff);
  padding: 20px;
  border-radius: 12px;
  box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.2);
`;

const StyledTableCell = styled(TableCell)`
  font-weight: bold;
  color: #333;
`;

const StyledTableRow = styled(TableRow)`
  &:hover {
    background: rgba(255, 213, 182, 0.4);
  }
`;

const StyledButton = styled(Button)`
  background: linear-gradient(135deg, #85e3ff, #d5aaff);
  color: white;
  font-weight: bold;
  border-radius: 8px;
  padding: 6px 12px;
  &:hover {
    background: linear-gradient(135deg, #d5aaff, #ffaaa5);
  }
`;

const StyledDeleteButton = styled(StyledButton)`
  background: linear-gradient(135deg, #ffaaa5, #ffd3b6);
  &:hover {
    background: linear-gradient(135deg, #ffd3b6, #85e3ff);
  }
`;

export default Dishes;
