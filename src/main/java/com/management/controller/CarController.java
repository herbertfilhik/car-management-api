package com.management.controller;

import com.management.exception.CarAlreadyExistsException;
import com.management.model.CarModel;
import com.management.repository.CarRepository;
import com.management.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping
	public List<CarModel> getAllCars() {
		return carService.findAllCars();
	}

	@PostMapping
	public ResponseEntity<?> addCar(@RequestBody @Valid CarModel carModel) {
		CarModel savedCar = carService.saveCar(carModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
		return carService.findCarById(id).map(car -> ResponseEntity.ok().body(car))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable Long id) {
		try {
			carService.deleteCar(id);
			//return ResponseEntity.ok("Carro deletado com sucesso!");
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CarModel carDetails) {
		if (carDetails.getLicensePlate() == null || carDetails.getLicensePlate().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo 'license_plate' é obrigatório.");
		}

		Optional<CarModel> updatedCar = carService.updateCar(id, carDetails);
		if (updatedCar.isPresent()) {
			return ResponseEntity.ok(updatedCar.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
		}
	}

	// Add more endpoints as needed
}
