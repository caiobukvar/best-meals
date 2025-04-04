package siq.mealevaluationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

    public MealEvaluation createEvaluation(Long mealId, MealEvaluation evaluation) {
        // Verifica se a refeição existe no meal-service
        String mealUrl = mealServiceUrl + "/meals/" + mealId;
        try {
            restTemplate.getForObject(mealUrl, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Refeição não encontrada no meal-service", e);
        }

        evaluation.setMealId(mealId);
        return evaluationRepository.save(evaluation);
    }

    public List<MealEvaluation> getMealEvaluations(Long mealId) {
        return evaluationRepository.findByMealId(mealId);
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
