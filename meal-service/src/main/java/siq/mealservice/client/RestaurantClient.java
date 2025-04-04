package siq.mealservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import siq.mealservice.dto.RestaurantResponse;

@FeignClient(name = "restaurant-service")
public interface RestaurantClient {
    @GetMapping("/restaurants/{id}")
    RestaurantResponse getRestaurantById(@PathVariable("id") Long id);
}
