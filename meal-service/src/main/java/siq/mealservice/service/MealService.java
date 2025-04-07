package siq.mealservice.service;

import org.springframework.stereotype.Service;
import siq.mealservice.client.RestaurantClient;
import siq.mealservice.dto.RestaurantResponse;
import siq.mealservice.exception.RestaurantNotFoundException;
import siq.mealservice.model.Meal;
import siq.mealservice.repository.MealRepository;

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
        System.out.println("Buscando restaurante com ID: " + restaurantId);
        RestaurantResponse restaurantResponse = restaurantClient.getRestaurantById(restaurantId);

        // Add this debug print
        System.out.println("Restaurant response: " + restaurantResponse);

        if (restaurantResponse == null) {
            throw new RestaurantNotFoundException("Restaurante com ID " + restaurantId + " não encontrado.");
        }

        // Add this debug print
        System.out.println("Restaurant ID from response: " + restaurantResponse.getId());

        meal.setRestaurantId(restaurantId);

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
