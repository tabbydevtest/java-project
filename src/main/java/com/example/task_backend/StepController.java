package com.example.task_backend.controller;

import com.example.task_backend.Step;
import com.example.task_backend.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/steps")
public class StepController {

    @Autowired
    private StepService stepService;

    @PostMapping
    public ResponseEntity<Step> createStep(@RequestBody Step step) {
        Step createdStep = stepService.createStep(step);
        return new ResponseEntity<>(createdStep, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Step> updateStep(@PathVariable String id, @RequestBody Step stepDetails) {
        Optional<Step> updatedStep = stepService.updateStep(id, stepDetails);
        if (updatedStep.isPresent()) {
            return new ResponseEntity<>(updatedStep.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Step>> getStepsByTaskId(@PathVariable String taskId) {
        List<Step> steps = stepService.getStepsByTaskId(taskId);
        return new ResponseEntity<>(steps, HttpStatus.OK);
    }


}