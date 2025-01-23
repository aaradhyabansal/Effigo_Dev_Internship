import { BrowserRouter, Routes, Route } from "react-router-dom";
import RestaurantList from "../Components/RestaurantList";
import RestaurantDetails from "../Components/RestaurantDetails";
import AddRestaurant from "../Components/AddRestaurant";
import NavBar from "../Components/Navbar";
import Dishes from "../Components/Dishes";
import AddNewDish from "../Components/AddDish";

function Food() {
  return (
    <div>
      <NavBar />
      <Routes>
        <Route path="/restaurants" element={<RestaurantList />}></Route>
        <Route path="/restaurants/:id" element={<RestaurantDetails />}></Route>
        <Route
          path="/restaurants/addrestaurant/:id"
          element={<AddRestaurant />}
        ></Route>
        <Route path="/dishes" element={<Dishes />}></Route>
        <Route path="/dishes/addnewdish/:id" element={<AddNewDish />}></Route>
      </Routes>
    </div>
  );
}

export default Food;
