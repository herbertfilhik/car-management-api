package com.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.exception.CarAlreadyExistsException;
import com.management.model.CarModel;
import com.management.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.eq;


class CarControllerTest {

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void whenAddCarThenReturnSavedCar() {
		CarModel carModel = new CarModel("Toyota", "Corolla", 2020, "ABC1D23");
		when(carService.saveCar(any(CarModel.class))).thenReturn(carModel);

		ResponseEntity<?> response = carController.addCar(carModel);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(carModel, response.getBody());
	}

	@Test
	void whenAddCarWithValidDataThenReturnCreatedStatus() {
		CarModel carModel = new CarModel("Toyota", "Corolla", 2020, "ABC1D23");
		when(carService.saveCar(any(CarModel.class))).thenReturn(carModel);

		ResponseEntity<?> response = carController.addCar(carModel);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	void whenUpdateCarWithValidDataThenReturnUpdatedCar() {
		// Dado (Given)
		CarModel existingCar = new CarModel("Toyota", "Corolla", 2020, "ABC1D23");
		CarModel updatedDetails = new CarModel("Toyota", "Corolla", 2021, "DEF4G56");
		when(carService.updateCar(eq(1L), any(CarModel.class))).thenReturn(Optional.of(updatedDetails));

		// Quando (When)
		ResponseEntity<?> response = carController.updateCar(1L, updatedDetails);

		// Então (Then)
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedDetails, response.getBody());
	}

	@Test
	void whenUpdateCarNotFoundThenReturnNotFoundStatus() {
		// Dado (Given)
		CarModel updatedDetails = new CarModel("Toyota", "Corolla", 2021, "DEF4G56");
		when(carService.updateCar(eq(1L), any(CarModel.class))).thenReturn(Optional.empty());

		// Quando (When)
		ResponseEntity<?> response = carController.updateCar(1L, updatedDetails);

		// Então (Then)
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Carro não encontrado!", response.getBody());
	}

}
