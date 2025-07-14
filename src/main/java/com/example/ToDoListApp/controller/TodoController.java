package com.example.ToDoListApp.controller;

import com.example.ToDoListApp.model.Todo;
import com.example.ToDoListApp.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Todo> getAll() {
        return repository.getAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody String task) {
        return repository.add(task);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTodo(@PathVariable int id) {
        return repository.delete(id);
    }
}
