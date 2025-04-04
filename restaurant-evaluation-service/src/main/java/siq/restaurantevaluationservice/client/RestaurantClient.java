package siq.restaurantevaluationservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import siq.restaurantevaluationservice.dto.RestaurantResponse;

@Service
public class RestaurantClient {

    private final RestTemplate restTemplate;

    @Autowired
    public RestaurantClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestaurantResponse getRestaurantById(Long restaurantId) {
        String url = "http://restaurant-service/restaurants/" + restaurantId;
        return restTemplate.getForObject(url, RestaurantResponse.class);
    }
}
