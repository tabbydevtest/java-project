package com.example.task_backend.service;

import com.example.task_backend.Step;
import com.example.task_backend.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;

    public Step createStep(Step step) {
        return stepRepository.save(step);
    }

    public Optional<Step> updateStep(String id, Step stepDetails) {
        Optional<Step> stepOptional = stepRepository.findById(id);
        if (stepOptional.isPresent()) {
            Step step = stepOptional.get();
            step.setStepName(stepDetails.getStepName());
            step.setTaskId(stepDetails.getTaskId());
            step.setStepDescription(stepDetails.getStepDescription());
            step.setDueDate(stepDetails.getDueDate());

            return Optional.of(stepRepository.save(step));
        }
        return Optional.empty();
    }

    public List<Step> getStepsByTaskId(String taskId) {
        return stepRepository.findByTaskId(taskId);
    }
}
