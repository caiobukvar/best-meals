package siq.mealservice.service;

import siq.mealservice.client.RestaurantClient;
import siq.mealservice.model.Restaurant;
import siq.mealservice.dto.RestaurantResponse;
import siq.mealservice.model.Meal;
import siq.mealservice.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final RestaurantClient restaurantClient;

    public MealService(MealRepository mealRepository, RestaurantClient restaurantClient) {
        this.mealRepository = mealRepository;
        this.restaurantClient = restaurantClient;
    }

    public Meal createMeal(Meal meal, Long restaurantId) {
        RestaurantResponse restaurantResponse = restaurantClient.getRestaurantById(restaurantId);

        if (restaurantResponse == null) {
            throw new RuntimeException("Restaurante não encontrado");
        }

        Restaurant restaurant = new Restaurant(restaurantResponse.getId());
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
