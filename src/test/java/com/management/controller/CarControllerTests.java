package com.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.model.CarModel;
import com.management.service.CarService;

class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CarService carService;

	@InjectMocks
	private CarController carController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
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

	@Test
	void deleteCar_WhenCarExists_ShouldReturnNoContent() throws Exception {
		Long carId = 1L;
		doNothing().when(carService).deleteCar(carId);

		mockMvc.perform(delete("/cars/{id}", carId)).andExpect(status().isNoContent());
	}

	@Test
	void deleteCar_WhenCarDoesNotExist_ShouldReturnNotFound() throws Exception {
		Long carId = 2L;
		String errorMessage = "Carro não encontrado!";
		doThrow(new IllegalArgumentException(errorMessage)).when(carService).deleteCar(carId);

		mockMvc.perform(delete("/cars/{id}", carId)).andExpect(status().isNotFound());
	}

	@Test
	void getCarById_WhenCarExists_ShouldReturnCar() throws Exception {
		CarModel carModel = new CarModel();
		carModel.setId(1L);
		carModel.setBrand("Toyota");
		carModel.setModel("Corolla");
		carModel.setYear(2020);
		carModel.setLicensePlate("ABC1D23");

		when(carService.findCarById(anyLong())).thenReturn(Optional.of(carModel));

		mockMvc.perform(get("/cars/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(carModel.getId()))
				.andExpect(jsonPath("$.brand").value(carModel.getBrand()))
				.andExpect(jsonPath("$.model").value(carModel.getModel()))
				.andExpect(jsonPath("$.year").value(carModel.getYear()))
				.andExpect(jsonPath("$.licensePlate").value(carModel.getLicensePlate()));
	}

	@Test
	void getCarById_WhenCarDoesNotExist_ShouldReturnNotFound() throws Exception {
		when(carService.findCarById(anyLong())).thenReturn(Optional.empty());

		mockMvc.perform(get("/car/{id}", 1L).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}
