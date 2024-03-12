package com.management.service;

import com.management.model.CarModel;
import com.management.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CarService {

	private final CarRepository carRepository;

	@Autowired
	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Optional<CarModel> saveCar(CarModel carModel) {
		// Verifica se a placa é null antes de validar
		if (carModel.getPlate() != null) {
			validateLicensePlate(carModel.getPlate());
		} else {
			// Considerar lançar uma exceção ou tratar o caso de placa null conforme a regra
			// de negócio
			throw new IllegalArgumentException("A placa do veículo não pode ser nula.");
		}

		Optional<CarModel> existingCar = carRepository.findByBrandAndModelAndYearAndPlate(carModel.getBrand(),
				carModel.getModel(), carModel.getYear(), carModel.getPlate());
		if (existingCar.isPresent()) {
			// Carro já existe, então retornamos um Optional vazio para indicar conflito
			return Optional.empty();
		} else {
			// Carro não existe, então salvamos e retornamos em um Optional
			return Optional.of(carRepository.save(carModel));
		}
	}

	private void validateLicensePlate(String licensePlate) {
		// Supondo que a placa deva seguir o padrão Mercosul: ABC1D23 ou ABC1234
		Pattern pattern = Pattern.compile("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");
		if (!pattern.matcher(licensePlate).matches()) {
			throw new IllegalArgumentException("Formato de placa inválido.");
		}
	}
}
