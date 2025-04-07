package siq.mealservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Meal {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "Coxinha")
    @NotBlank
    private String name;

    @Schema(example = "10.9")
    @Positive
    private double cost;

    @Schema(example = "Massa de mandioca, frango e requeijão")
    @NotBlank
    private String ingredients;

    @JsonIgnore
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    public Meal() {}

    public Meal(String name, Long restaurantId, double cost, String ingredients) {
        this.name = name;
        this.restaurantId = restaurantId;
        this.cost = cost;
        this.ingredients = ingredients;
    }

    public void assignRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
