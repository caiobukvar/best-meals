package siq.mealservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.mealservice.model.Meal;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByRestaurantId(Long restaurantId);
}
