package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.resources.EstadoResource;
import com.io.github.AugustoMello09.Locadora.service.EstadoService;

@SpringBootTest
public class EstadoResourceTest {
	
	private static final long ID = 1L;


	private static final String ESTADO = "As";

	@InjectMocks
	private EstadoResource resource;
	
	@Mock
	private EstadoService service;
	
	private EstadoDTO estadoDTO;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEstado();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(estadoDTO);
		ResponseEntity<EstadoDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(EstadoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(ESTADO, response.getBody().getName());
	}

	@Test
	public void testCreate() {
		when(service.create(estadoDTO)).thenReturn(estadoDTO);
		ResponseEntity<EstadoDTO> response = resource.create(estadoDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(EstadoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(ESTADO, response.getBody().getName());
		verify(service).create(estadoDTO);
	}
	
	@Test
	void whenFindAllThenReturnEstadosDTO() {
		 List<EstadoDTO> estadosDTOs = new ArrayList<>();
	        estadosDTOs.add(new EstadoDTO(1L, "SP"));
	        estadosDTOs.add(new EstadoDTO(2L, "RJ"));
	     
	     when(service.findAll()).thenReturn(estadosDTOs);  
	     
	     ResponseEntity<List<EstadoDTO>> response = resource.findAll();
	     
	     assertNotNull(response);
	     assertNotNull(response.getBody());
		 assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testDelete() {
		doNothing().when(service).delete(anyLong());
		ResponseEntity<Void> response = resource.delete(ID);
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		verify(service, times(1)).delete(anyLong());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	private void startEstado() {
		estadoDTO = new EstadoDTO(ID, ESTADO);
	}
}
