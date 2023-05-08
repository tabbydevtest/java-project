package com.example.task_backend;

import lombok.AllArgsConstructor;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final MongoTemplate mongoTemplate;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<String> findTaskIdsByTaskCategory(String taskCategory) {
        List<Task> tasks = taskRepository.findByTaskCategory(taskCategory);
        return tasks.stream().map(Task::getId).collect(Collectors.toList());
    }

    public Set<String> findAllUniqueTaskCategories() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(Task::getTaskCategory).collect(Collectors.toSet());
    }


    public Task createTask(Task task) {
        Task savedTask = usingMongoTemplateAndQuery(task.getTaskName(), task);
        return savedTask;
    }

    private Task usingMongoTemplateAndQuery(String taskName, Task task) {
        Query query = new Query();
        query.addCriteria(Criteria.where("taskName").is(taskName));

        Task existingTask = mongoTemplate.findOne(query, Task.class);

        if (existingTask == null) {
            return mongoTemplate.save(task);
        } else {
            throw new TaskAlreadyExistsException("Task with the same name already exists: " + taskName);
        }
    }


    public Task findTaskById(String id) {
        return taskRepository.findTaskById(id);
    }




    public class TaskAlreadyExistsException extends RuntimeException {
        public TaskAlreadyExistsException(String message) {
            super(message);
        }
    }





}