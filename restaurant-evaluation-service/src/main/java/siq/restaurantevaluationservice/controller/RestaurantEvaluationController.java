package siq.restaurantevaluationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.restaurantevaluationservice.model.RestaurantEvaluation;
import siq.restaurantevaluationservice.service.RestaurantEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantEvaluationController {

    private final RestaurantEvaluationService evaluationService;

    public RestaurantEvaluationController(RestaurantEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{restaurantId}/evaluations")
    public ResponseEntity<RestaurantEvaluation> createEvaluation(
            @PathVariable Long restaurantId,
            @RequestBody RestaurantEvaluation evaluation) {
        RestaurantEvaluation savedEvaluation = evaluationService.createEvaluation(restaurantId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/{restaurantId}/evaluations")
    public ResponseEntity<List<RestaurantEvaluation>> getEvaluations(@PathVariable Long restaurantId) {
        List<RestaurantEvaluation> evaluations = evaluationService.getRestaurantEvaluations(restaurantId);
        return ResponseEntity.ok(evaluations);
    }

    @DeleteMapping("/evaluations/{evaluationId}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long evaluationId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }
}
