package siq.restaurantservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
@Schema(description = "Restaurant information")
public class Restaurant {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the restaurant", example = "1")
    private Long id;

    @Column(name = "name")
    @Schema(description = "Name of the restaurant", example = "Delicious Food")
    private String name;

    @Column(name = "address")
    @Schema(description = "Address of the restaurant", example = "123 Main St")
    private String address;

    @Column(name = "state")
    @Schema(description = "State where the restaurant is located", example = "CA")
    private String state;

    @Column(name = "zip_code")
    @Schema(description = "ZIP code of the restaurant", example = "12345")
    private String zipCode;

    // Constructor and getters/setters remain the same
    public Restaurant() {}

    public Restaurant(Long id, String name, String address, String state, String zipCode) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.state = state;
        this.zipCode = zipCode;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}