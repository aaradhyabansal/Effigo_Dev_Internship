import axios from "axios";
const usernamee = "aaradhya"; // Replace with the actual username
const passwordd = "abcd1234";
const basicAuth = `Basic ${btoa(`${usernamee}:${passwordd}`)}`;
// Create an Axios instance
const apiClient = axios.create({
  baseURL: "http://localhost:8080", // Base URL for the backend// Automatically include credentials (e.g., cookies) with requests
});

// Retrieve all todos for a user
export function retrieveAllTodosForUser(username) {
  console.log(apiClient.defaults); // Check default settings like baseURL, headers, etc.
  return apiClient.get(`/users/${username}/todos`);
}

// Delete a todo by ID
export function deleteTodoForUserById(username, id) {
  return apiClient.delete(`/users/${username}/todos/${id}`);
}

// Retrieve a specific todo by ID
export function retrieveTodoForUserById(username, id) {
  return apiClient.get(`/users/${username}/todos/${id}`);
}

// Update a todo by ID
export function updateTodoForUserById(username, id, todo) {
  return apiClient.put(`/users/${username}/todos/${id}`, todo);
}

// Add a new todo for a user
export function addTodoForUser(username, todo) {
  return apiClient.post(`/users/${username}/todos`, todo);
}
