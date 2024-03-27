package com.management.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@Import(GlobalExceptionHandler.class) // Importa a classe @ControllerAdvice para o contexto do teste
class GlobalExceptionHandlerTests {
	
    GlobalExceptionHandler handler = new GlobalExceptionHandler();
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    void handleCarAlreadyExists_ReturnsConflictStatus() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        CarAlreadyExistsException exception = new CarAlreadyExistsException("Carro já existe.");

        ResponseEntity<String> response = handler.handleCarAlreadyExists(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Carro já existe.", response.getBody());
    }
    
    @Test
    void handleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Erro de argumento inválido");
        ResponseEntity<String> response = handler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro de argumento inválido", response.getBody());
    }

    @Test
    void handleInvalidLicensePlateException() {
        InvalidLicensePlateException exception = new InvalidLicensePlateException("Placa inválida");
        ResponseEntity<String> response = handler.handleOutraExcecao(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Placa de licença inválida: Placa inválida", response.getBody());
    }

    @Test
    void handleYearOutOfRangeException() {
        YearOutOfRangeException exception = new YearOutOfRangeException("Ano fora do intervalo");
        ResponseEntity<?> response = handler.handleYearOutOfRangeException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message: Ano fora do intervalo", response.getBody());
    }

    @Test
    void handleInvalidModelException() {
        InvalidModelException exception = new InvalidModelException("Modelo inválido");
        ResponseEntity<String> response = handler.handleOutraExcecao(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message: Modelo inválido", response.getBody());
    }

    @Test
    void handleInvalidBrandException() {
        InvalidBrandException exception = new InvalidBrandException("Marca inválida");
        ResponseEntity<String> response = handler.handleOutraExcecao(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Message: Marca inválida", response.getBody());
    }
}
