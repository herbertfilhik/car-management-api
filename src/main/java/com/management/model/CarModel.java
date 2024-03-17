package com.management.model;

import jakarta.persistence.Entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A brand é obrigatória.")
    @NotNull(message = "A brand está null.")
    @Size(max = 255, message = "A brand deve ter no máximo 255 caracteres.")
    @Column(name = "brand")
    private String brand;
    
    @NotBlank(message = "O model é obrigatório.")
    @NotNull(message = "O model está null.")
    @NotEmpty(message = "O campo model não pode ser empty")
    @Size(max = 255, message = "O model deve ter no máximo 255 caracteres.")
    @Column(name = "model")
    private String model;

    @NotEmpty(message = "O campo 'license_plate' é obrigatório.")
    @NotNull(message = "O license plate está null.")
    @Column(name = "license_plate") // Assegura o mapeamento correto para o nome da coluna no banco de dados
    private String licensePlate; // Nome da variável em camelCase

    @Min(value = 1900, message = "O year deve ser maior que 1900.")
    @Column(name = "year")
    //@NotEmpty(message = "O year é obrigatório.")
    //@NotNull(message = "O year está null.")
    private Integer year;

    // Construtor padrão necessário para o JPA
    public CarModel() {
    }
    
    public CarModel(String brand, String model, Integer year, String licensePlate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
    }

    // Getters e Setters
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
