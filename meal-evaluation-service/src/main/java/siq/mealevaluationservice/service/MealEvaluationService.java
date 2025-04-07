package siq.mealevaluationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import siq.mealevaluationservice.exception.ResourceNotFoundException;
import siq.mealevaluationservice.model.MealEvaluation;
import siq.mealevaluationservice.repository.MealEvaluationRepository;

import java.util.List;

@Service
public class MealEvaluationService {

    private final MealEvaluationRepository evaluationRepository;
    private final RestTemplate restTemplate;

    @Value("${services.meal-service}")
    private String mealServiceUrl;

    public MealEvaluationService(MealEvaluationRepository evaluationRepository, RestTemplate restTemplate) {
        this.evaluationRepository = evaluationRepository;
        this.restTemplate = restTemplate;
    }

    public MealEvaluation createEvaluation(Long restaurantId, Long mealId, MealEvaluation evaluation) {
        String mealUrl = mealServiceUrl + "/api/restaurants/" + restaurantId + "/meals/" + mealId;

        try {
            restTemplate.getForObject(mealUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Meal not found or does not belong to this restaurant.");
        } catch (Exception e) {
            throw new RuntimeException("Error validating meal on meal-service.", e);
        }

        evaluation.setMealId(mealId);
        evaluation.setRestaurantId(restaurantId);
        return evaluationRepository.save(evaluation);
    }

    public List<MealEvaluation> getMealEvaluations(Long restaurantId, Long mealId) {
        String mealUrl = mealServiceUrl + "/api/restaurants/" + restaurantId + "/meals/" + mealId;

        try {
            restTemplate.getForObject(mealUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Meal not found or does not belong to this restaurant.");
        } catch (Exception e) {
            throw new RuntimeException("Error validating meal on meal-service.", e);
        }

        List<MealEvaluation> evaluations = evaluationRepository.findByMealIdAndRestaurantId(mealId, restaurantId);
        if (evaluations.isEmpty()) {
            throw new ResourceNotFoundException("No evaluations found for this meal");
        }
        return evaluations;
    }

    public void deleteEvaluation(Long evaluationId) {
        if (!evaluationRepository.existsById(evaluationId)) {
            throw new ResourceNotFoundException("Evaluation with ID " + evaluationId + " not found");
        }
        evaluationRepository.deleteById(evaluationId);
    }

    public Double getAverageRating(Long restaurantId, Long mealId) {
        String mealUrl = mealServiceUrl + "/api/restaurants/" + restaurantId + "/meals/" + mealId;

        try {
            restTemplate.getForObject(mealUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Meal not found or does not belong to this restaurant.");
        } catch (Exception e) {
            throw new RuntimeException("Error validating meal on meal-service.", e);
        }

        List<MealEvaluation> evaluations = evaluationRepository.findByMealIdAndRestaurantId(mealId, restaurantId);
        if (evaluations.isEmpty()) {
            throw new ResourceNotFoundException("No evaluations found for this meal");
        }

        double average = evaluations.stream()
                .mapToInt(MealEvaluation::getRating)
                .average()
                .orElse(0.0);

        return Math.round(average * 100.0) / 100.0;
    }

}
