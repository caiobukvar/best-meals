package siq.restaurantevaluationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import siq.restaurantevaluationservice.model.RestaurantEvaluation;
import siq.restaurantevaluationservice.repository.RestaurantEvaluationRepository;

import java.util.List;

@Service
public class RestaurantEvaluationService {

    private final RestaurantEvaluationRepository evaluationRepository;
    private final RestTemplate restTemplate;
    private final String restaurantServiceUrl;

    public RestaurantEvaluationService(
            RestaurantEvaluationRepository evaluationRepository,
            RestTemplate restTemplate,
            @Value("${services.restaurant-service}") String restaurantServiceUrl) {
        this.evaluationRepository = evaluationRepository;
        this.restTemplate = restTemplate;
        this.restaurantServiceUrl = restaurantServiceUrl;
    }

    public RestaurantEvaluation createEvaluation(Long restaurantId, RestaurantEvaluation evaluation) {
        String restaurantUrl = "http://restaurant-service/api/restaurants/" + restaurantId;

        try {
            restTemplate.getForObject(restaurantUrl, String.class); // Apenas verifica se o restaurante existe
        } catch (Exception e) {
            throw new RuntimeException("Restaurante não encontrado no serviço de restaurantes", e);
        }

        evaluation.setRestaurantId(restaurantId);
        return evaluationRepository.save(evaluation);
    }

    public List<RestaurantEvaluation> getRestaurantEvaluations(Long restaurantId) {
        String restaurantUrl = "http://restaurant-service/api/restaurants/" + restaurantId;

        try {
            restTemplate.getForObject(restaurantUrl, String.class); // Apenas verifica se o restaurante existe
        } catch (Exception e) {
            throw new RuntimeException("Restaurante não encontrado no serviço de restaurantes", e);
        }

        return evaluationRepository.findByRestaurantId(restaurantId);
    }

    public void deleteEvaluation(Long evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }
}
