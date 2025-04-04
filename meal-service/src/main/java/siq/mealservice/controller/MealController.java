package siq.mealservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import siq.mealservice.model.Meal;
import siq.mealservice.model.Restaurant;
import siq.mealservice.service.MealService;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        Restaurant restaurant = meal.getRestaurant(); // Pegamos o restaurante da refeição
        return ResponseEntity.ok(mealService.createMeal(meal, restaurant));
    }

    @GetMapping("/{mealId}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long mealId) {
        return ResponseEntity.ok(mealService.getMealById(mealId));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Meal>> getMealsByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(mealService.getMealsByRestaurant(restaurantId));
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long mealId) {
        mealService.deleteMeal(mealId);
        return ResponseEntity.noContent().build();
    }
}
