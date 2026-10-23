package org.example.controller;

import org.example.model.SurveyParticipation;
import org.example.service.SurveyParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey-participations")
public class SurveyParticipationController {
    private final SurveyParticipationService participationService;

    public SurveyParticipationController(SurveyParticipationService participationService) {
        this.participationService = participationService;
    }

    @GetMapping
    public ResponseEntity<List<SurveyParticipation>> getAll() {
        return ResponseEntity.ok(participationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyParticipation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(participationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SurveyParticipation> create(@RequestBody SurveyParticipation participation) {
        SurveyParticipation created = participationService.create(participation);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SurveyParticipation> update(@PathVariable Long id, @RequestBody SurveyParticipation updates) {
        return ResponseEntity.ok(participationService.update(id, updates));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        participationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<SurveyParticipation> submit(@PathVariable Long id) {
        return ResponseEntity.ok(participationService.submit(id));
    }

    @PostMapping("/{id}/restart")
    public ResponseEntity<SurveyParticipation> restart(@PathVariable Long id) {
        return ResponseEntity.ok(participationService.restart(id));
    }
}
