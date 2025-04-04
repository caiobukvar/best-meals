package siq.restaurantservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import siq.restaurantservice.model.Restaurant;
import siq.restaurantservice.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestTemplate restTemplate;

    @Value("${services.restaurant-evaluation-service}")
    private String restaurantEvaluationServiceUrl;

    public RestaurantService(RestaurantRepository restaurantRepository, RestTemplate restTemplate) {
        this.restaurantRepository = restaurantRepository;
        this.restTemplate = restTemplate;
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

    public Object getRestaurantEvaluations(Long restaurantId) {
        String evaluationsUrl = restaurantEvaluationServiceUrl + "/restaurants/" + restaurantId + "/evaluations";
        return restTemplate.getForObject(evaluationsUrl, Object.class);
    }
}
