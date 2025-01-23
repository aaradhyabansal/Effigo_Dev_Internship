import React, { useState, useEffect } from "react";
import {
  addNewRestaurant,
  getRestaurantById,
  getRestauratnedNameById,
  updateRestaurant,
} from "../api/FoodApi";
import { useNavigate, useParams } from "react-router-dom";

const AddRestaurant = () => {
  const [name, setName] = useState("");
  const { id } = useParams();
  const navigate = useNavigate();
  useEffect(() => retrieveName(), [id]);

  function retrieveName() {
    if (id != -1) {
      getRestauratnedNameById(id)
        .then((response) => {
          setName(response.data.name);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (name.trim() === "") {
      alert("Restaurant name is required!");
      return;
    }
    if (id == -1) {
      await addNewRestaurant(name);
    } else {
      await updateRestaurant(id, name);
    }
    alert("Restaurant saved successfully!");
    navigate("/restaurants");

    setName(""); // Clear the form after submission
  };

  const formStyle = {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "#f5f5f5",
    border: "1px solid #ddd",
    borderRadius: "8px",
    padding: "20px",
    maxWidth: "400px",
    margin: "50px auto",
    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
  };

  const inputStyle = {
    width: "100%",
    padding: "10px",
    margin: "10px 0",
    borderRadius: "4px",
    border: "1px solid #ccc",
    fontSize: "16px",
  };

  const buttonStyle = {
    backgroundColor: "#4CAF50",
    color: "white",
    padding: "10px 20px",
    border: "none",
    borderRadius: "4px",
    cursor: "pointer",
    fontSize: "16px",
    transition: "background-color 0.3s",
  };

  const buttonHoverStyle = {
    backgroundColor: "#45a049",
  };

  return (
    <form style={formStyle} onSubmit={handleSubmit}>
      <h2 style={{ color: "#333" }}>Add Restaurant</h2>
      <input
        type="text"
        placeholder="Enter restaurant name"
        style={inputStyle}
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <button
        type="submit"
        style={buttonStyle}
        onMouseOver={(e) => (e.target.style.backgroundColor = "#45a049")}
        onMouseOut={(e) => (e.target.style.backgroundColor = "#4CAF50")}
      >
        Submit
      </button>
    </form>
  );
};

export default AddRestaurant;
