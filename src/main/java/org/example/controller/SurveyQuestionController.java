package org.example.controller;

import org.example.model.SurveyQuestion;
import org.example.service.SurveyQuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey-questions")
public class SurveyQuestionController {
    private final SurveyQuestionService questionService;

    public SurveyQuestionController(SurveyQuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<SurveyQuestion>> getAll() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyQuestion> getById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SurveyQuestion> create(@RequestBody SurveyQuestion question) {
        SurveyQuestion created = questionService.create(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SurveyQuestion> update(@PathVariable Long id, @RequestBody SurveyQuestion updates) {
        return ResponseEntity.ok(questionService.update(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
