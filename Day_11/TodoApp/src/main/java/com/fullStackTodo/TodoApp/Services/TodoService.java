package com.fullStackTodo.TodoApp.Services;

import com.fullStackTodo.TodoApp.Models.Todo;
import com.fullStackTodo.TodoApp.Exceptions.TodoNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	private static int todosCount = 0;

	static {
		todos.add(new Todo(++todosCount, "aaradhya", "Master Java Programming",
				LocalDate.now().plusYears(10), false));
		todos.add(new Todo(++todosCount, "aaradhya", "Learn AWS",
				LocalDate.now().plusYears(11), false));
		todos.add(new Todo(++todosCount, "aaradhya", "Learn DevOps",
				LocalDate.now().plusYears(12), false));
	}

	public List<Todo> findByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		List<Todo> filteredTodos = todos.stream().filter(predicate).toList();
		if (filteredTodos.isEmpty()) {
			throw new TodoNotFoundException("No todos found for user: " + username);
		}
		return filteredTodos;
	}

	public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
		if (username == null || description == null || targetDate == null) {
			throw new IllegalArgumentException("Invalid input: username, description, or targetDate cannot be null.");
		}

		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
		return todo;
	}

	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		boolean isRemoved = todos.removeIf(predicate);
		if (!isRemoved) {
			throw new TodoNotFoundException("Todo with ID " + id + " not found. Unable to delete.");
		}
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Optional<Todo> todo = todos.stream().filter(predicate).findFirst();
		return todo.orElseThrow(() -> new TodoNotFoundException("Todo with ID " + id + " not found."));
	}

	public void updateTodo(Todo todo) {
		if (todo == null || todo.getId() <= 0) {
			throw new IllegalArgumentException("Invalid Todo object: null or invalid ID.");
		}
		deleteById(todo.getId());
		todos.add(todo);
	}
}
