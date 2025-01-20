import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

export function retrieveAllTodosForUser(username) {
  return apiClient.get(`/users/${username}/todos`);
}

export function deleteTodoForUserById(username, id) {
  return apiClient.delete(`/users/${username}/todos/${id}`);
}

export function retrieveTodoForUserById(username, id) {
  return apiClient.get(`/users/${username}/todos/${id}`);
}

export function updateTodoForUserById(username, id, todo) {
  return apiClient.put(`/users/${username}/todos/${id}`, todo);
}
