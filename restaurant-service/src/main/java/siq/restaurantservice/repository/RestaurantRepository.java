package siq.restaurantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import siq.restaurantservice.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
