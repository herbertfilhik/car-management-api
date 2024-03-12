package com.management.model;

import jakarta.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;

    @NotEmpty(message = "O campo 'plate' é obrigatório.")
    private String plate;

    private Integer year;

    // Constructors, getters, and setters
    public CarModel() {
    }

    public CarModel(String brand, String model, String plate, Integer year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.plate = plate;
    }

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
}
