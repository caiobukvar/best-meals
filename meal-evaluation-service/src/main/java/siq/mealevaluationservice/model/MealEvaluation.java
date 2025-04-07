package siq.mealevaluationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class MealEvaluation {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "meal_id", nullable = false)
    private Long mealId;

    @Schema(example = "5")
    @Min(1)
    @Max(5)
    private int rating;

    @Schema(example = "The meal was great!")
    private String comment;

    @JsonIgnore
    private Long restaurantId;

    // Getters e Setters
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMealId() { return mealId; }

    public void setMealId(Long mealId) { this.mealId = mealId; }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getRestaurantId() { return restaurantId; }

    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
}
