package siq.mealservice.service;

import org.springframework.stereotype.Service;
import siq.mealservice.model.Meal;
import siq.mealservice.model.Restaurant;
import siq.mealservice.repository.MealRepository;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal createMeal(Meal meal, Restaurant restaurant) {
        meal.assignRestaurant(restaurant);
        return mealRepository.save(meal);
    }

    public Meal getMealById(Long mealId) {
        return mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));
    }

    public List<Meal> getMealsByRestaurant(Long restaurantId) {
        return mealRepository.findByRestaurantId(restaurantId);
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
    }
}
