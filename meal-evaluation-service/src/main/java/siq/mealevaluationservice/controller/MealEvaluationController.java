package siq.mealevaluationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.mealevaluationservice.model.MealEvaluation;
import siq.mealevaluationservice.service.MealEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/meal-evaluations")
public class MealEvaluationController {

    private final MealEvaluationService evaluationService;

    public MealEvaluationController(MealEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{mealId}")
    public ResponseEntity<MealEvaluation> createEvaluation(
            @PathVariable Long mealId,
            @RequestBody @Valid MealEvaluation evaluation) {
        MealEvaluation savedEvaluation = evaluationService.createEvaluation(mealId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<List<MealEvaluation>> getMealEvaluations(@PathVariable Long mealId) {
        List<MealEvaluation> evaluations = evaluationService.getMealEvaluations(mealId);
        return ResponseEntity.ok(evaluations);
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long evaluationId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }
}
