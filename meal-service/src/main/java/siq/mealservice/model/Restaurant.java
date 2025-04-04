package siq.mealservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Restaurant {

    @Id
    private Long id;

    public Restaurant() {
    }

    public Restaurant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
