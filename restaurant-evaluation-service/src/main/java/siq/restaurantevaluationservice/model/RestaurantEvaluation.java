package siq.restaurantevaluationservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_evaluation")
public class RestaurantEvaluation {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the meal", example = "1")
    private Long id;

    @Schema(description = "Rating of the meal", example = "5")
    @Column(nullable = false)
    private int rating;

    @Schema(description = "Comment", example = "This meal was so good!")
    @Column(columnDefinition = "TEXT")
    private String comment;

    @JsonIgnore
    @Schema(description = "Restaurant ID", example = "1")
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;

    public RestaurantEvaluation() {
    }

    public RestaurantEvaluation(int rating, String comment, Long restaurantId) {
        this.rating = rating;
        this.comment = comment;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
