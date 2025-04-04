package siq.restaurantservice.model;

public class Evaluation {
    private Long id;
    private Long restaurantId;
    private int rating;
    private String comment;

    public Evaluation() {}

    public Evaluation(Long id, Long restaurantId, int rating, String comment) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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
}
