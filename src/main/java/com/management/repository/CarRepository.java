package com.management.repository;

import com.management.model.Car;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<Car> findByBrandAndModelAndYear(String brand, String model, Integer year);
}
