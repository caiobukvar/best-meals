package siq.restaurantservice.controller;

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
import siq.restaurantservice.model.Evaluation;
import siq.restaurantservice.model.Restaurant;
import siq.restaurantservice.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Restaurant Management", description = "Endpoints for managing restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @Operation(summary = "Create a new restaurant", description = "Creates a new restaurant with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant created successfully",
                    content = @Content(schema = @Schema(implementation = Restaurant.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    @GetMapping("/{restaurantId}")
    @Operation(
            summary = "Get restaurant by ID",
            description = "Returns a restaurant based on the provided ID",
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
            @ApiResponse(responseCode = "200", description = "Restaurant found",
                    content = @Content(schema = @Schema(implementation = Restaurant.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<Restaurant> getRestaurantById(
            @PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.ok(restaurant);
    }

    @GetMapping
    @Operation(summary = "Get all restaurants", description = "Returns a list of all restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @DeleteMapping("/{restaurantId}")
    @Operation(
            summary = "Delete a restaurant",
            description = "Deletes a restaurant based on the provided ID",
            parameters = {
                    @Parameter(
                            name = "restaurantId",
                            description = "ID of the restaurant to delete",
                            required = true,
                            example = "1",
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{restaurantId}/evaluations")
    @Operation(
            summary = "Get restaurant evaluations",
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
                    content = @Content(schema = @Schema(implementation = Evaluation.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    public ResponseEntity<List<Evaluation>> getRestaurantEvaluations(
            @PathVariable @Valid Long restaurantId) {
        List<Evaluation> evaluations = restaurantService.getRestaurantEvaluations(restaurantId);
        return ResponseEntity.ok(evaluations);
    }
}