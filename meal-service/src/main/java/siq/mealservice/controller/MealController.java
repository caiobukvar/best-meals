package siq.mealservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.mealservice.model.Meal;
import siq.mealservice.service.MealService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/meals")
@Tag(name = "Meal Management", description = "Endpoints for managing meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new meal",
            description = "Creates a new meal for a specific restaurant",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Meal created successfully",
                    content = @Content(schema = @Schema(implementation = Meal.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<Meal> createMeal(
            @PathVariable("restaurantId") Long restaurantId,
            @RequestBody @Valid Meal meal
    ) {
        Meal createdMeal = mealService.createMeal(meal, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeal);
    }


    @GetMapping("/{mealId}")
    @Operation(
            summary = "Get meal by ID",
            description = "Retrieves a meal by its ID within a specific restaurant",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
                    @Parameter(name = "mealId", description = "ID of the meal", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meal found",
                    content = @Content(schema = @Schema(implementation = Meal.class))),
            @ApiResponse(responseCode = "404", description = "Meal not found")
    })
    public ResponseEntity<Meal> getMealById(
            @PathVariable("restaurantId") Long restaurantId,
            @PathVariable("mealId") Long mealId
    ) {
        return ResponseEntity.ok(mealService.getMealById(mealId));
    }

    @GetMapping
    @Operation(
            summary = "Get meals by restaurant ID",
            description = "Retrieves all meals for a specific restaurant",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meals found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Meal.class)))),
            @ApiResponse(responseCode = "404", description = "No meals found for the restaurant")
    })
    public ResponseEntity<?> getMealsByRestaurant(
            @PathVariable("restaurantId") Long restaurantId) {
        List<Meal> meals = mealService.getMealsByRestaurant(restaurantId);

        if (meals.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ainda não existem refeições cadastradas para este restaurante");
        }

        return ResponseEntity.ok(meals);
    }

    @DeleteMapping("/{mealId}")
    @Operation(
            summary = "Delete a meal",
            description = "Deletes a meal by its ID",
            parameters = {
                    @Parameter(name = "restaurantId", description = "ID of the restaurant", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64")),
                    @Parameter(name = "mealId", description = "ID of the meal", required = true,
                            in = ParameterIn.PATH, schema = @Schema(type = "integer", format = "int64"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Meal deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Meal not found")
    })
    public ResponseEntity<Void> deleteMeal(
            @PathVariable("restaurantId") Long restaurantId,
            @PathVariable("mealId") Long mealId
    ) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }
}
