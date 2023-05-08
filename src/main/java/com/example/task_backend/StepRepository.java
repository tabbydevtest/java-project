package com.example.task_backend.repository;

import com.example.task_backend.Step;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends MongoRepository<Step, String> {
    List<Step> findByTaskId(String taskId);
}