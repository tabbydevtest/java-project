package com.example.task_backend;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    Task findTaskByTaskName(String taskName);
    List<Task> findByTaskCategory(String taskCategory);

    Task findTaskById(String id);
}




