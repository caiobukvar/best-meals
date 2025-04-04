package siq.restaurantevaluationservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant_evaluation")
public class RestaurantEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

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
