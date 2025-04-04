package siq.restaurantevaluationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.restaurantevaluationservice.model.RestaurantEvaluation;

import java.util.List;

public interface RestaurantEvaluationRepository extends JpaRepository<RestaurantEvaluation, Long> {
    List<RestaurantEvaluation> findByRestaurantId(Long restaurantId);
}
