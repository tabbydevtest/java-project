package com.example.task_backend;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor


public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<Task> fetchAllTasks() {
        return taskService.getAllTasks();
    }
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    @GetMapping("/search")
    public List<String> searchTaskIdsByCategory(@RequestParam String taskCategory) {
        return taskService.findTaskIdsByTaskCategory(taskCategory);
    }



    @GetMapping("/unique-categories")
    public Set<String> findAllUniqueTaskCategories() {
        return taskService.findAllUniqueTaskCategories();
    }

    @GetMapping("/{id}")
    public Task findTaskById(@PathVariable String id) {
        return taskService.findTaskById(id);
    }


    @ExceptionHandler(TaskService.TaskAlreadyExistsException.class)
    public ResponseEntity<String> handleTaskAlreadyExistsException(TaskService.TaskAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
