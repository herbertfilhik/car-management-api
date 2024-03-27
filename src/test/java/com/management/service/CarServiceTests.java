package com.management.service;

import com.management.exception.CarAlreadyExistsException;
import com.management.exception.InvalidModelException;
import com.management.exception.YearOutOfRangeException;
import com.management.model.CarModel;
import com.management.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

	@Mock
	private CarRepository carRepository;

	@InjectMocks
	private CarService carService;
	
    private CarModel existingCar;
    private CarModel carDetails;

	private CarModel carModel;

	@BeforeEach
	void setUp() {
		carModel = new CarModel();
		carModel.setBrand("TestBrand");
		carModel.setModel("TestModel");
		carModel.setYear(2020);
		carModel.setLicensePlate("ABC1D23");
		
        existingCar = new CarModel();
        existingCar.setId(1L);
        existingCar.setBrand("Ford");
        existingCar.setModel("Fiesta");
        existingCar.setYear(2019);
        existingCar.setLicensePlate("XYZ1234");

        carDetails = new CarModel();
        carDetails.setBrand("Tesla");
        carDetails.setModel("Model S");
        carDetails.setYear(2020);        
        carDetails.setLicensePlate("TES1A23");

	}

	@Test
	void saveCar_ValidCar_ReturnsCar() {
		when(carRepository.findByBrandAndModelAndYearAndLicensePlate(any(), any(), any(), any()))
				.thenReturn(Optional.empty());
		when(carRepository.save(any(CarModel.class))).thenReturn(carModel);

		CarModel savedCar = carService.saveCar(carModel);

		assertNotNull(savedCar);
		verify(carRepository, times(1)).save(carModel);
	}

	@Test
	void saveCar_CarAlreadyExists_ThrowsException() {
		when(carRepository.findByBrandAndModelAndYearAndLicensePlate(any(), any(), any(), any()))
				.thenReturn(Optional.of(carModel));

		assertThrows(CarAlreadyExistsException.class, () -> carService.saveCar(carModel));
	}

	@Test
	void validateLicensePlate_InvalidLicensePlate_ThrowsException() {
		carModel.setLicensePlate("Invalid");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> carService.saveCar(carModel));

		assertEquals("Formato de placa inválido.", exception.getMessage());
	}

	@Test
	void findAllCars_ReturnsListOfCars() {
		when(carRepository.findAll()).thenReturn(Arrays.asList(carModel));

		List<CarModel> cars = carService.findAllCars();

		assertFalse(cars.isEmpty());
		verify(carRepository).findAll();
	}

	@Test
	void findCarById_CarExists_ReturnsCar() {
		when(carRepository.findById(anyLong())).thenReturn(Optional.of(carModel));

		Optional<CarModel> foundCar = carService.findCarById(1L);

		assertTrue(foundCar.isPresent());
		verify(carRepository).findById(1L);
	}

	@Test
	void deleteCar_CarExists_DeletesCar() {
		when(carRepository.existsById(anyLong())).thenReturn(true);

		assertDoesNotThrow(() -> carService.deleteCar(1L));
		verify(carRepository).deleteById(1L);
	}

	@Test
	void deleteCar_CarDoesNotExist_ThrowsException() {
		when(carRepository.existsById(anyLong())).thenReturn(false);

		assertThrows(IllegalArgumentException.class, () -> carService.deleteCar(1L));
		verify(carRepository, never()).deleteById(anyLong());
	}

	@Test
	void updateCar_WhenCarExists_ReturnsUpdatedCar() {
		when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(existingCar));
		when(carRepository.save(any(CarModel.class))).thenReturn(carDetails);

		Optional<CarModel> updatedCar = carService.updateCar(1L, carDetails);

		assertTrue(updatedCar.isPresent());
		assertEquals(carDetails.getBrand(), updatedCar.get().getBrand());
		assertEquals(carDetails.getModel(), updatedCar.get().getModel());
		assertEquals(carDetails.getYear(), updatedCar.get().getYear());
		assertEquals(carDetails.getLicensePlate(), updatedCar.get().getLicensePlate());
		verify(carRepository).save(any(CarModel.class)); // Verifica se o carro foi salvo
	}

	@Test
	void updateCar_WhenCarDoesNotExist_ReturnsEmptyOptional() {
		when(carRepository.findById(any(Long.class))).thenReturn(Optional.empty());

		Optional<CarModel> result = carService.updateCar(2L, carDetails);

		assertFalse(result.isPresent()); // Verifica se o resultado é um Optional.empty()
	}

	// Adicione mais testes conforme necessário para cobrir todos os métodos e
	// validações
}
