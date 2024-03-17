package com.management.service;

import com.management.exception.CarAlreadyExistsException;
import com.management.model.CarModel;
import com.management.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CarService {

	private final CarRepository carRepository;

	@Autowired
	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

    public CarModel saveCar(CarModel carModel) {
        validateLicensePlate(carModel.getLicensePlate());

        Optional<CarModel> existingCar = carRepository.findByBrandAndModelAndYearAndLicensePlate(carModel.getBrand(), carModel.getModel(), carModel.getYear(), carModel.getLicensePlate());        																						
        if (existingCar.isPresent()) {
            throw new CarAlreadyExistsException("Carro já incluído anteriormente.");
        }

        return carRepository.save(carModel);
    }

	public Optional<CarModel> updateCar(Long id, CarModel carDetails) {
		return carRepository.findById(id).map(car -> {
			validateLicensePlate(carDetails.getLicensePlate()); // Valida a placa
			car.setBrand(carDetails.getBrand()); // Atualiza a marca
			car.setModel(carDetails.getModel()); // Atualiza o modelo
			car.setYear(carDetails.getYear()); // Atualiza o ano
			car.setLicensePlate(carDetails.getLicensePlate()); // Atualiza a placa
			return Optional.of(carRepository.save(car)); // Salva o carro atualizado
		}).orElse(Optional.empty()); // Se não encontrar o carro, retorna um Optional vazio
	}

	private void validateLicensePlate(String licensePlate) {
		// Supondo que a placa deva seguir o padrão Mercosul: ABC1D23 ou ABC1234
		Pattern pattern = Pattern.compile("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");
		if (!pattern.matcher(licensePlate).matches()) {
			throw new IllegalArgumentException("Formato de placa inválido.");
		}
	}

	public List<CarModel> findAllCars() {
		return carRepository.findAll();
	}

	public Optional<CarModel> findCarById(Long id) {
		return carRepository.findById(id);
	}

	public void deleteCar(Long id) {
		if (carRepository.existsById(id)) {
			carRepository.deleteById(id);
		} else {
			// Pode lançar uma exceção personalizada se o carro não existir.
			throw new IllegalArgumentException("Carro não encontrado!");
		}
	}

}
