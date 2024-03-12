package com.management.controller;

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

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarService carService;

	@GetMapping
	public List<CarModel> getAllCars() {
		return carRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> addCar(@RequestBody CarModel carModel) {
	    try {
	        Optional<CarModel> savedCar = carService.saveCar(carModel);
	        if (savedCar.isPresent()) {
	            return ResponseEntity.ok(savedCar.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carro já incluído anteriormente.");
	        }
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarModel> getCarById(@PathVariable Long id) {
		return carRepository.findById(id).map(car -> ResponseEntity.ok().body(car))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable Long id) {
		// Verifica se o carro com o ID fornecido existe
		boolean exists = carRepository.existsById(id);
		if (exists) {
			carRepository.deleteById(id);
			return ResponseEntity.ok("Carro deletado com sucesso!");
		} else {
			// return ResponseEntity.notFound().build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CarModel carDetails) {
	    if (carDetails.getPlate() == null || carDetails.getPlate().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo 'plate' é obrigatório.");
	    }

	    return carRepository.findById(id).map(car -> {
	        try {
	            car.setBrand(carDetails.getBrand());
	            car.setModel(carDetails.getModel());
	            car.setYear(carDetails.getYear());
	            car.setPlate(carDetails.getPlate());
	            Optional<CarModel> updatedCar = carService.saveCar(car);
	            return ResponseEntity.ok(updatedCar);
	        } catch (IllegalArgumentException e) {
	            // Captura a exceção de placa inválida e retorna um bad request
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!"));
	}

	// Add more endpoints as needed
}
