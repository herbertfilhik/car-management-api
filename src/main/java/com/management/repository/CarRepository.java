package com.management.repository;

import com.management.model.CarModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {

	Optional<CarModel> findByBrandAndModelAndYearAndLicensePlate(String brand, String model, Integer year, String licensePlate);
}
