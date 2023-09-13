package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.repositories.EstadoRepository;

@SpringBootTest
public class EstadoServiceTest {

	private static final String ESTADO = "São Paulo";

	private static final long ID = 1L;

	private Optional<Estado> optionalEstado;

	private Estado estado;

	private EstadoDTO estadoDTO;

	@Mock
	private EstadoRepository repository;

	@InjectMocks
	private EstadoService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEstado();
	}

	@Test
	void whenFindByIdThenReturnEstadoDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalEstado);
		EstadoDTO response = service.findById(ID);
		Assertions.assertNotNull(response);
		assertEquals(EstadoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(ESTADO, response.getName());
	}
	
	@Test
	void whenFindAllThenReturnListOfEstadoDTO() {
        List<Estado> estados = new ArrayList<>();
        estados.add(new Estado(1L, "PR"));
        estados.add(new Estado(2L, "MT"));
        estados.add(new Estado(3L, "SP"));
        when(repository.findAll()).thenReturn(estados);
        List<EstadoDTO> estadosDTOs = service.findAll();
        verify(repository, times(1)).findAll();
        List<EstadoDTO> expectedDTOs = estados.stream().map(EstadoDTO::new).collect(Collectors.toList());
        assertNotNull(expectedDTOs);
        assertNotNull(estadosDTOs);
		
	}	
	
	
	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenCreateEstadoThenReturnEstadoDTO() {
		Estado estado = new Estado(ID, ESTADO);
		when(repository.save(any(Estado.class))).thenReturn(estado);
		EstadoDTO response = service.create(estadoDTO);
		assertNotNull(response);
		assertEquals(EstadoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(ESTADO, response.getName());
	}

	@Test
	void whenDeleteEstadoWithAssociatedDataThenThrowDataIntegrityViolationException() {
	    Long id = ID;
	    when(repository.findById(id)).thenReturn(Optional.of(estado));
	    doThrow(DataIntegrityViolationException.class).when(repository).deleteById(ID);
	    assertThrows(DataIntegratyViolationException.class, () -> service.delete(ID));
	}

	@Test
	void whenDeleteEstadoThenDeleteSuccessfully() {
		Long estadoId = ID;
		when(repository.findById(estadoId)).thenReturn(optionalEstado);
		service.delete(estadoId);
		verify(repository).findById(estadoId);
		verify(repository).deleteById(estadoId);
	}

	private void startEstado() {
		estadoDTO = new EstadoDTO(ID, ESTADO);
		estado = new Estado(ID, ESTADO);
		optionalEstado = Optional.of(estado);
	}
}
