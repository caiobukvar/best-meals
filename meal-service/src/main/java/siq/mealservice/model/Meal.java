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

    // @Schema para adicionar ex no swagger
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
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public void assignRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Meal() {}

    public Meal(String name, Restaurant restaurant, double cost, String ingredients) {
        this.name = name;
        this.restaurant = restaurant;
        this.cost = cost;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant  getRestaurant() {
        return restaurant;
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
}
