package siq.mealevaluationservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.mealevaluationservice.model.MealEvaluation;
import siq.mealevaluationservice.service.MealEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/meal-evaluations")
public class MealEvaluationController {

    private final MealEvaluationService evaluationService;

    public MealEvaluationController(MealEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{mealId}")
    public ResponseEntity<MealEvaluation> createEvaluation(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId,
            @RequestBody @Valid MealEvaluation evaluation) {
        MealEvaluation savedEvaluation = evaluationService.createEvaluation(restaurantId, mealId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<List<MealEvaluation>> getMealEvaluations(
            @PathVariable Long restaurantId,
            @PathVariable Long mealId) {
        List<MealEvaluation> evaluations = evaluationService.getMealEvaluations(restaurantId, mealId);
        return ResponseEntity.ok(evaluations);
    }

    @DeleteMapping("/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long evaluationId, @PathVariable Long restaurantId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }
}
