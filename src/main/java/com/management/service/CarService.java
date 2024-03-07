package com.management.service;

import com.management.model.CarModel;
import com.management.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarModel saveCar(CarModel carModel) throws IllegalArgumentException {
        validateLicensePlate(carModel.getPlate());
        // Aqui entraria o restante da lógica de negócio e chamadas ao repositório
        return carRepository.save(carModel);
    }

    private void validateLicensePlate(String licensePlate) {
        // Supondo que a placa deva seguir o padrão Mercosul: ABC1D23 ou ABC1234
        Pattern pattern = Pattern.compile("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");
        if (!pattern.matcher(licensePlate).matches()) {
            throw new IllegalArgumentException("Formato de placa inválido.");
        }
    }
}
