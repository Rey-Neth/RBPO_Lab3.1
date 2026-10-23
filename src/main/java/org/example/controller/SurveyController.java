package org.example.controller;

import org.example.model.Survey;
import org.example.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<List<Survey>> getAll() {
        return ResponseEntity.ok(surveyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getById(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Survey> create(@RequestBody Survey survey) {
        Survey created = surveyService.create(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Survey> update(@PathVariable Long id, @RequestBody Survey updates) {
        return ResponseEntity.ok(surveyService.update(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        surveyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<Survey> publish(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.publish(id));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Survey> close(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.close(id));
    }

    @PostMapping("/{id}/copy")
    public ResponseEntity<Survey> copy(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(surveyService.copy(id));
    }
}
