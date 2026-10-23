package org.example.controller;

import org.example.model.SurveyAnswer;
import org.example.service.SurveyAnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey-answers")
public class SurveyAnswerController {
    private final SurveyAnswerService answerService;

    public SurveyAnswerController(SurveyAnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<List<SurveyAnswer>> getAll() {
        return ResponseEntity.ok(answerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyAnswer> getById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SurveyAnswer> create(@RequestBody SurveyAnswer answer) {
        SurveyAnswer created = answerService.create(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SurveyAnswer> update(@PathVariable Long id, @RequestBody SurveyAnswer updates) {
        return ResponseEntity.ok(answerService.update(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
