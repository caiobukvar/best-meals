package siq.mealevaluationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.mealevaluationservice.model.MealEvaluation;

import java.util.List;

public interface MealEvaluationRepository extends JpaRepository<MealEvaluation, Long> {
    List<MealEvaluation> findByMealIdAndRestaurantId(Long mealId, Long restaurantId);
}