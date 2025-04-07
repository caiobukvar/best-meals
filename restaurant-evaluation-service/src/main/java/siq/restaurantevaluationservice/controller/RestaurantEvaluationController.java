package siq.restaurantevaluationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.restaurantevaluationservice.model.RestaurantEvaluation;
import siq.restaurantevaluationservice.service.RestaurantEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Restaurant Evaluation Management", description = "Endpoints for managing restaurant evaluations")
public class RestaurantEvaluationController {

    private final RestaurantEvaluationService evaluationService;

    public RestaurantEvaluationController(RestaurantEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{restaurantId}/evaluations")
    @Operation(
            summary = "Create a new restaurant evaluation",
            description = "Creates a new evaluation for a specific restaurant",
            parameters = {
                    @Parameter(
                            name = "restaurantId",
                            description = "ID of the restaurant to evaluate",
                            required = true,
                            example = "1",
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluation created successfully",
                    content = @Content(schema = @Schema(implementation = RestaurantEvaluation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<RestaurantEvaluation> createEvaluation(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody @Valid RestaurantEvaluation evaluation
    ) {
        RestaurantEvaluation savedEvaluation = evaluationService.createEvaluation(restaurantId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/{restaurantId}/evaluations")
    @Operation(
            summary = "Get evaluations by restaurant ID",
            description = "Returns all evaluations for a specific restaurant",
            parameters = {
                    @Parameter(
                            name = "restaurantId",
                            description = "ID of the restaurant",
                            required = true,
                            example = "1",
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluations found",
                    content = @Content(schema = @Schema(implementation = RestaurantEvaluation.class))),
            @ApiResponse(responseCode = "404", description = "No evaluations found for the restaurant")
    })
    public ResponseEntity<?> getEvaluations(
            @PathVariable("restaurantId") @Valid Long restaurantId) {
        List<RestaurantEvaluation> evaluations = evaluationService.getRestaurantEvaluations(restaurantId);

        if (evaluations.isEmpty()) {
            return ResponseEntity.status(404).body("Ainda não existem avaliações para este restaurante");
        }

        return ResponseEntity.ok(evaluations);
    }

    @DeleteMapping("/evaluations/{evaluationId}")
    @Operation(
            summary = "Delete an evaluation",
            description = "Deletes a restaurant evaluation by its ID",
            parameters = {
                    @Parameter(
                            name = "evaluationId",
                            description = "ID of the evaluation to delete",
                            required = true,
                            example = "1",
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evaluation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Evaluation not found")
    })
    public ResponseEntity<Void> deleteEvaluation(
            @PathVariable("evaluationId") @Valid Long evaluationId) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{restaurantId}/evaluations/average")
    @Operation(
            summary = "Get average rating of a restaurant",
            description = "Calculates the average rating for a specific restaurant",
            parameters = {
                    @Parameter(
                            name = "restaurantId",
                            description = "ID of the restaurant",
                            required = true,
                            example = "1",
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average rating calculated"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found or no evaluations")
    })
    public ResponseEntity<?> getRestaurantAverageRating(
            @PathVariable("restaurantId") @Valid Long restaurantId) {
        Double averageRating = evaluationService.getRestaurantAverageRating(restaurantId);

        if (averageRating == null) {
            return ResponseEntity.status(404).body("Ainda não existem avaliações para este restaurante");
        }

        return ResponseEntity.ok(averageRating);
    }

}
