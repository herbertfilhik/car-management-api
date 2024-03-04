package com.management.controller;

import com.management.model.Car;
import com.management.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
	
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        // Verifica se já existe um carro com a mesma marca, modelo e ano
        Optional<Car> existingCar = carRepository.findByBrandAndModelAndYear(car.getBrand(), car.getModel(), car.getYear());
        if (existingCar.isPresent()) {
            // Se já existir, retorna uma resposta de conflito
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carro já incluído anteriormente.");
        }
        // Se não existir, salva o novo carro
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok(savedCar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carRepository.findById(id)
                .map(car -> ResponseEntity.ok().body(car))
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
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        }
    }

    // Add more endpoints as needed
}
