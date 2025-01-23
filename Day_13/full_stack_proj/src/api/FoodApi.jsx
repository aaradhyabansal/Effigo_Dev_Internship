import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

// const getAllRestaurants = async () => {
//   try {
//     const response = await  // Endpoint is '/restaurants'
//     return response.data;
//   } catch (error) {
//     console.error("Error fetching restaurants", error);
//     throw error;
//   }
// };

export function getAllRestaurants() {
  return apiClient.get("/restaurants");
}

export function getRestaurantById(id) {
  return apiClient.get(`/restaurants/${id}/dishes`);
}

export function addNewRestaurant(name) {
  return apiClient.post(`/restaurants/addrestaurant/${name}`);
}

export function updateRestaurant(id, name) {
  return apiClient.put(`/restaurants/${id}/update/${name}`);
}
export function getRestauratnedNameById(id) {
  return apiClient.get(`/restaurants/getrestaurant/${id}`);
}

export function deleteRestaurantById(id) {
  return apiClient.delete(`/restaurants/delete/${id}`);
}

export function getAllDishes() {
  return apiClient.get(`/dishes`);
}

export function addNewDish(dish, id) {
  console.log(dish);
  console.log(dish.restaurant_id);
  console.log(id);
  return apiClient.post(`/dishes/addnewdish/${id}`, dish);
}

export function deleteDishById(id) {
  return apiClient.delete(`/dishes/delete/${id}`);
}
export function updateDish(dish, id) {
  return apiClient.put(`/dishes/update/${id}`, dish);
}
export function getDishById(id) {
  return apiClient.get(`/dishes/${id}`);
}
