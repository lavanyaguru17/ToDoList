package com.example.ToDoListApp.repository;



import com.example.ToDoListApp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}

