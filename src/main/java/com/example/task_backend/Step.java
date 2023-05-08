package com.example.task_backend;

import com.example.task_backend.CustomLocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@CompoundIndexes({
        @CompoundIndex(name = "uniqueStepNameAndTaskId", def = "{'stepName': 1, 'taskId': 1}", unique = true)
})
public class Step {
    @Id
    private String id;
    private String stepName;
    private String taskId;
    private String stepDescription;

    public Step(String stepName, String taskId, String stepDescription, LocalDate dueDate) {
        this.stepName = stepName;
        this.taskId = taskId;
        this.stepDescription = stepDescription;
        this.dueDate = LocalDate.from(dueDate);

    }

    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate dueDate;


}

