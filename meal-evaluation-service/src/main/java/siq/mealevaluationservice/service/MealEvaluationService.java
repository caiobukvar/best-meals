package siq.mealevaluationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
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
            throw new RuntimeException("Refeição não encontrada ou não pertence ao restaurante informado.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar refeição no meal-service.", e);
        }

        evaluation.setMealId(mealId);
        evaluation.setRestaurantId(restaurantId);
        return evaluationRepository.save(evaluation);
    }

    public List<MealEvaluation> getMealEvaluations(Long restaurantId, Long mealId) {
        return evaluationRepository.findByMealIdAndRestaurantId(mealId, restaurantId);
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
