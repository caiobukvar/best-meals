package siq.restaurantservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;
import siq.restaurantservice.model.Evaluation;
import siq.restaurantservice.model.Restaurant;
import siq.restaurantservice.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestTemplate restTemplate;
    private final String restaurantEvaluationServiceUrl;

    // Construtor
    public RestaurantService(RestaurantRepository restaurantRepository, RestTemplate restTemplate,
                             @Value("${services.restaurant-evaluation-service}") String restaurantEvaluationServiceUrl) {
        this.restaurantRepository = restaurantRepository;
        this.restTemplate = restTemplate;
        this.restaurantEvaluationServiceUrl = restaurantEvaluationServiceUrl;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }
}
