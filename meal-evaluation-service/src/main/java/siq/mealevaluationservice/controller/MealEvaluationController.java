package siq.mealevaluationservice.controller;

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
import siq.mealevaluationservice.model.MealEvaluation;
import siq.mealevaluationservice.service.MealEvaluationService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/meal-evaluations")
@Tag(name = "Meal Evaluation Management", description = "Endpoints for managing meal evaluations")
public class MealEvaluationController {

    private final MealEvaluationService evaluationService;

    public MealEvaluationController(MealEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/{mealId}")
    @Operation(
            summary = "Create a new meal evaluation",
            description = "Creates a new meal evaluation for a specific meal from a specific restaurant",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
                    @Parameter(name = "mealId",description = "ID of the meal", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal evaluation created successfully",
                    content = @Content(schema = @Schema(implementation = MealEvaluation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<MealEvaluation> createEvaluation(
            @PathVariable("restaurantId") Long restaurantId,
             @PathVariable("mealId") Long mealId,
            @RequestBody @Valid MealEvaluation evaluation) {
        MealEvaluation savedEvaluation = evaluationService.createEvaluation(restaurantId, mealId, evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/{mealId}")
    @Operation(
            summary = "Get meal evaluations",
            description = "Retrieves evaluations for a specific meal from a specific restaurant",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
                    @Parameter(name = "mealId", description = "ID of the meal", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MealEvaluation.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant or meal not found")
    })
    public ResponseEntity<List<MealEvaluation>> getMealEvaluations(
            @PathVariable("restaurantId") Long restaurantId,
            @PathVariable("mealId") Long mealId) {
        List<MealEvaluation> evaluations = evaluationService.getMealEvaluations(restaurantId, mealId);
        return ResponseEntity.ok(evaluations);
    }

    @DeleteMapping("/{evaluationId}")
    @Operation(
            summary = "Delete a meal evaluation",
            description = "Deletes a meal evaluation by its ID",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
                    @Parameter(name = "evaluationId", description = "ID of the evaluation to delete", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evaluation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Evaluation not found")
    })
    public ResponseEntity<Void> deleteEvaluation(
            @PathVariable("evaluationId") Long evaluationId,
            @PathVariable("restaurantId") Long restaurantId
    ) {
        evaluationService.deleteEvaluation(evaluationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{mealId}/average")
    @Operation(
            summary = "Get average rating for a meal",
            description = "Calculates the average rating for a specific meal from a specific restaurant",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
                    @Parameter(name = "mealId", description = "ID of the meal", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Average rating calculated successfully",
                    content = @Content(schema = @Schema(type = "number", format = "double", example = "4.25"))),
            @ApiResponse(responseCode = "404", description = "Meal not found or no evaluations found")
    })
    public ResponseEntity<Double> getAverageRating(
            @PathVariable("restaurantId") Long restaurantId,
            @PathVariable("mealId") Long mealId
    ) {
        Double average = evaluationService.getAverageRating(restaurantId, mealId);
        return ResponseEntity.ok(average);
    }

}
