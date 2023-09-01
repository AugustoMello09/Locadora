package com.io.github.AugustoMello09.Locadora.resource.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.resources.exception.ResourceExceptionHandler;
import com.io.github.AugustoMello09.Locadora.resources.exception.StandardError;

@SpringBootTest
public class ResourceExceptionHandlerTest {

	private static final String EMAIL_JA_EXISTE = "Email já existe";
	private static final String NAO_ENCONTRADO = "não encontrado";

	@MockBean
	private HttpServletRequest request;

	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenObjectNotFoundThenReturnAResponseEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFound(new ObjectNotFoundException(NAO_ENCONTRADO), new MockHttpServletRequest());
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(NAO_ENCONTRADO, response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}

	@Test
	void whenDataIntegraty() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegraty(new DataIntegratyViolationException(EMAIL_JA_EXISTE), new MockHttpServletRequest());
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(EMAIL_JA_EXISTE, response.getBody().getError());
		assertEquals(400, response.getBody().getStatus());
	}

}
