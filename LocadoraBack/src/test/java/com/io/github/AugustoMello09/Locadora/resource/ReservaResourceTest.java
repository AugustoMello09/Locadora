package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.EstoqueDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTO;
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTOInsert;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusEstoque;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaRepository;
import com.io.github.AugustoMello09.Locadora.resources.ReservaResource;
import com.io.github.AugustoMello09.Locadora.service.ReservaService;

@SpringBootTest
public class ReservaResourceTest {

	private static final LocalDate DATA = LocalDate.now();

	private static final StatusReserva ATIVA = StatusReserva.ATIVA;

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private ReservaService service;

	@Mock
	private ReservaRepository repository;

	@InjectMocks
	private ReservaResource resource;

	private ReservaDTO reservaDTO;
	private ReservaDTOInsert reservaDTOInsert;
	private UserDTO userDTO;
	private EstoqueDTO estoqueDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startReserva();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findReservaById(anyLong())).thenReturn(reservaDTO);
		ResponseEntity<ReservaDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ReservaDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQtdReservada());
		assertEquals(DATA, response.getBody().getDataReserva());
		assertEquals(ATIVA, response.getBody().getStatusReserva());
	}

	@Test
	void whenFindAllThenReturnSuccess() {
		when(service.create(reservaDTOInsert)).thenReturn(reservaDTO);
		ResponseEntity<ReservaDTO> response = resource.create(reservaDTOInsert);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(QUANTIDADE, response.getBody().getQtdReservada());
		assertEquals(DATA, response.getBody().getDataReserva());
		assertEquals(ATIVA, response.getBody().getStatusReserva());
		verify(service).create(reservaDTOInsert);
	}

	@Test
	public void testDelete() {
		ResponseEntity<Void> response = resource.cancelar(ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(service).cancelarReserva(ID);
	}

	private void startReserva() {
		reservaDTO = new ReservaDTO(ID, QUANTIDADE, ATIVA, DATA);
		estoqueDTO = new EstoqueDTO(ID, QUANTIDADE, StatusEstoque.DISPONIVEL, null, null, QUANTIDADE, QUANTIDADE, QUANTIDADE);
		userDTO = new UserDTO(ID, "oi", "oi", "oi", null);
		reservaDTOInsert = new ReservaDTOInsert(userDTO, estoqueDTO);
	}
	
}
