package com.example.ToDoListApp.repository;


import com.example.ToDoListApp.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepository {
    private final List<Todo> todos = new ArrayList<>();
    private int currentId = 1;

    public List<Todo> getAll() {
        return todos;
    }

    public Todo add(String task) {
        Todo todo = new Todo(currentId++, task);
        todos.add(todo);
        return todo;
    }

    public boolean delete(int id) {
        return todos.removeIf(todo -> todo.getId() == id);
    }
}

