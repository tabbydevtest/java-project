package com.example.task_backend;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Document
public class Task {
    @Id
    private String id;
    @Indexed(unique = true)
    private String  taskName;



    private String taskDescription;


    private String  taskCategory;

    public Task(String taskName, String taskDescription, String taskCategory) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskCategory = taskCategory;
    }

}

